package ru.job4j.waitnotify;

import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

@Ignore
public class SimpleBlockingQueueTest {

    private static class ThreadProducer implements Runnable {

        private final  SimpleBlockingQueue<Integer> simpleBlockingQueue;

        private ThreadProducer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
            this.simpleBlockingQueue = simpleBlockingQueue;
        }

        @Override
        public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                        simpleBlockingQueue.offer(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                try {
                   for (int i = 0; i < 3; i++) {
                       simpleBlockingQueue.poll();
                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    @Test
    public void whenTwoThreads() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<Integer>();
        Thread producer = new Thread(new ThreadProducer(simpleBlockingQueue));
        Thread consumer = new Thread(new ThreadConsumer(simpleBlockingQueue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

}