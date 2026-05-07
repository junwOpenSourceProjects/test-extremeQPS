package com.extreme;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

public class DbManager {
    
    private static MySQLPool pool;

    public static void init(Vertx vertx) {
        // 配置响应式非阻塞 MySQL 连接
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
            .setPort(3306)
            .setHost("127.0.0.1")
            .setDatabase("extreme_db")
            .setUser("root")
            .setPassword("root")
            .setCachePreparedStatements(true);

        // 极限情况下的连接池配置，因为我们是异步的，连接池不需要太大
        PoolOptions poolOptions = new PoolOptions()
            .setMaxSize(20) // 20 个异步连接足以打满 2核 CPU 和 400M 内存的 MySQL
            .setShared(true);

        pool = MySQLPool.pool(vertx, connectOptions, poolOptions);
        System.out.println("✅ Reactive MySQL 连接池初始化完成");
    }

    public static MySQLPool getPool() {
        return pool;
    }
}
