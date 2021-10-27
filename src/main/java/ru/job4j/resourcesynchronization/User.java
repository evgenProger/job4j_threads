package ru.job4j.resourcesynchronization;

public class User {
    public void setId(int id) {
        this.id = id;
    }

    private  int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
