package ru.job4j.resourcesynchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final List<T> list;


    public SingleLockList(List<T> list) {
        this.list = list;
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    public synchronized List<T> copy(List<T> list) {
        List<T> backup = new ArrayList<>(this.list.size());
        for (T value: list) {
            backup.add(value);
        }
        return backup;
    }



    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
