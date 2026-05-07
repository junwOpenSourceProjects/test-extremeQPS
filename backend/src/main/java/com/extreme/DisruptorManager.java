package com.extreme;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class DisruptorManager {

    private static RingBuffer<LogEvent> ringBuffer;

    public static void init() {
        // 缓冲区大小必须是 2 的幂
        int bufferSize = 1024 * 64; 
        
        Disruptor<LogEvent> disruptor = new Disruptor<>(
                LogEvent::new,
                bufferSize,
                DaemonThreadFactory.INSTANCE
        );

        // 设置消费者
        disruptor.handleEventsWith(new LogEventHandler());
        
        ringBuffer = disruptor.start();
        System.out.println("✅ Disruptor 无锁队列初始化完成");
    }

    public static void publishEvent(String data) {
        if (ringBuffer == null) return;
        long sequence = ringBuffer.next();
        try {
            LogEvent event = ringBuffer.get(sequence);
            event.data = data;
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    // 事件定义
    public static class LogEvent {
        public String data;
    }

    // 消费者定义
    public static class LogEventHandler implements EventHandler<LogEvent> {
        @Override
        public void onEvent(LogEvent event, long sequence, boolean endOfBatch) {
            // 在这里将高频埋点数据批量写入时序数据库 QuestDB
            // System.out.println("Disruptor 异步处理消费: " + event.data);
        }
    }
}
