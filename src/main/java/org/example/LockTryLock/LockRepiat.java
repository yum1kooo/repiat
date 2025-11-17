package org.example.LockTryLock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockRepiat {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Inc inc = new Inc();
        inc.firstThread();
        inc.secondThread();

        System.out.println(inc.getInc());
    }
}

class Inc{
    int count = 0;
    Lock lock = new ReentrantLock();

    public void increment (){
        for(int i = 0; i < 20000; i++)
        count++;
    }


    public void firstThread() { // тестовый метож
        while (true) {
            if (lock.tryLock()) {
                try {
                    increment();
                    System.out.println("1 поток завершил запись");
                    break;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("1 поток ждёт...");
                try {
                    Thread.sleep(10); // Короткая пауза
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void exitFromDeadLock(Lock lock1, Lock lock2){ //выход из deadlock
        boolean lock1Result = lock1.tryLock();
        boolean lock2Result = lock2.tryLock();

        while(true){
            try {
                lock1Result = lock1.tryLock();
                lock2Result = lock2.tryLock();
            } finally {
                if (lock1Result){
                    lock1.unlock();
                }
                if (lock2Result){
                    lock2.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(lock1Result && lock2Result){
                return;
            }
            }
        }
    }


    public void secondThread() { // тестоый метод
        // Пытаемся получить блокировку пока не получится
        while (true) {
            if (lock.tryLock()) {
                try {
                    increment();
                    System.out.println("2 поток завершил запись");
                    break; // Выходим из цикла после выполнения
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("2 поток ждёт...");
                try {
                    Thread.sleep(10); // Короткая пауза
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        public int getInc(){
            return count;
        }
}