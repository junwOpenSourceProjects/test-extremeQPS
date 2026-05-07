package com.extreme;

import com.dslplatform.json.DslJson;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {

    // 使用 DSL-JSON 实现超高速 JSON 序列化
    private static final DslJson<Object> dslJson = new DslJson<>();

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

    // DSL-JSON 需要的简单 DTO 结构 (实际中可以通过注解生成)
    public static class UserDTO {
        public Integer id;
        public String name;
    }
}
