package ru.job4j.resourcesynchronization;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    private static class ThreadAdd implements Runnable {
        private final UserStore store;

        private ThreadAdd(UserStore store) {
            this.store = store;
        }

        @Override
        public void run() {
            User userOne = new User(1, 200);
            User userTwo = new User(2, 100);
            this.store.add(userOne);
            this.store.add(userTwo);
        }
    }

    private static class ThreadDelete implements Runnable {
        private final UserStore store;

        private ThreadDelete(UserStore store) {
            this.store = store;
        }


        @Override
        public void run() {
            User userOne = new User(1, 200);
            User userTwo = new User(2, 100);
            this.store.add(userOne);
            this.store.add(userTwo);
            this.store.delete(userOne);
        }
    }

    private static class ThreadTransfer implements Runnable {
        private final UserStore store;

        public ThreadTransfer(UserStore store) {
            this.store = store;
        }

        @Override
        public void run() {
            User userOne = new User(1, 200);
            User userTwo = new User(2, 100);
            this.store.add(userOne);
            this.store.add(userTwo);
            this.store.transfer(2, 1, 30);
        }
    }



    @Test
    public void whenAddUser() throws InterruptedException {
        UserStore s = new UserStore();
        Thread first = new Thread(new ThreadAdd(s));
        Thread second = new Thread(new ThreadAdd(s));
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(s.getUsers().get(1).getAmount(), is(200));
    }

    @Test
    public void whenTransferThenNewAmount() throws InterruptedException {
        UserStore s = new UserStore();
        Thread first = new Thread(new ThreadTransfer(s));
        Thread second = new Thread(new ThreadTransfer(s));
        Thread third = new Thread(new ThreadTransfer(s));
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        assertThat(s.getUsers().get(1).getAmount(), is(230));
        assertThat(s.getUsers().get(2).getAmount(), is(70));

    }

    @Test
    public void whenDeleteUser() throws InterruptedException {
        UserStore s = new UserStore();
        Thread first = new Thread(new ThreadDelete(s));
        Thread second = new Thread(new ThreadDelete(s));
        Thread third = new Thread(new ThreadDelete(s));
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        assertThat(s.getUsers().size(), is(1));


    }
}