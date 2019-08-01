package com.liam.Executors.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class TestRun4 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0;i < 3 ; i++){
            executorService.execute(new MyRunnable(("" + (i+1))));
        }
        for (int i = 0;i < 3 ; i++){
            executorService.execute(new MyRunnable(("" + (i+1))));
        }
    }

    private static class MyRunnable implements Runnable {

        private String usrename ;
        public MyRunnable(String s){
            this.usrename = s;
        }

        @Override
        public void run() {
            try{
                System.out.println(Thread.currentThread().getName()+" username="+usrename+"开始");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" username="+usrename+"结束");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
