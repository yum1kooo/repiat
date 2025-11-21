package org.example.SemaphoreT;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTaskTwo {
    public static void main(String[] args) {
            Server server = new Server();
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    try {
                        server.work(rand.nextInt(33));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executorService.shutdown();


    }
}


class Server {
    Semaphore semaphore = new Semaphore(3, true);
    Random rand = new Random();


    public void work(int id) throws InterruptedException {
        System.out.println("Пользователь " + id + " ждет в очереди");
        semaphore.acquire(); // никогда не вставляем acquire в блок try catch если мы напишем в него то у нас появиться еще 1 место
        try{
            System.out.println("Пользователь " + id + " подключился " +" доступно слотов " + semaphore.availablePermits());
            Thread.sleep(1000 + rand.nextInt(2000));
        } finally{
            semaphore.release();
            System.out.println("Пользователь " + id + " отключился");
        }
    }
}