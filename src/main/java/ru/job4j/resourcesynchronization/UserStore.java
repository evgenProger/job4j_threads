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
       return users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
       return this.users.replace(user.getId(), user) == null;
    }

    public synchronized boolean delete(User user) {
        return this.users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom != null && userTo != null && userFrom.getAmount() > userTo.getAmount()) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            result = true;
        }
        return result;
    }

    public synchronized Map<Integer, User> getUsers() {
        return users;
    }
}
