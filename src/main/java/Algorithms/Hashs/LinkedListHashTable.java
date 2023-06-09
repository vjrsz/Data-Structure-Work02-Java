package Algorithms.Hashs;

import Algorithms.Generic;
import java.util.LinkedList;

public class LinkedListHashTable<K,V> extends HashTable<K,V> {
    private LinkedList<Generic<K, V>>[] table;

    public LinkedListHashTable(int capacity, double loadFactor) {
        super(capacity, 0, loadFactor);
        this.table = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public LinkedListHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public void put(K key, V value) {
        if( getSize() >= getCapacity() * getLoadFactor() ){
            this.resizeTable();
        }

        int index = getIndex(key);

        LinkedList<Generic<K, V>> list = table[index];

        for (Generic<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        list.add(new Generic<>(key, value));

        setSize(getSize() + 1);
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Generic<K, V>> list = table[index];
        for (Generic<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Generic[] getAll() {
        Generic[] generics = new Generic[getSize()];

        int i = 0;
        for (LinkedList<Generic<K, V>> list : table) {
            for (Generic<K, V> entry : list) {
                if (entry != null) {
                    generics[i++] = entry;
                }
            }
        }

        return generics;
    }

    public boolean remove(K key) {
        int index = getIndex(key);
        LinkedList<Generic<K, V>> list = table[index];
        for (Generic<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                list.remove(entry);
                setSize(getSize() - 1);
                return true;
            }
        }
        return false;
    }

    private void resizeTable() {
        setCapacity(getCapacity() * 2);
        setSize(0);

        LinkedList<Generic<K, V>>[] newTable = new LinkedList[getCapacity()];

        for (int i = 0; i < getCapacity(); i++) {
            newTable[i] = new LinkedList<>();
        }

        for (LinkedList<Generic<K, V>> chain : table) {
            for (Generic<K, V> entry : chain) {
                putInNewTable(newTable, entry.getKey(), entry.getValue());
            }
        }

        table = newTable;
    }
    private void putInNewTable(LinkedList<Generic<K, V>>[] newTable, K key, V value) {
        int index = Math.abs(key.hashCode() % getCapacity());

        LinkedList<Generic<K, V>> list = newTable[index];

        list.add(new Generic(key, value));

        setSize(getSize() + 1);
    }
}