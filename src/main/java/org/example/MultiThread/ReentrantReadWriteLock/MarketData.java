package org.example.MultiThread.ReentrantReadWriteLock;

import java.util.Map;
import java.util.Random;
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
    Random rand = new Random();
   public MarketData(){
       dbSharesPrices.put("gazprom", 1.2);
       dbSharesPrices.put("lukoil", 1.2);
   }


    public  void printAllShares(){
       readLock.lock();
       try {
           for(Map.Entry<String,Double> entry:dbSharesPrices.entrySet()){
                System.out.println("name shares " + entry.getKey()+" prices "+ entry.getValue());
       }
       } finally {
           readLock.unlock();
       }
    }


    public String getShares(String name) {
        readLock.lock();
        try {
            String key = name.toLowerCase().trim();
            if (dbSharesPrices.containsKey(key)) {
                Double price = dbSharesPrices.get(key);
                System.out.println("Читателей сейчас: " + rwl.getReadLockCount());
                return name + " price " + price;
            }
        } finally {
            readLock.unlock();
        }
        return "name incorrect";
    }

    public void updateDBSharesPrices(String name, Double price) {
        writeLock.lock();
        try {

            String key = name.toLowerCase().trim();
            if(dbSharesPrices.containsKey(key)) {
                dbSharesPrices.put(key, price);
                System.out.println(">>> Shares updated: " + name + " New Price: " + price);
            } else {
                System.out.println("Акция " + name + " не найдена для обновления");
            }

        } finally {
            writeLock.unlock();
        }
    }
}
