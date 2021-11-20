package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch extends RecursiveTask<Integer> {

    private final Object[] objects;
    private final int from;
    private final int to;

    public ParallelIndexSearch(Object[] objects, int from, int to) {
        this.objects = objects;
        this.from = from;
        this.to = to;
    }

    public int search(Object o) {
        int result = 0;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(o)) {
                result = i;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if (objects.length < 10) {
            this.search(new Object());
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch leftSearch = new ParallelIndexSearch(objects, from, mid);
        ParallelIndexSearch rightSearch = new ParallelIndexSearch(objects, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        int leftIndex = leftSearch.join();
        int rightIndex = rightSearch.join();
        return this.search(new Object());
    }

    public static int find(Object[] objArr) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch(objArr, 0, objArr.length - 1));
    }

}
