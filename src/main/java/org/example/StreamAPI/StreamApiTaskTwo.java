package org.example.StreamAPI;

import java.util.*;
import java.util.stream.Collectors;

public class StreamApiTaskTwo {
    public static void main(String[] args) {

        System.out.println("Task one");
        List<Integer> one = List.of(1,2,3,4,5,6,7,8,9,10);
        int count = one.stream()
                .filter(x -> x % 2 == 0) //фильтруем под требования
                .mapToInt(a -> a) //преобразуем поток в числа
                .sum(); //считаем
        System.out.println(count);


        System.out.println("task two");
        List<String> two = List.of("will", "joes", "evelen", "zhrini");
        List<String> filterTwo = two.stream()
                .filter(x -> x.length() > 4) // фильтруем по имени у кого больше 4 букв
                .toList(); //в лист
        System.out.println(filterTwo);


        System.out.println("task three");
        List<String> three = List.of("will", "joes", "evelen", "zhrini", "zhrini", "will");
        List<String> threeSorted = three.stream()
                .distinct() //удаляем дубликаты
                .sorted() //сортируем по алфавитному порядку (по умолчанию сортируеться так)
                .toList();
        System.out.println(threeSorted);


        System.out.println("task four");
        List<User> four = List.of(new User("anton", 33, 0), new User("will", 15, 0));
        List<User> listFourSorted = four.stream()
                .filter(x -> x.getAge() > 18) //фильтруем у кого возраст больше 18
                .toList();
        System.out.println(listFourSorted);


        System.out.println("task five");
        List<User> five = List.of(new User("anton", 33, 28000), new User("will", 15, 15000), new User("Bill", 55, 35000));
        Optional<User> a = five.stream()
                .max(Comparator.comparing(User::getSalary)); //берем макс значения из поля с зп
        System.out.println(a);


        System.out.println("task six");
        String[] six = {"apple","banana","apricot","blueberry","avocado"};
        Map<Character, List<String>> resSix = Arrays.stream(six)
                .collect(Collectors.groupingBy(s -> s.charAt(0))); // сортируем по первой букве в имени в (0) это нулевой индекс т.е 1 буква слова
        for(Map.Entry<Character, List<String>> sixx : resSix.entrySet()){
            System.out.println(sixx.getKey() + " - key " + sixx.getValue() + " - value");
        }


        System.out.println("task seven");
        List<User> seven = List.of(new User("anton", 33, 28000), new User("will", 15, 15000), new User("Bill", 55, 35000));
        LinkedHashMap<String, Long> collect = seven.stream()
                .collect(Collectors.groupingBy(User::getName, Collectors.summarizingInt(x -> x.salary * x.getAge())))//ключ имя, значение статистика по имени(макс мин ср)
                .entrySet().stream() //превращаем map в поток пар
                .collect(Collectors.toMap(
                        Map.Entry::getKey,// ключ — имя
                        e -> e.getValue().getSum(),   // значение — сумма
                        (z, b) -> z,                  // merge function (если вдруг ключи совпадут)
                        LinkedHashMap::new            // LinkedHashMap
                ));
        for(Map.Entry<String, Long> sevenForEach : collect.entrySet()) {
            System.out.println("key -> " + sevenForEach.getKey() + " value -> " + sevenForEach.getValue());
        }


        System.out.println("task nine");
        String[] wordNine = {"arbyz", "watermelon", "apple", "banana", "apple", "apple", "arbyz", "watermelon", "kiwi"};
        int nineCounter = 0;
        Map<String, Long> nineMap = Arrays.stream(wordNine)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting())); //если x совпадает с существующем x тогда срабатывает counting
        for(Map.Entry<String, Long> niine : nineMap.entrySet()){
            System.out.println("key -> " + niine.getKey() + " value -> " + niine.getValue());
        }
    }
}

class User {
    String name;
    int age;
    int salary;

    public User(String name, int age, int salary){
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "user " + name + " age " + age + " salary " + salary;
    }
}