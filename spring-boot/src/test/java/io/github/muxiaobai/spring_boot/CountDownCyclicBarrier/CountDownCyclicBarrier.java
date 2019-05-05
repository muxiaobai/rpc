package io.github.muxiaobai.spring_boot.CountDownCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CountDownCyclicBarrier {

    private static final int nums = 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(nums);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier (nums+1);

    public static void main(String[] args) {
        CountDownCyclicBarrier countDownCyclicBarrier = new CountDownCyclicBarrier ();
        countDownCyclicBarrier.test ();
    }
    public void test(){
        Long start = System.currentTimeMillis ();
        for(int i  = 0;i<nums;i++){
            Thread thread = new Thread(()->{
                try {
                    countDownLatch.await();
                    Thread.sleep (5000);
                    System.out.println("ThreadName:"+Thread.currentThread().getName());
                    cyclicBarrier.await ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            countDownLatch.countDown();
        }
        Long end = 0L ;
        try {
            cyclicBarrier.await();
            end = System.currentTimeMillis ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (BrokenBarrierException e) {
            e.printStackTrace ();
        }
        System.out.println (end- start);
    }
}
