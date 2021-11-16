package ru.job4j.pool;

import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);

    public ThreadPool() {
        for (int i = 0; i < 5; i++) {
            try {
                work(new Job());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads.add(thread);

        }
        for (Thread t: threads) {
            while (!t.isInterrupted()) {
                t.start();
                try {
                    shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() throws InterruptedException {
        for (Thread t: threads) {
          t.interrupt();
        }
    }

    public static class Job implements Runnable {

        @Override
        public void run() {
            System.out.println("Задача выполнена");
        }
    }


    public static void main(String[] args) {
        new ThreadPool();
    }
}
