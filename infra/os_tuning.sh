#!/bin/bash

# ==========================================
# Ubuntu 极限高并发与低内存调优脚本
# 请使用 root 权限执行此脚本 (sudo ./os_tuning.sh)
# ==========================================

echo "================ 开始系统调优 ================"

# 1. 创建并启用 4GB Swap (虚拟内存)
# 在 2核2G 机器上，为了防止 OOM，强制增加 Swap。
if [ ! -f "/swapfile" ]; then
    echo "创建 4GB Swapfile..."
    fallocate -l 4G /swapfile
    chmod 600 /swapfile
    mkswap /swapfile
    swapon /swapfile
    # 持久化到 fstab
    echo '/swapfile none swap sw 0 0' >> /etc/fstab
    echo "Swapfile 创建完成。"
else
    echo "Swapfile 已存在，跳过创建。"
fi

# 2. 调整内核 Swappiness 倾向
# 设置为 30 (默认 60)。告诉系统：尽量使用物理内存保证实时性，
# 但也不要死抗到 OOM 才用 Swap。
sysctl vm.swappiness=30
echo 'vm.swappiness=30' >> /etc/sysctl.conf

# 3. 文件句柄数及 TCP 极限调优 (百万 QPS 必备)
cat >> /etc/sysctl.conf << EOF
# 系统级别最大文件句柄数
fs.file-max = 2000000
# 允许的最大跟踪连接数
net.netfilter.nf_conntrack_max = 2000000
# 提升 TCP TIME_WAIT 回收效率
net.ipv4.tcp_tw_reuse = 1
# 扩大本地端口范围
net.ipv4.ip_local_port_range = 1024 65000
# 扩大 TCP 接收和发送缓冲区
net.ipv4.tcp_rmem = 4096 87380 16777216
net.ipv4.tcp_wmem = 4096 65536 16777216
# 增加 TCP 最大连接数
net.core.somaxconn = 65535
EOF

# 应用 sysctl
sysctl -p

# 4. 解除用户级别的限制
cat >> /etc/security/limits.conf << EOF
* soft nofile 1000000
* hard nofile 1000000
root soft nofile 1000000
root hard nofile 1000000
EOF

echo "================ 系统调优完成 ================"
echo "请重新登录或重启系统以确保所有 limits.conf 生效。"
