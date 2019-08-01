package com.liam.Executors.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 验证newCachedThreadPool()方法创建的线程池，没有达到池中线程对象时可以复用的。
 */
public class TestRun1 {

    public static void main(String[] args) {
        ExecutorService executorService  = Executors.newCachedThreadPool();
        for (int i = 0;i < 10;i++){
            executorService.execute(new MyRunnable((""+ (i+1))));
        }
    }

    private static class MyRunnable implements Runnable {
        private String usernane;

        public MyRunnable(String s) {
            super();
            this.usernane = s;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "userName=" + usernane + "  开始" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "userName=" + usernane + "  结束" + System.currentTimeMillis());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
