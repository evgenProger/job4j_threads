package ru.job4j.nonblockingalgoritm;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private int number = 1;
    private final AtomicReference<Integer> count = new AtomicReference<>(number);

    public void increment() {
        if(!(count.compareAndSet(number, number++))) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
    }

    public int get() {
        int result = count.get();
        if (result == number) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        return result;
    }
}
