package com.liam.Executors.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建了两个线程，他们之间是异步运行
 */
public class TestPool1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1开始");
                    Thread.sleep(1000);
                    System.out.println("1结束");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("2开始");
                    Thread.sleep(1000);
                    System.out.println("2结束");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
