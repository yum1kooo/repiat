package org.example.CollableAndFuture;

import java.util.Random;
import java.util.concurrent.*;

public class CollableFutureTaskThree {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Person person = new Person();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
            Person[] persons = new Person[20_000];
            int sum = 0;
            Random random = new Random();
            for(int i=0;i<persons.length;i++){

                persons[i] = new Person();
                //int randName = random.nextInt(person.nameMas.length);
                //int randAge = random.nextInt(100);
                //int randCity = random.nextInt(person.cityMas.length);

                //persons[i].name = person.nameMas[randName];
                //persons[i].city = person.cityMas[randCity];
                //persons[i].age = randAge;

                if(persons[i].getAge() > 50 && persons[i].getCity().equals("New York")){
                    sum++;
                    System.out.println("first callable " + persons[i].name + " " + persons[i].city + " " + persons[i].age);
                }
            }
            return sum;
            }
        };

        Future<Integer> task1 = executorService.submit(callable);
        Future<Integer> task2 = executorService.submit(callable);
        Future<Integer> task3 = executorService.submit(callable);
        Future<Integer> task4 = executorService.submit(callable);
        Future<Integer> task5 = executorService.submit(callable);

        task1.get();
        task2.get();
        task3.get();
        task4.get();
        task5.get();

        Integer sum = task1.get() + task2.get() + task3.get() + task4.get() + task5.get();
        executorService.shutdown();
        System.out.println(sum);
    }
}


class Person{
    String name;
    int age;
    String city;
    String[] nameMas = {"john", "andry", "mails", "piter", "johny"};
    String[] cityMas = {"New York" , "moscow", "chicago", "night city"};
    Random rand = new Random();
    public Person(){
        this.name = nameMas[rand.nextInt(nameMas.length)];
        this.city = cityMas[rand.nextInt(cityMas.length)];
        this.age = rand.nextInt(100);
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = nameMas[rand.nextInt(nameMas.length)];
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = rand.nextInt(100);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = cityMas[rand.nextInt(cityMas.length)];
    }
}