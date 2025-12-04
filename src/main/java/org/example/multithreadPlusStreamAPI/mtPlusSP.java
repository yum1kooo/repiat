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
        System.out.println(wordsSizePartOne);

        int wordsSizePartTwo = words.size() / 5 + wordsSizePartOne;
        System.out.println(wordsSizePartTwo);

        int wordsSizePartThree = words.size() / 5 + wordsSizePartTwo;
        System.out.println(wordsSizePartThree);

        int wordsSizePartFour = words.size() / 5 + wordsSizePartThree;
        System.out.println(wordsSizePartFour);

        int wordsSizePartFive = words.size() / 5 + wordsSizePartFour;
        System.out.println(wordsSizePartFive);

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
                        .skip(wordsSizePartOne - 1)
                        .limit(wordsSizePartTwo)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
                return collect;
            }
        };

        Future<Map<String, Long>> f1 = ex.submit(one);
        long oneFuture = f1.get().size();
        Future<Map<String, Long>> f2 = ex.submit(two);
        long twoFuture = f2.get().size();

        long sum = oneFuture + twoFuture;
        System.out.println(sum);

        ex.shutdown();
    }
}
