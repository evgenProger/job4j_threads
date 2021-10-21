package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class FileDownload implements Runnable {
    private final String url;
    private final int speed;

    public FileDownload(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream("pom_tmp.xml")) {
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
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread download = new Thread(new FileDownload(url, speed));
        download.start();
        download.join();
    }


}

