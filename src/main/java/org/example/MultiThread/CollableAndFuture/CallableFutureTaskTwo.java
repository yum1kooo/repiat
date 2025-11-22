package org.example.MultiThread.CollableAndFuture;

import java.util.Random;
import java.util.concurrent.*;

public class CallableFutureTaskTwo {
    static String[] words = {"apple", "banana", "orange"};
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        int maxLength = 5;



        Callable<Integer> task1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                String[] words1 = new String[200_000];
                int sum = 0;
                for (int i = 0; i < words1.length; i++) {
                    int rand = random.nextInt(words.length);
                    String word = words[rand];
                    words1[i] = word;
                    if(words1[i].length() > maxLength){
                        sum++;
                        System.out.println(words1[i] + ": word > 5" + " sum word > 5 " + sum);
                    } else  {
                        System.out.println(words1[i] + " word < max length ");
                    }
                }
                return sum;
            }
        };

        Callable<Integer> task2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                String[] words1 = new String[200_000];
                int sum = 0;
                for (int i = 0; i < words1.length; i++) {
                    int rand = random.nextInt(words.length);
                    String word = words[rand];
                    words1[i] = word;
                    if(words1[i].length() > maxLength){
                        sum++;
                        System.out.println(words1[i] + ": word > 5" + " sum word > 5 " + sum);
                    } else  {
                        System.out.println(words1[i] + " word < max length ");
                    }
                }
                return sum;
            }
        };
        Callable<Integer> task3 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                String[] words1 = new String[200_000];
                int sum = 0;
                for (int i = 0; i < words1.length; i++) {
                    int rand = random.nextInt(words.length);
                    String word = words[rand];
                    words1[i] = word;
                    if(words1[i].length() > maxLength){
                        sum++;
                        System.out.println(words1[i] + ": word > 5" + " sum word > 5 " + sum);
                    } else  {
                        System.out.println(words1[i] + " word < max length ");
                    }
                }
                return sum;
            }
        };
        Callable<Integer> task4 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                String[] words1 = new String[200_000];
                int sum = 0;
                for (int i = 0; i < words1.length; i++) {
                    int rand = random.nextInt(words.length);
                    String word = words[rand];
                    words1[i] = word;
                    if(words1[i].length() > maxLength){
                        sum++;
                        System.out.println(words1[i] + ": word > 5" + " sum word > 5 " + sum);
                    } else  {
                        System.out.println(words1[i] + " word < max length ");
                    }
                }
                return sum;
            }
        };
        Callable<Integer> task5 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                String[] words1 = new String[200_000];
                int sum = 0;
                for (int i = 0; i < words1.length; i++) {
                    int rand = random.nextInt(words.length);
                    String word = words[rand];
                    words1[i] = word;
                    if(words1[i].length() > maxLength){
                        sum++;
                        System.out.println(words1[i] + ": word > 5" + " sum word > 5 " + sum);
                    } else  {
                        System.out.println(words1[i] + " word < max length ");
                    }
                }
                return sum;
            }
        };


        Future<Integer> sum1 = executorService.submit(task1);
        Future<Integer> sum2 = executorService.submit(task2);
        Future<Integer> sum3 = executorService.submit(task3);
        Future<Integer> sum4 = executorService.submit(task4);
        Future<Integer> sum5 = executorService.submit(task5);

        int a = sum1.get();
        int b = sum2.get();
        int c = sum3.get();
        int d = sum4.get();
        int e = sum5.get();

        int finalSum = a + b + c + d + e;

        executorService.shutdown();
        System.out.println("sum1: " + finalSum);

    }
}
