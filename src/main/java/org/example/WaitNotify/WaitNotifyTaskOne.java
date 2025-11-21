package org.example.WaitNotify;

import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyTaskOne {
    public static void main(String[] args) throws InterruptedException {
        ProduceConsume pr = new ProduceConsume();
        Random rand = new Random();



        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        pr.produce(1, "TEST ");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


        for (int i = 0; i < 10; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        pr.consume(33);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executor.shutdown();
    }
}

class ProduceConsume{
    TreeMap<Integer, String> warehouse = new TreeMap<>();
    int maxPlace = 4;
    // заполняем maxPlace до 0 когда она равен 0 тогда ждем
    public synchronized void produce(int id, String name) throws InterruptedException {
        while (maxPlace == 0) { // while что бы случайно не вызвать wait
            wait();
        }
        System.out.println("Товар " + name + "приехал на склад");
        Thread.sleep(500);
        maxPlace--;
        System.out.println("Товар положили на полку " + id + " кол-во доступных полок " + maxPlace);
        warehouse.put(id, name);
        notifyAll();

    }


    public synchronized void consume(int id) throws InterruptedException {
        // и тут если maxPlace == 4 тогда ждем пока не появяться еще товары
        while (maxPlace == 4) { // while что бы случайно не вызвать wait
            wait();
        }
        Thread.sleep(1000);
        System.out.println("Забираю товар с полки " + id);
        warehouse.remove(id);
        maxPlace++;
        System.out.println("свободных полок " + maxPlace);
        notify();
    }
}
