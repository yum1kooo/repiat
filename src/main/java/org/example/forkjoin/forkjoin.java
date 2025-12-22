package org.example.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class forkjoin {

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(); //создаем fjp для запуска задачи

        int[] arr = new int[100_000]; //создаем массив с которым мы будем работать и заполняем его
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        Action task = new Action(arr, 0, arr.length); // создаем экземпляр класса action и в него передаем массив с которым работаем а так же границы работы

        int result = fjp.invoke(task);  // для форк джоина дает задачу
        System.out.println(result); // выводим результат
        System.out.println("чтоasd");

    }
}

class Action extends RecursiveTask<Integer> {

    private int[] arr;  // массив для работы
    private int from; //точка старта при работе fjp
    private int to; // конец

    public Action(int[] arr, int from, int to) { // дефолтный конструктор
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() { // метод унаследованный от RecursiveTask
        if (to - from <= 10_000) { //если длина меньше 10000 то задачу мы не делим
            int sum = 0; //сумма элементов текущего диапазона
            for (int i = from; i < to; i++) {
                if (i % 2 == 0) {
                    sum++;
                }
            }
            return sum;
        }
        int mid = (from + to) / 2; // находим середину массива

        Action left = new Action(arr, from, mid); //делим задачу на 2 части передаем массив и от начала до середины диапозон работы left части
        Action right = new Action(arr, mid, to); // и тут тоже самое только от середины и то конца

        left.fork(); // отдаем в пул потоков
        int rightResult = right.compute(); // правую часть считаем сами
        int leftResult = left.join(); // левая ожидает правую
        return rightResult + leftResult; // и тут просто соединяем их
    }

}
