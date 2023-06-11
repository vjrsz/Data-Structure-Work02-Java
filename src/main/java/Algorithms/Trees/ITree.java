package Algorithms.Trees;

import java.util.List;

public interface ITree<K,V> {
    public void put(K key, V value);
    public List<V> getAll();
    public String getName();
    public long getComparisons();
}
