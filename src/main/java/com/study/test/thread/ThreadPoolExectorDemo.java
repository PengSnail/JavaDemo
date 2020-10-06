package com.study.test.thread;

import java.util.concurrent.*;

public class ThreadPoolExectorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,       //核心线程数
                5,  //最大线程数
                1,      //空闲线程的存活时间
                TimeUnit.SECONDS,     //keepAliveTime的单位
                new LinkedBlockingQueue<Runnable>(3),   //暂存等待队列
                Executors.defaultThreadFactory(),   //线程工厂
                new ThreadPoolExecutor.AbortPolicy());//饱和策略
        for (int i = 0; i < 9; i++) {
            threadPoolExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }
}
