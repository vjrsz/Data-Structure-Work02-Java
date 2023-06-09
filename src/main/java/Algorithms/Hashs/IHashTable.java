package Algorithms.Hashs;

import Algorithms.Generic;

public interface IHashTable<K, V> {
    public void put(K key, V value);
    public V get(K key);
    public Generic[] getAll();
    public boolean remove(K key);
}
