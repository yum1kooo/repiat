package org.example.StreamAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StreamTaskOne {
    public static void main(String[] args) {
        Product product = new Product("asd", " ", 11);
        Map<Product, Integer> pr = new HashMap<>();
        pr.put(new Product("rtx 5070", "videocard", 53000), 1);
        pr.put(new Product("banana", "food", 10), 500);
        pr.put(new Product("TV", "electronic", 53000), 1);
        pr.put(new Product("TV", "electronic", 53000), 1);
        System.out.println(pr.size());
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
}


