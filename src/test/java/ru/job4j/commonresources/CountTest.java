package ru.job4j.commonresources;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CountTest {
    /**
     * Класс описывает нить со счетчиком
     */
    private class ThreadCount extends Thread {

        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new Thread(new ThreadCount(count));
        Thread second = new Thread(new ThreadCount(count));
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.getValue(), is(2));
    }

}