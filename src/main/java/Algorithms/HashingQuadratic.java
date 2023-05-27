package Algorithms;

import java.util.ArrayList;
import java.util.List;

public class HashingQuadratic<K, V> implements HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Generic<K, V>[] table;
    private int size;
    private int capacity;
    private double loadFactor;

    public HashingQuadratic() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashingQuadratic(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.table = new Generic[capacity];
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        if (size >= loadFactor * capacity)
            resizeTable();

        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (initialIndex + i * i) % capacity;
            i++;
        }

        if (table[index] == null)
            size++;

        table[index] = new Generic<>(key, value);
    }

    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (initialIndex + i * i) % capacity;
            i++;
        }

        if (table[index] != null && table[index].getKey().equals(key))
            return table[index].getValue();

        return null;
    }

    public Generic<K, V>[] getAll() {
        return this.table;
    }

    public void remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (initialIndex + i * i) % capacity;
            i++;
        }

        if (table[index] != null && table[index].getKey().equals(key)) {
            table[index] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        int index = (hashCode % capacity + capacity) % capacity;
        return index;
    }

    /*private int getIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
    }*/

    private void resizeTable() {
        capacity *= 2;
        Generic<K, V>[] newTable = new Generic[capacity];
        size = 0;

        for (Generic<K, V> entry : table) {
            if (entry != null)
                putInNewTable(newTable, entry.getKey(), entry.getValue());
        }

        table = newTable;
    }

    private void putInNewTable(Generic<K, V>[] table, K key, V value) {
        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        while (table[index] != null) {
            index = (initialIndex + i * i) % capacity;
            i++;
        }

        table[index] = new Generic<>(key, value);
        size++;
    }
}
