package org.example.ReentrantReadWriteLock;

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
                    market.getShares("gazprom");
                }
            });
        }
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    market.getShares("lukoil");
                }
            });
        }

        executor.submit(new Runnable() {
            @Override
            public void run() {
                market.updateDBSharesPrices();
            }
        });
    }
}
