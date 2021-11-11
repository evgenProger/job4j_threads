package ru.job4j.nonblockingalgoritm.cash;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Cash {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return this.memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base base = this.memory.computeIfPresent(model.getId(),
                (key, value) -> new Base(key, value.getVersion() + 1));
        if (base == null) {
            throw new OptimisticException("Versions aren't equals");
        }
        return true;
    }

    public void delete(Base model) {
        this.memory.remove(model.getId());
    }

    public Base get(int key) {
        return this.memory.get(key);
    }

}
