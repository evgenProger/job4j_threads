package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) throws InterruptedException {
        int capacity = 3;
        while (queue.size() == capacity) {
            System.out.println("Очередь заполнина. Ждем пока освободится место.");
            wait();
        }
        System.out.println("Добавляем элемент в очередь." + queue);
        queue.add(value);
        System.out.println("Очередь после добавления" + queue);
        notifyAll();
   }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            System.out.println("Очередь пуста. Нужно подождать заполнения");
            wait();
        }
        System.out.println("Забрали элемент и место освободилось" + queue);
        return this.queue.poll();
    }
}
