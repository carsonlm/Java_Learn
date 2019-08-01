package com.liam.Executors.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 达到线程池复用
 */
public class TestRun2 {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i =0; i < 5 ;i++){
            executorService.execute(new MyRunnable(("X"+(i+1))));
        }
        Thread.sleep(1000);
        System.out.println("");
        System.out.println("");
        for (int i = 0; i <5 ;i++){
            executorService.execute(new MyRunnable(("Y"+ (i+1))));
        }
    }

    private static class MyRunnable implements Runnable {
        private String usernamne;

        public MyRunnable(String s){
            super();
            this.usernamne =s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" userName"+usernamne+" 开始");
            System.out.println(Thread.currentThread().getName()+" userName"+usernamne+" 结束");
        }
    }
}
