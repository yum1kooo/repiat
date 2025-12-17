package org.example.multithreadPlusStreamAPI;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class mtPlusSP {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> words = List.of(
                "солнечный", "день", "маленький", "город", "проснулся", "рано", "утром",
                "птицы", "пели", "деревьях", "прохожие", "спешили", "делам", "площадь",
                "собирались", "торговцы", "предлагая", "свежие", "фрукты", "овощи",
                "дети", "играли", "фонтан", "смех", "разносился", "улицах", "старый",
                "художник", "мольбертом", "ищущий", "вдохновение", "воздухе", "аромат",
                "хлеба", "пекарни", "дома", "скрывали", "фасадами", "истории", "тайны",
                "солнце", "поднималось", "выше", "освещая", "мостовые", "крыши", "кошки",
                "лениво", "растягивались", "подоконниках", "наблюдая", "прохожими",
                "жизнь", "города", "текла", "неспешно", "даря", "радость", "уют",
                "каждому", "птицы", "солнце", "дети", "город", "улицах", "смех",
                "старый", "художник", "мостовые", "площадь", "торговцы", "играли",
                "фонтан", "воздухе", "аромат", "овощи", "фрукты", "хлеба", "пекарни",
                "дома", "крыши", "подоконниках", "наблюдая", "прохожими", "радость",
                "уют", "каждому", "солнечный", "день", "маленький", "город", "проснулся",
                "рано", "утром", "птицы", "пели", "деревьях", "прохожие", "спешили",
                "делам", "площадь", "собирались", "торговцы", "предлагая", "свежие",
                "фрукты", "овощи", "дети", "играли", "фонтан"
        );
        Map<String, Long> collect = words.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        int wordsSizePartOne = words.size() / 5;

        int wordsSizePartTwo = words.size() / 5 + wordsSizePartOne;

        int wordsSizePartThree = words.size() / 5 + wordsSizePartTwo;

        int wordsSizePartFour = words.size() / 5 + wordsSizePartThree;

        int wordsSizePartFive = words.size() / 5 + wordsSizePartFour;


        ExecutorService ex = Executors.newFixedThreadPool(5);

        Callable<Map<String, Long>> one = new Callable<>() {
            @Override
            public Map<String, Long> call() throws Exception {
                int sum = 0;
                Map<String, Long> collect = words.stream()
                        .limit(wordsSizePartOne)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
                return collect;
            }
        };
        Callable<Map<String, Long>> two = new Callable<>() {
            @Override
            public Map<String, Long> call() throws Exception {
                int sum = 0;
                Map<String, Long> collect = words.stream()
                        .skip(wordsSizePartOne)
                        .limit(wordsSizePartTwo)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
                return collect;
            }
        };
        Callable<Map<String, Long>> three = new Callable<>() {
            @Override
            public Map<String, Long> call() throws Exception {
                int sum = 0;
                Map<String, Long> collect = words.stream()
                        .skip(wordsSizePartTwo - 1)
                        .limit(wordsSizePartThree)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
                return collect;
            }
        };
        Callable<Map<String, Long>> four = new Callable<>() {
            @Override
            public Map<String, Long> call() throws Exception {
                int sum = 0;
                Map<String, Long> collect = words.stream()
                        .skip(wordsSizePartThree - 1)
                        .limit(wordsSizePartFour)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
                return collect;
            }
        };
        Callable<Map<String, Long>> five = new Callable<>() {
            @Override
            public Map<String, Long> call() throws Exception {
                int sum = 0;
                Map<String, Long> collect = words.stream()
                        .skip(wordsSizePartFour - 1)
                        .limit(wordsSizePartFive)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
                return collect;
            }
        };

        Future<Map<String, Long>> f1 = ex.submit(one);
        long oneFuture = f1.get().size();

        Future<Map<String, Long>> f2 = ex.submit(two);
        long twoFuture = f2.get().size();

        Future<Map<String, Long>> f3 = ex.submit(three);
        long threeFuture = f3.get().size();

        Future<Map<String, Long>> f4 = ex.submit(four);
        long fourFuture = f4.get().size();

        Future<Map<String, Long>> f5 = ex.submit(five);
        long fiveFuture = f5.get().size();

        long sum = oneFuture + twoFuture + threeFuture;
        System.out.println(sum);

        System.out.println(wordsSizePartOne + " one");
        System.out.println(wordsSizePartTwo + " two");
        System.out.println(wordsSizePartThree + " three");
        System.out.println(wordsSizePartFour + " four");
        System.out.println(wordsSizePartFive + " five");


        ex.shutdown();
    }
}
