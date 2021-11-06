package ru.job4j.nonblockingalgoritm;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {
    @Test
    public void whenThreeThreadsIncrement() throws InterruptedException {
        CASCount count = new CASCount();

        Thread incremetOne = new Thread (
                count::increment
        );

        Thread incremetTwo = new Thread(
                count::increment
        );

        Thread incremetThree = new Thread(
                count::increment
        );

        incremetOne.start();
        incremetTwo.start();
        incremetThree.start();
        incremetOne.join();
        incremetTwo.join();
        incremetThree.join();
        assertThat(count.get(), is(4));
    }
}