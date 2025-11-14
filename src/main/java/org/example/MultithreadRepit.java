package org.example;


import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MultithreadRepit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        AtomicLong total = new AtomicLong(0);


        int[] numbers = new int[1_000_000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        Callable<int[]> method = new Callable<int[]>() {
            @Override
            public int[] call() throws Exception {
                for (int i = 0; i < numbers.length; i++) {
                    total.getAndAdd(numbers[i]);
                    System.out.println("massiv = " + numbers[i] + " total = " + total.get());
                }
                return numbers;
            }
        };
        Future<int[]> task1 = executor.submit(method);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("total " + total.get());
    }
}
