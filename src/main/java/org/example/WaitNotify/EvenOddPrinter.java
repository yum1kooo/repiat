package org.example.WaitNotify;

import java.util.concurrent.ArrayBlockingQueue;

public class EvenOddPrinter {
    public static void main(String[] args) throws InterruptedException {
            printer printer = new printer();

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        printer.even();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.odd();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });



            t1.start();
            t2.start();
            t1.join();
            t2.join();
    }
}

class printer {
    int[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    private final int LIMIT = 20;
    public synchronized void even() throws InterruptedException {
        for (int i = 0; i <= value.length; i++) {
            if (i % 2 == 0) {
                notify();
                System.out.println("Print even number " + i);
                wait();
            }
        }
    }

    public synchronized void odd() throws InterruptedException {
        for (int i = 0; i <= value.length; i++) {
            if (i == 20) {
                notify();
            }

            if (i % 2 != 0) {
                notify();
                System.out.println("Print odd number " + i);
                wait();
           }
        }
    }

}
