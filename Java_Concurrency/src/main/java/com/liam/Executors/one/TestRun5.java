package com.liam.Executors.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用newSingleThreadExecutor()创建单一线程池，实现队列方式
 */
public class TestRun5 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i =0 ;i < 3;i++){
            executorService.execute(new MyRunnable((""+(i+1))));
        }
    }

    private static class MyRunnable implements Runnable {
        private String username;
        public MyRunnable(String s){
            super();
            this.username=s;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+" userName="+username+" 开始");
                System.out.println(Thread.currentThread().getName()+" userName="+username+" 结束");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
