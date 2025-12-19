package org.example.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestFork {
    public static void main(String[] args) {
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++){
            arr[i] = i;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        TestForkJoin testForkJoin = new TestForkJoin(arr, 0, arr.length);
        int result = forkJoinPool.invoke(testForkJoin);
        System.out.println(result);
    }
}


class TestForkJoin extends RecursiveTask<Integer> {
    private int[] arr;
    private int from;
    private int to;


    public TestForkJoin(int[] arr, int from, int to){
        this.arr = arr;
        this.from = from;
        this.to = to;
    }


    @Override
    protected Integer compute() {
        if(to - from <= 100){
            int sum = 0;
            for(int i = from; i < to; i++){
                if(arr[i] % 2 == 0){
                    sum++;
                }
            }
            return sum;
        }

            int mid = (from + to) / 2;

            TestForkJoin left = new TestForkJoin(arr, from, mid);
            TestForkJoin right = new TestForkJoin(arr, mid, to);

            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();

        return rightResult + leftResult;
    }
}
