package org.example.ReentrantReadWriteLock;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MarketData {
    static TreeMap<String,Double> dbSharesPrices = new TreeMap<>();
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    Lock readLock = rwl.readLock();
    Lock writeLock = rwl.writeLock();
    Scanner sc = new Scanner(System.in);

   public MarketData(){
       dbSharesPrices.put("gazprom", 1.2);
       dbSharesPrices.put("lukoil", 1.2);
   }


    public String getShares(String name) {
       readLock.lock();
       try {
           int countThread = rwl.getReadLockCount();
       for (Map.Entry<String, Double> entry : dbSharesPrices.entrySet()) {
            if (entry.getKey().equals(name.toLowerCase())) {
                return name;
            }
        }
       } finally {
           readLock.unlock();
       }
        return "input incorrect";
    }

    public void updateDBSharesPrices() {
        writeLock.lock();
        System.out.println("0 - exit");
        System.out.println("Enter name");
        String name = sc.nextLine().trim().toLowerCase();
        System.out.println("Enter price");
        Double price = sc.nextDouble();
        try {
                for (Map.Entry<String, Double> entry : dbSharesPrices.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(name)) {
                        System.out.println("Shares found");
                        dbSharesPrices.put(name.trim().toLowerCase(), price);
                    }
                    System.out.println("Shares updated" + " name " + name + " price " + price);
                }
        } finally {
            writeLock.unlock();
        }
    }
}
