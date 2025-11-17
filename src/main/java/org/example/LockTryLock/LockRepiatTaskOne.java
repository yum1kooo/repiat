package org.example.LockTryLock;

import java.util.concurrent.ExecutionException;
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
    int count = 0;
    Lock lock = new ReentrantLock();

    public void increment() {
        for (int i = 0; i < 20000; i++)
            count++;
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
                    return;
                }
                if(lock1Lock){
                    lock1.unlock();
                }
                if(lock2Lock){
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
        while (true) {
            if (lock.tryLock()) {
                    try {
                    increment();
                    System.out.println("запись в 1 поток прошла");
                    break;
                    } finally {
                        lock.unlock();
                    }
            } else {
                Thread.sleep(1);
                System.out.println("Поток занят 2 потоком");
            }
        }
    }

    public void secondThread() throws InterruptedException {
        while (true) {
            if (lock.tryLock()) {
                try {
                    increment();
                    System.out.println("запись во 2 поток прошла");
                    break;
                } finally {
                    lock.unlock();
                }
            } else {
                Thread.sleep(1);
                System.out.println("Поток занят 1 потоком");
            }
        }
    }

    public int getInc() {
        return count;
    }
}