package org.example.StreamAPI;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTaskOne {
    public static void main(String[] args) {
        Product product = new Product("asd", " ", 11);
        Map<Product, Integer> pr = new HashMap<>();
        pr.put(new Product("rtx 5070", "videocard", 53000), 1);
        pr.put(new Product("banana", "food", 10), 500);
        pr.put(new Product("TV", "electronic", 53000), 1);
        pr.put(new Product("TV", "electronic", 53000), 1);
        System.out.println(pr.size());

        //TASK ONE
        List<String> abc  = pr.keySet()
                .stream()
                .filter(x -> x.price > 5000) //фильтруем по цене
                .filter(a -> a.getCategory().equals("electronic")) // выбираем категорию
                .map(Product::getName) // получаем имена
                .toList(); // преобразовываем в лист
        System.out.println(abc);


        pr.entrySet().stream()
                .map(a -> a.getKey().getName() + " товар " + a.getValue() + " шт")
                .sorted()
                .forEach(System.out::println);


        int sum = pr.entrySet().stream()
                .mapToInt(a -> a.getKey().getPrice() * a.getValue()) //преобразуем поток в int далее берем ключ(Produce и на этом product берем getPrice) значение это кол-во
                .sum(); // считаем сумму всех чисел
        System.out.println(sum);



        //TASK TWO
        List<Product> list = new ArrayList<>();
        list.add(new Product("banana", "food", 39));
        list.add(new Product("TV", "electronic", 5));
        list.add(new Product("iphone", "electronic", 69000));
        list.add(new Product("macbook", "electronic", 76000));
        list.add(new Product("rtx 4080", "electronic", 150000));

        //task 2.1
        Map<String, List<Product>> collect = list.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        /*
        Полученное значение (категорию) он использует как ключ в итоговой Map.
        Сам создаёт для этого ключа список, если он ещё не создан.
        Добавляет текущий продукт в этот список.
        Идёт дальше по всем элементам.
         */

        for(Map.Entry<String, List<Product>> entry : collect.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        //task 2.2
        Map<String, Long> tas = list.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));

        for(Map.Entry<String, Long> entry : tas.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        //task 2.3

        Map<String, Optional<Product>> collect1 = list.stream()  // 1 группируем по категории далее группируем по max цене в категории создаем comparator у указываем по чему будем
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.maxBy(Comparator.comparing(Product::getPrice))));  // сортировать макс цену

        for(Map.Entry<String, Optional<Product>> entry : collect1.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}


class Product {
    String name;
    String category;
    int price;


    public Product(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    @Override
    public String toString() {
        return "name " + name + ", category " + category + ", price " + price;
    }


}


