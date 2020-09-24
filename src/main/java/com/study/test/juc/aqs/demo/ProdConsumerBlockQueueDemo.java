package com.study.test.juc.aqs.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1
 *  线程  操作（方法）  资源类
 *  判断  干活  通知
 *  防止虚假唤醒机制 多线程判断要用 while
 */
class ShareData {
    //默认开启，进行生产+消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public ShareData(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.getClass().getName());
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            Thread.sleep(1000);
        }
        System.out.println("大老板叫停，" + Thread.currentThread().getName() + "生产结束.");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒没取到蛋糕，消费退出.");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列蛋糕" + result + "成功.");
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}

/**
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 */
public class ProdConsumerBlockQueueDemo {

    public static void main(String[] args) {
        ShareData resource = new ShareData(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            try {
                resource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                resource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5s时间到，停止生产.");
        resource.stop();
    }
}
