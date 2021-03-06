package com.study.test.juc.aqs;


import lombok.Getter;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Getter
enum CountryEnum {
    ONE(1, "楚"),
    TWO(2, "齐"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");

    private Integer retCode;
    private String retMsg;

    CountryEnum(Integer retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum element : values) {
            if (index == element.getRetCode()) {
                return element;
            }
        }
        return null;
    }
}

/**
 * https://www.apiref.com/java11-zh/java.base/java/util/concurrent/CountDownLatch.html#countDown()
 * @author PengG
 * @date 2020/9/24 8:57
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            final int tempI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  " + CountryEnum.forEach_CountryEnum(tempI).getRetMsg() + "国灭");
                //将计数减一
                //减少锁存器的计数，如果计数达到零则释放所有等待的线程。
                countDownLatch.countDown();
            }, i+"").start();
        }
        try {
            //只有六国全灭，秦国才是一统天下
            //当 CountDownLatch 的计数为0时，主线程才继续执行
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + " 六国灭，秦国一统天下");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待所有玩家加载完成才能开始游戏
     * @throws InterruptedException
     */
    public static void loadGame() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Random random = new Random();
        String[] allPlayers = new String[10];
        for (int i = 0; i < 10; i++) {
            int temp = i;
            threadPool.submit(() -> {
                for (int j = 0; j <= 100; j++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    allPlayers[temp] = j + "%";
                    System.out.print("\r" + Arrays.toString(allPlayers));
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("\n所有玩家加载完成，游戏开始");
        threadPool.shutdown();
    }
}
