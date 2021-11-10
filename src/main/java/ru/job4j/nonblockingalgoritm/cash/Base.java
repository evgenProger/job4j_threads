package ru.job4j.nonblockingalgoritm.cash;

public class Base {
    private final int id;
    private final int version;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }
}
