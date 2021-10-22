package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class FileDownload implements Runnable {
    private final String url;
    private final int speed;
    private final String target;

    public FileDownload(String url, int speed, String target) {
        this.url = url;
        this.speed = speed;
        this.target = target;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(target)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, 1024);
                long finish = System.currentTimeMillis();
                long differentTime = finish - start;
                start = System.currentTimeMillis();
                if (speed > differentTime) {
                    Thread.sleep(speed - differentTime);
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 3) {
            throw new IllegalArgumentException("No arguments found");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String target = args[2];

        Thread download = new Thread(new FileDownload(url, speed, target));
        download.start();
        download.join();
    }
}

