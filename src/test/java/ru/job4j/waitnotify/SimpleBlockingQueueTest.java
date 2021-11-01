package ru.job4j.waitnotify;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueueTest {

    private static class ThreadProducer implements Runnable {

        private final  SimpleBlockingQueue<Integer> simpleBlockingQueue;

        private ThreadProducer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
            this.simpleBlockingQueue = simpleBlockingQueue;
        }


        @Override
        public void run() {
            for (int i = 1; i < 16; i += 3) {
                simpleBlockingQueue.offer(i);
                System.out.println("выполняется производитель");
            }
        }
    }

    private static class ThreadConsumer implements Runnable {

        private final SimpleBlockingQueue<Integer> simpleBlockingQueue;

        private ThreadConsumer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
            this.simpleBlockingQueue = simpleBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 1; i < 50; i += 3) {
                simpleBlockingQueue.poll();
                System.out.println("Выполняется потребитель");
            }
        }
    }

    @Test
    public void whenTwoThreads() throws InterruptedException {
        Thread producer = new Thread(new ThreadProducer(new SimpleBlockingQueue<Integer>()));
        Thread consumer = new Thread(new ThreadConsumer(new SimpleBlockingQueue<Integer>()));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

}