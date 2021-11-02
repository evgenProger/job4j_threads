package ru.job4j.waitnotify;

/**
 * Класс, блокирует выполнение счетчика по условию.
 */

public class CountBarrier {

    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (monitor) {
            while (count < total) {
                monitor.wait();
            }
        }
    }
}
