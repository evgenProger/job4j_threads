package ru.job4j.nonblockingalgoritm;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class CASCountTest {
    @Ignore
    @Test
    public void whenThreeThreadsIncrement() throws InterruptedException {
        CASCount count = new CASCount();

        Thread incrementOne = new Thread(
                count::increment
        );

        Thread incrementTwo = new Thread(
                count::increment
        );

        Thread incrementThree = new Thread(
                count::increment
        );

        incrementOne.start();
        incrementTwo.start();
        incrementThree.start();
        incrementOne.join();
        incrementTwo.join();
        incrementThree.join();
        assertThat(count.get(), is(3));
    }

}