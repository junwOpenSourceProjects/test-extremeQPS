#!/bin/bash

# ==========================================
# 极限 QPS 压测模拟脚本
# ==========================================
# 依赖工具: wrk (brew install wrk 或 apt install wrk)

echo "================ 开始压测 ================"
echo "场景 1: 测试 Nginx 静态文件 (Nuxt SSG) 吞吐量"
# 模拟 10 万个并发连接 (使用 12 个线程) 持续 10 秒
# wrk -t12 -c1000 -d10s http://127.0.0.1/

echo "场景 2: 测试 Java 业务 API 极限吞吐量 (不走 Nginx)"
# wrk -t12 -c1000 -d10s http://127.0.0.1:8080/api/users

echo "场景 3: 测试纯写/埋点吞吐量 (结合 Disruptor 无锁队列)"
# 编写 lua 脚本生成 POST 请求
cat << 'EOF' > post.lua
wrk.method = "POST"
wrk.body   = '{"event":"click","time":161123123}'
wrk.headers["Content-Type"] = "application/json"
EOF

# wrk -t12 -c1000 -d10s -s post.lua http://127.0.0.1:8080/api/track

echo "注意: 压测时请观察 htop/top 确认上下文切换 (CS) 次数，得益于 Epoll 和 Disruptor，该指标应保持在合理水平。"
