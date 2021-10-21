package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] symbols = new String[] {"\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            for (String str: symbols) {
                System.out.print("\rLoad :" + " " + str);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();

    }
}
