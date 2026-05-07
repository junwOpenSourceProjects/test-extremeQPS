package com.extreme;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;

public class DbManager {
    
    private static MySQLPool pool;
    private static CircuitBreaker breaker;

    public static void init(Vertx vertx) {
        // 配置响应式非阻塞 MySQL 连接
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
            .setPort(3306)
            .setHost("127.0.0.1")
            .setDatabase("tikhub")
            .setUser("root")
            .setPassword("")
            .setCachePreparedStatements(true);

        // 极限情况下的连接池配置，因为我们是异步的，连接池不需要太大
        PoolOptions poolOptions = new PoolOptions()
            .setMaxSize(20) // 20 个异步连接足以打满 2核 CPU 和 400M 内存的 MySQL
            .setMaxWaitQueueSize(100) // 超过 100 个请求排队直接快速失败，防雪崩
            .setShared(true);

        pool = MySQLPool.pool(vertx, connectOptions, poolOptions);

        // 初始化数据库熔断器：3秒超时，失败5次开启熔断，半开状态下隔10秒重试
        breaker = CircuitBreaker.create("mysql-circuit-breaker", vertx,
            new CircuitBreakerOptions()
                .setMaxFailures(5) // 连续失败5次熔断
                .setTimeout(3000) // SQL 超过 3 秒强制超时返回
                .setFallbackOnFailure(true) // 熔断后是否执行降级逻辑
                .setResetTimeout(10000) // 10秒后尝试恢复
        ).fallback(v -> {
            // 降级返回，快速释放资源
            System.err.println("⚠️ 触发数据库熔断/超时降级！快速释放请求。");
            return null; // 或者返回一个默认的安全对象
        });

        System.out.println("✅ Reactive MySQL 连接池及熔断器初始化完成");
    }

    public static MySQLPool getPool() {
        return pool;
    }

    public static CircuitBreaker getBreaker() {
        return breaker;
    }
}
