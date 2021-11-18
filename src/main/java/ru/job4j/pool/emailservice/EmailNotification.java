package ru.job4j.pool.emailservice;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getUsername());
        String email = user.getEmail();
        pool.execute(() -> {
            EmailNotification emailNotification = new EmailNotification();
            emailNotification.send(subject, body, email);
        });
    }

    public void close() {
       pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

}

