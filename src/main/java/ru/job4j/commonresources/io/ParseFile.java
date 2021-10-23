package ru.job4j.commonresources.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {

    private final SaveToFile save;
    private final File file;

    public ParseFile(SaveToFile save, File file) {
        this.save = save;
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        int data;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            while((data = in.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }
}
