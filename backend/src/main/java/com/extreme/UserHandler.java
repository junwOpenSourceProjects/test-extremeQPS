package com.extreme;

import com.dslplatform.json.DslJson;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.dslplatform.json.CompiledJson;

public class UserHandler {

    // 使用 DSL-JSON 实现超高速 JSON 序列化
    private static final DslJson<Object> dslJson = new DslJson<>(new com.dslplatform.json.DslJson.Settings<>().includeServiceLoader());

    public static void getUsers(RoutingContext ctx) {
        DbManager.getPool().query("SELECT id, name FROM users LIMIT 100")
            .execute(ar -> {
                if (ar.succeeded()) {
                    List<UserDTO> users = new ArrayList<>();
                    ar.result().forEach(row -> {
                        UserDTO u = new UserDTO();
                        // 假定 row 的字段顺序或名称
                        u.id = row.getInteger("id");
                        u.name = row.getString("name");
                        users.add(u);
                    });
                    
                    sendJsonResponse(ctx, users);
                } else {
                    ctx.response().setStatusCode(500).end(ar.cause().getMessage());
                }
            });
    }

    public static void trackEvent(RoutingContext ctx) {
        // 将高频的数据直接丢入 Disruptor，不在这里阻塞或直接写库
        // 从而保证接口极高的吞吐量
        DisruptorManager.publishEvent("TrackEvent_" + System.currentTimeMillis());
        ctx.response().setStatusCode(200).end("OK");
    }

    // Level 1: 复杂 JSON 响应 (5-10个字段)
    public static void level1Json(RoutingContext ctx) {
        ComplexDTO dto = new ComplexDTO();
        dto.id = 1001L;
        dto.title = "极限 QPS 测试";
        dto.description = "这是一款用于压测的高性能服务";
        dto.status = 1;
        dto.createdAt = System.currentTimeMillis();
        dto.tags = List.of("Performance", "Java", "Vert.x");
        dto.author = "Antigravity";
        sendJsonResponse(ctx, dto);
    }

    // Level 2: MD5 鉴权拦截器消耗 CPU + 返回 JSON
    public static void level2Auth(RoutingContext ctx) {
        // 模拟 MD5 计算消耗 CPU
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(("SimulateJWTToken_" + System.currentTimeMillis()).getBytes());
            byte[] digest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        level1Json(ctx);
    }

    // Level 3: 鉴权 + 数据库查询
    public static void level3IO(RoutingContext ctx) {
        // 模拟 Auth
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(("SimulateJWTToken_" + System.currentTimeMillis()).getBytes());
            md.digest();
        } catch (NoSuchAlgorithmException e) { }

        // 熔断器包裹的数据库查询，超过 3 秒强制熔断降级
        DbManager.getBreaker().<io.vertx.sqlclient.RowSet<io.vertx.sqlclient.Row>>execute(promise -> {
            DbManager.getPool().query("SELECT * FROM xhs_note WHERE id LIKE '%9000000000000001%' LIMIT 1")
                .execute(ar -> {
                    if (ar.succeeded()) {
                        promise.complete(ar.result());
                    } else {
                        promise.fail(ar.cause());
                    }
                });
        }).onComplete(ar -> {
            ComplexDTO dto = new ComplexDTO();
            if (ar.succeeded() && ar.result() != null) {
                if (ar.result().size() > 0) {
                    dto.id = ar.result().iterator().next().getLong("id"); // 假设有id字段，容错
                    dto.title = "Found Record";
                } else {
                    dto.id = 0L;
                    dto.title = "Not Found";
                }
                dto.description = "Level 3 IO Result";
            } else {
                // 熔断/超时/异常降级返回
                dto.id = -1L;
                dto.title = "System Degraded / Timeout";
                dto.description = "Too many concurrent slow queries, circuit breaker activated";
            }
            dto.status = 1;
            dto.createdAt = System.currentTimeMillis();
            sendJsonResponse(ctx, dto);
        });
    }

    // 高性能序列化输出
    private static void sendJsonResponse(RoutingContext ctx, Object obj) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            dslJson.serialize(obj, os);
            ctx.response()
                .putHeader("content-type", "application/json")
                .end(Buffer.buffer(os.toByteArray()));
        } catch (IOException e) {
            ctx.response().setStatusCode(500).end();
        }
    }

    // DSL-JSON 需要的简单 DTO 结构
    @CompiledJson
    public static class UserDTO {
        public Integer id;
        public String name;
    }

    @CompiledJson
    public static class ComplexDTO {
        public Long id;
        public String title;
        public String description;
        public Integer status;
        public Long createdAt;
        public List<String> tags;
        public String author;
    }
}
