package ru.job4j.nonblockingalgoritm;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private int number = 0;
    private final AtomicReference<Integer> count = new AtomicReference<>(number);

    public void increment() {
        do {
           number = count.get();
        } while (count.compareAndSet(number, number + 1));
    }

    public int get() {
        return count.get();
    }

}

