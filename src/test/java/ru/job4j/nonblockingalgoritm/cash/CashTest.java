package ru.job4j.nonblockingalgoritm.cash;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CashTest {
    @Test
    public void whenAdd() throws InterruptedException {
        Base baseOne = new Base(2, 1255);
        Base baseTwo = new Base(3, 187);
        Cash cash = new Cash();
        Thread threadOne = new Thread(
                () -> cash.add(baseOne)

        );
        Thread threadTwo = new Thread(
                () -> cash.add(baseTwo)
        );
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        assertThat(cash.get(3).getVersion(), is(187));
    }

    @Test
    public void whenUpdateThenNewVersion() throws InterruptedException {
        Base baseOne = new Base(2, 1255);
        Base baseTwo = new Base(3, 187);
        Cash cash = new Cash();
        cash.add(baseOne);
        cash.add(baseTwo);
        Thread threadOne = new Thread(
                () -> cash.update(baseTwo)
        );
        threadOne.start();
        threadOne.join();
        assertThat(cash.get(3).getVersion(), is(188));
    }

    @Test
    public void whenDelete() throws InterruptedException {
        Base baseOne = new Base(2, 1255);
        Base baseTwo = new Base(3, 187);
        Cash cash = new Cash();
        cash.add(baseOne);
        cash.add(baseTwo);
        Thread threadOne = new Thread(
                () -> cash.delete(baseTwo)
        );
        threadOne.start();
        threadOne.join();
        assertNull(cash.get(3));
    }
}