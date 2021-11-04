package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void offer(T value) throws InterruptedException {

        while (queue.size() == capacity) {
            System.out.println("The Queue is full. Waiting when to free up space.");
            wait();
        }
        System.out.println("Adding object into the queue." + queue);
        queue.add(value);
        System.out.println("The Queue after add" + queue);
        notifyAll();
   }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            System.out.println("The Queue is empty. Need to wait when add");
            wait();
            notifyAll();
        }
        System.out.println("Poll the object and the space is vacated" + queue);
        return this.queue.poll();
    }

    public synchronized Queue<T> getQueue() {
        return queue;
    }
}
