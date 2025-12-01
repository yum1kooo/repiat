package org.example.MultiThread.WaitNotify;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

public class ProduceConsumer {
    public static void main(String[] args) {
        Prod prod = new Prod();
        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 25; i++) {
            executor.submit(() -> {
                try {
                    prod.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (int i = 0; i < 25; i++) {
            executor.submit(() -> {
                try {
                    prod.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

            executor.shutdown();
    }

}
class Prod {
    ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(50);
    Random rand = new Random();

    Semaphore producerLimit = new Semaphore(10); // A одновременно produce MAX
    Semaphore orderLimit = new Semaphore(10);    // макс заказов

    public void produce() throws InterruptedException {
        producerLimit.acquire();
        try {
            orderLimit.acquire();               // ждем если переполнено
            int randNum = rand.nextInt(100);
            System.out.println("Пришел новый заказ " + randNum);
            queue.put(randNum);
        } finally {
            producerLimit.release();
        }
    }

    public void consumer() throws InterruptedException {
        int order = queue.take();
        System.out.println("Беру и обрабатываю заказ " + order);
        orderLimit.release();                   // освободили место в лимите
    }
}

