package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] objects;
    private final int from;
    private final int to;
    private final  T element;

    public ParallelIndexSearch(T[] objects, int from, int to, T element) {
        this.objects = objects;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    public int search() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (objects[i].equals(this.element)) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return this.search();
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch leftSearch = new ParallelIndexSearch(objects, from, mid, element);
        ParallelIndexSearch rightSearch = new ParallelIndexSearch(objects, mid + 1, to, element);
        leftSearch.fork();
        rightSearch.fork();
        int leftIndex = (int) leftSearch.join();
        int rightIndex = (int) rightSearch.join();
        return Math.max(leftIndex, rightIndex);
    }

    public int find() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return ((int) forkJoinPool.invoke(new ParallelIndexSearch(objects, 0, objects.length - 1, element)));
    }
}
