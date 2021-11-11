package ru.job4j.resourcesynchronization;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rs1 = new TreeSet<>();
        list.iterator().forEachRemaining(rs1::add);
        assertThat(rs1, is(Set.of(1, 2)));
    }

    @Test
    public void getValue() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        Thread third = new Thread(() -> list.get(1));
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        int val = list.get(1);
        assertThat(val, is(2));
    }

}