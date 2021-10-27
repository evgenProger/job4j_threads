package ru.job4j.resourcesynchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();


    public synchronized boolean add(User user) {
        users.put(user.getId(), user);
        return true;
    }

    public synchronized boolean update(User user) {
        users.put(user.getId(), user);
        return true;
    }

    public synchronized boolean delete(User user) {
        this.users.remove(user.getId());
        return true;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        this.update(userFrom);
        this.update(userTo);
        return true;
    }

    public synchronized Map<Integer, User> getUsers() {
        return users;
    }
}
