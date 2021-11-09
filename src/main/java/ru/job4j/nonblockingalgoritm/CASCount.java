package ru.job4j.nonblockingalgoritm;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int number = 0;
        count.set(number);
        do {
           number = count.get();
           System.out.println("счетчик равен" + " " + number);
        } while (count.compareAndSet(number, number + 1));
    }

    public int get() {
        return count.get();
    }
}

