package ru.job4j.commonresources.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveToFile {

    private final File target;

    public SaveToFile(File target) {
        this.target = target;
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(target);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
