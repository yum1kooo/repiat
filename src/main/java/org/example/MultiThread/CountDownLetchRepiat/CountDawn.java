package org.example.MultiThread.CountDownLetchRepiat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDawn {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Test test = new Test(latch);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(test);
        }

        executorService.shutdown();

        latch.await();
        System.out.println("Latch open");


    }
}


class Test implements Runnable {
    CountDownLatch count;

    public Test(CountDownLatch count) {
        this.count = count;
    }

    public void run() {
        try {
            Thread.sleep(1333);
        } catch (InterruptedException e) {
        }
            count.countDown();
    }
}