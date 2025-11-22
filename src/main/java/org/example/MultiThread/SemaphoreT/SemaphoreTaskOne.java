package org.example.MultiThread.SemaphoreT;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTaskOne {
    public static void main(String[] args) throws InterruptedException {
        ParkingLogic parkingLogic = new ParkingLogic();
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        parkingLogic.park();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


        executorService.shutdown();

    }
}

class ParkingLogic{
    Semaphore semaphore = new Semaphore(4, true);
        String[] cars = {
                "BMW M3",
                "Audi A6",
                "Mercedes C200",
                "Toyota Supra",
                "Nissan Skyline",
                "Honda Civic",
                "Ford Mustang",
                "Chevrolet Camaro",
                "Porsche 911",
                "Lamborghini Huracan",
                "Ferrari F8",
                "Kia Stinger",
                "Hyundai Sonata",
                "Volkswagen Golf",
                "Subaru Impreza"
        };
        Random rand = new Random();

    public ParkingLogic(){
    }

    public void park() throws InterruptedException {
        String carName = cars[rand.nextInt(cars.length)];
                semaphore.acquire();
        try {
                System.out.println("Машина " + carName + " заехала на парковку " + " свободных мест " + semaphore.availablePermits());
                Thread.sleep(rand.nextInt(rand.nextInt(1000)));
        } catch(InterruptedException e){
            e.printStackTrace();
        } finally{
            System.out.println("Машина " + carName + " уехала с парковки всего свободных мест " + semaphore.availablePermits());
            semaphore.release();
        }
    }
}
