package org.example.MultiThread.CashDataBase;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppCashSystem {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Random rand = new Random();
        UserCash us = new UserCash();
        String[] name = {"Oleg", "Timchik", "Alice", "Tom"};

        for (int i = 0; i < 15; i++) {
            executorService.execute(() -> {
                System.out.println(us.getUser(rand.nextInt(12)));
            });
        }

        for (int i = 0; i < 13; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    us.updateUser(rand.nextInt(13), name[rand.nextInt(4)]);
                }
            });
        }

        executorService.submit(new Runnable(){
            public void run() {
                us.clearCash();
            }
        });

        for (int i = 0; i < 15; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    us.userCashAdd(rand.nextInt(13), name[rand.nextInt(4)]);
                }
            });
        }
        executorService.shutdown();
    }
}
