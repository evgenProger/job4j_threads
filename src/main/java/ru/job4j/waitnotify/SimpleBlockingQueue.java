package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private  int capacity = 3;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() <= capacity) {
            queue.add(value);
            System.out.println("Добавляем элемент");
        }
        System.out.println("производитель заполнил очередь" + queue);
        wait();


   }

    public synchronized T poll() throws InterruptedException {
        T val;
        while (this.queue.isEmpty()) {
            System.out.println("Очередь пуста. Нужно подождать заполнения");
            wait();
        }
        val = this.queue.poll();
        System.out.println("Место освободилось" + queue);
        notify();
        return val;
    }

    public synchronized Queue<T> getQueue() {
        return queue;
    }
}
