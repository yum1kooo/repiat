package org.example.CollableAndFuture;


import java.util.concurrent.*;

public class CollableFutureTaskOne {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Long total = 0l;
        long a = 166_666_500_000L;
        long b = 333_333_000_000L;
        long c = 499_999_500_000L;

        //499_999_500_000
        //166_666_500_000

        long start = System.currentTimeMillis();

        int[] numbersOne = new int[333_333];
        for (int i = 0; i < numbersOne.length; i++) {
            numbersOne[i] = i;
        }

        int[] numbersTwo = new int[333_333];
        for (int i = 0; i < numbersTwo.length; i++) {
            numbersTwo[i] = i + 333_333;
        }

        int[] numbersThree = new int[333_334];
        for (int i = 0; i < numbersThree.length; i++) {
            numbersThree[i] = i + 666_666;
        }

        Callable<Long> method1 = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                long sum = 0;
                for (int i = 0; i < 333_333; i++) {
                    sum += numbersOne[i];
                }
                return sum;
            }
        };

        Callable<Long> method2 = new Callable<Long>() {
            public Long call() throws Exception {
                long sum = 0;
                for (int i = 0; i < numbersTwo.length; i++) {
                    sum += numbersTwo[i];
                }
                return sum;
            }
        };

        Callable<Long> method3 = new Callable<Long>() {
            public Long call() throws Exception {
                long sum = 0;
                for (int i = 0; i < numbersThree.length; i++) {
                    sum += numbersThree[i];
                }
                return sum;
            }
        };

        Future<Long> task1 = executor.submit(method1);
        Future<Long> task2 = executor.submit(method2);
        Future<Long> task3 = executor.submit(method3);

        Long partOne = task1.get();
        Long partTwo = task2.get();
        Long partThree = task3.get();

        long end = System.currentTimeMillis();

        long finalSum = partOne + partTwo + partThree;

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("total " + finalSum + " time " + (end - start));
    }
}
