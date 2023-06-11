package Algorithms.Hashs;

import Algorithms.Generic;

public interface IHashTable<K, V> {
    public void put(K key, V value); // funcao de inserir e alterarw
    public V get(K key); // funcao de busca
    public Generic[] getAll();
    public boolean remove(K key);
    public String getName();
}
