package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParallelIndexSearchTest {

    @Test
    public void whenArrayLessTenThenFindIndex() {
        Integer[] arr = new Integer[]{5, 7, 1, 25, 89, 14};
        int from = 0;
        int to = arr.length - 1;
        ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(arr, from, to, 1);
        assertThat(search.find(), is(2));
    }

    @Test
    public void whenArrayMoreTenThenFindIndex() {
        Integer[] arr = new Integer[] {5, 7, 1, 25, 89, 14, 47, 68, 51, 11, 24, 78, 98, 74};
        int from = 0;
        int to = arr.length - 1;
        ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(arr, from, to, 51);
        assertThat(search.find(), is(8));

    }

    @Test
    public void whenFindFirstIndex() {
        Integer[] arr = new Integer[] {5, 7, 1, 25, 89, 14, 47, 68, 51, 11, 24, 78, 98, 74};
        int from = 0;
        int to = arr.length - 1;
        ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(arr, from, to, 5);
        assertThat(search.find(), is(0));

    }

    @Test
    public void whenFindLastIndex() {
        Integer[] arr = new Integer[] {5, 7, 1, 25, 89, 14, 47, 68, 51, 11, 24, 78, 98, 74};
        int from = 0;
        int to = arr.length - 1;
        ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(arr, from, to, 74);
        assertThat(search.find(), is(13));
    }

    @Test
    public void whenHasNotObject() {
        Integer[] arr = new Integer[] {5, 7, 1, 25, 89, 14, 47, 68, 51, 11, 24, 78, 98, 74};
        int from = 0;
        int to = arr.length - 1;
        ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(arr, from, to, 101);
        assertThat(search.find(), is(-1));
    }
}

