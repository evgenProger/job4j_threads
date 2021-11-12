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
        Base base = memory.computeIfPresent(model.getId(), (id, base1) -> {
            if (model.getVersion() != base1.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            return new Base(id, base1.getVersion() + 1);
        });
        return base != null;
    }

    public void delete(Base model) {
        this.memory.remove(model.getId());
    }

    public Base get(int key) {
        return this.memory.get(key);
    }
}
