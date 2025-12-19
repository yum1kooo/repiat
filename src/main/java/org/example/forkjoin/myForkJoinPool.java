package org.example.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class myForkJoinPool {
    public static void main(String[] args) {
            int[] arr = new int[100_000];
            for (int i = 0; i < arr.length; i++){
                arr[i] = i;
            }

            Fork f = new Fork(arr, 0, arr.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
            int result = forkJoinPool.invoke(f);
        System.out.println(result);
    }
}

class Fork extends RecursiveTask<Integer>{
    private int[] arr;
    private int from;
    private int to;


    public Fork(int[] arr, int from, int to){
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10_000){
            int sum = 0;
            int count = 0;
            for (int i = from; i < to; i++){
                if(i % 3 == 0){
                    sum++;
                }
            }
            return sum;
        }

        int mid = (from + to) / 2;

        Fork left = new Fork(arr, from, mid);
        Fork right = new Fork(arr, mid, to);

        left.fork();
        int rightResult = right.compute();
        int leftResult = left.join();
        return rightResult +  leftResult;
    }
}
