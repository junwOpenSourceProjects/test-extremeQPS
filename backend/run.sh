#!/bin/bash

# ==========================================
# 极限压榨 2核 2G 内存的 JVM 启动脚本
# ==========================================

JAR_FILE="target/extreme-qps-backend-1.0-SNAPSHOT.jar"

# 必须使用 SerialGC: 在双核且内存极小(512M分配给Java)的环境下，
# G1GC 会因为本身内存开销大、频繁产生 concurrent cycle 而严重拖慢性能。
# SerialGC (或者 ParallelGC) 在小内存下吞吐量极高。
GC_OPTS="-XX:+UseSerialGC"

# 严格限制内存
MEM_OPTS="-Xms512m -Xmx512m -XX:MaxMetaspaceSize=128m -XX:ReservedCodeCacheSize=128m"

# 禁用偏向锁 (JDK 15后已废弃，但在旧版本提升高并发性能)，开启大页内存支持等
PERF_OPTS="-Djava.net.preferIPv4Stack=true -Dvertx.disableMetrics=true -Dvertx.disableTCCL=true"

# 使用 JDK 21+ 虚拟线程 (Loom)
# 在 Vert.x 的事件循环中使用虚拟线程可以无缝实现同步写法的异步非阻塞执行
LOOM_OPTS="--enable-preview"

echo "🚀 启动极限性能后端..."
echo "JAVA_OPTS: $GC_OPTS $MEM_OPTS $PERF_OPTS"

java $GC_OPTS $MEM_OPTS $PERF_OPTS -jar $JAR_FILE
