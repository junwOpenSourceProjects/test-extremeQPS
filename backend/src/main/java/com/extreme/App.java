package com.extreme;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class App {

    public static void main(String[] args) {
        // 1. 开启 Vert.x 并在 Linux 上强制使用 Netty Native Epoll
        // 原生 Epoll 能大幅度降低网络中断带来的 CPU 上下文切换
        VertxOptions options = new VertxOptions()
            .setPreferNativeTransport(true); 

        Vertx vertx = Vertx.vertx(options);
        
        System.out.println("🚀 极限性能后端启动中...");
        if (vertx.isNativeTransportEnabled()) {
            System.out.println("✅ Native Epoll 传输已启用!");
        } else {
            System.out.println("⚠️ 未使用 Native Epoll (可能在非 Linux 环境)");
        }

        // 初始化子系统
        DbManager.init(vertx);
        DisruptorManager.init();
        AeronIpcServer.init();

        // 2. 配置 Router
        Router router = Router.router(vertx);
        
        // 一个模拟获取业务数据的接口
        router.get("/api/users").handler(UserHandler::getUsers);
        
        // 一个纯粹的 ping 接口，测试框架最高性能
        router.get("/api/ping").handler(ctx -> ctx.response().end("pong"));
        
        router.get("/api/level1").handler(UserHandler::level1Json);
        router.get("/api/level2").handler(UserHandler::level2Auth);
        router.get("/api/level3").handler(UserHandler::level3IO);

        // 一个接收数据的接口，压测写性能
        router.post("/api/track").handler(UserHandler::trackEvent);

        // 3. 创建极简 HTTP 服务器
        HttpServerOptions serverOptions = new HttpServerOptions()
            .setTcpFastOpen(true)   // 开启 TCP Fast Open 减少握手延迟
            .setTcpNoDelay(true)    // 禁用 Nagle 算法，降低延迟
            .setTcpQuickAck(true);  // Linux 特有的 Quick ACK

        HttpServer server = vertx.createHttpServer(serverOptions);

        server.requestHandler(router)
              .listen(8080, http -> {
                  if (http.succeeded()) {
                      System.out.println("✅ HTTP Server 监听在 8080 端口");
                  } else {
                      System.err.println("❌ 启动失败: " + http.cause());
                  }
              });
    }
}
