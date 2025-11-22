package org.example.MultiThread.LockTryLock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockRepiatTaskOne {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Inc inc = new Inc();

        Thread t1 = new Thread(){
            public void run(){
                try {
                    inc.firstThread();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                try {
                    inc.secondThread();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(inc.getInc());
    }
}

class Inc{
    AtomicInteger count = new AtomicInteger(0);
    int[] mass = new int[20_100];
    Lock lock = new ReentrantLock();
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public void increment() {
        for (int i = 0; i < 20000; i++)
            count.incrementAndGet();
    }

    public void addMass() {
        for (int i = 0; i < 20000; i++)
            mass[i] += 1;
    }


    public void exitFromDeadLock(Lock lock1, Lock lock2) {
        boolean lock1Lock = false;
        boolean lock2Lock = false;

        while(true){
            try {
                lock1Lock = lock1.tryLock();
                lock2Lock = lock2.tryLock();
            } finally {
                if(lock1Lock && lock2Lock){
                    System.out.println("ОТПУСИЛ 1,2");
                    return;
                }
                if(lock1Lock){
                    System.out.println("отпустил 1");
                    lock1.unlock();
                }
                if(lock2Lock){
                    System.out.println("Отпустил 2");
                    lock2.unlock();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void firstThread() throws InterruptedException {
        exitFromDeadLock(lock1, lock2);
        try {
            increment();
            addMass();
            System.out.println("Поток 1 завершил работу, count=" + count);
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        exitFromDeadLock(lock2, lock1);
        try {
            increment();
            addMass();
            System.out.println("Поток 2 завершил работу, count=" + count);
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }


    public AtomicInteger getInc() {
        return count;
    }

    public int getMass() {
        int countMass = 0;
        for(int i = 0; i < mass.length; i++){
            countMass++;
        };
        return countMass;
    }
}