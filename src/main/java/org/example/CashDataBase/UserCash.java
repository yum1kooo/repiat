package org.example.CashDataBase;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserCash {
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    Lock readLock = rwl.readLock();
    Lock writeLock = rwl.writeLock();
    HashMap<Integer, String> cash = new HashMap<>();

    public UserCash(){
        cash.put(1, "Andrey");
        cash.put(2, "Bob");
        cash.put(3, "Jack");
        cash.put(4, "Chris");
        cash.put(5, "David");
        cash.put(6, "John");
        cash.put(7, "Joe");
        cash.put(8, "Joe");
        cash.put(9, "Joe");
        cash.put(10, "Joe");
        cash.put(11, "Joe");
        cash.put(12, "Joe");
        cash.put(13, "Joe");
    }

    public String getUser(int id){
        readLock.lock();
        try {
        if(cash.containsKey(id)) {
            return cash.get(id);
        }
        } finally {
            readLock.unlock();
        }
        return null;
    }

    public void userCashAdd(int id)

    public void updateUser(int id, String name){
        writeLock.lock();
        if(cash.containsKey(id)) {
            cash.put(id, name);
            System.out.println("new name " + name + " id " + id);
        }
        writeLock.unlock();
    }

    public void clearCash(){
        writeLock.lock();
        cash.clear();
        writeLock.unlock();
    }


}
