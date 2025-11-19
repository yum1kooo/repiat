package org.example.CashDataBase;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserCash {
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    Lock readLock = rwl.readLock();
    Lock writeLock = rwl.writeLock();
    HashMap<Integer, String> cash = new HashMap<>();
    Random rand = new Random();
    int countIdUser = 13;
    int countUser = 13;
        int[] idUser = new int[countIdUser];
        String[] user = new String[countUser];

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


        for (int i = 0; i < idUser.length; i++) {
            idUser[i] = i;
        }
        for(int i = 0; i < user.length; i++){
            user[i] = cash.get(idUser[i]);
        }
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


    public void userCashAdd(int id, String name){
        writeLock.lock();
        id = idUser[rand.nextInt(countIdUser)];
        name = user[rand.nextInt(user.length)];
        try{
            cash.put(id, name);
            System.out.println("new name after clear cash " + name + " " + id);
        } finally {
            writeLock.unlock();
        }
    }

    public void updateUser(int id, String name){
        writeLock.lock();
        try {
        if(cash.containsKey(id)) {
            cash.put(id, name);
            System.out.println("new name " + name + " id " + id);
        }
        } finally {
            writeLock.unlock();
        }
    }

    public void clearCash(){
        writeLock.lock();
        System.out.println("cache clearing");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cash.clear();
        System.out.println("cache cleared");
        writeLock.unlock();
    }


}
