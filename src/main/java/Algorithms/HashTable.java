package Algorithms;

import java.util.List;

public interface HashTable<K, V> {
    public V get(K key);
    public Generic[] getAll();
    public void put(K key, V value);
    public void remove(K key);
    public int size();
}
