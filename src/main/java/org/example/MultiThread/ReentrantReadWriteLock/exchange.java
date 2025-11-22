package org.example.MultiThread.ReentrantReadWriteLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class exchange {
    public static void main(String[] args) {
        MarketData market = new  MarketData();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(market.getShares("Lukoil"));
                }
            });
        }

        executor.submit(new Runnable() {
            @Override
            public void run() {
                market.updateDBSharesPrices("lukoil", 16.3);
            }
        });

        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(market.getShares("gazprom"));
                }
            });
        }

        executor.submit(new Runnable() {
            @Override
            public void run() {
                market.updateDBSharesPrices("gazprom", 12.3);
            }
        });


        executor.shutdown();
    }
}
