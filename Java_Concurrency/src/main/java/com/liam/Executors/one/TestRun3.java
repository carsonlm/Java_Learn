package com.liam.Executors.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class TestRun3 {
    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory();
        ExecutorService executorService = Executors.newCachedThreadPool(myThreadFactory);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("运行"+System.currentTimeMillis()+Thread.currentThread().getName());
            }
        });
    }

    private static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("定制池中的线程池对象名 "+Math.random());
            return thread;
        }
    }
}
