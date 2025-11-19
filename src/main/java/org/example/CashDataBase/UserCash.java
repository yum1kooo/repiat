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
    // весь класс написан по тз под appcashSystem для использования random
    int countIdUser = 13;  //сделанно для того что бы код не сыпался когда мы меняем длину массива, с этим полем везде меняеться длина
    int countUser = 13; //сделанно для того что бы код не сыпался когда мы меняем длину массива, с этим полем везде меняеться длина
        int[] idUser = new int[countIdUser];  // храним первые id из map
        String[] user = new String[countUser]; //храним первые имена из map

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
            idUser[i] = i; // добавляем в массив idUser столько id сколько в поле countIdUser (ну и соответственно добавляеться в массив)
        }
        for(int i = 0; i < user.length; i++){
            user[i] = cash.get(idUser[i]); // для user[i] = берем из map по ключу имя пользователя (ну и соответственно добавляеться в массив)
        }
    }


    public String getUser(int id){
        readLock.lock();
        try {
        if(cash.containsKey(id)) {
            return cash.get(id); // по ключу возвращаем имя
        }
        } finally {
            readLock.unlock();
        }
        return null;
    }


    public void userCashAdd(int id, String name){
        writeLock.lock();
        id = idUser[rand.nextInt(countIdUser)]; //после очистки "кеша" берем рандомный id из массива
        name = user[rand.nextInt(user.length)]; // берем рандомное имя по id из массива по индексу
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
