package Algorithms.Hashs;

public abstract class HashTable<K, V> implements IHashTable<K, V> {
    protected static final int DEFAULT_CAPACITY = 10;
    protected static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int capacity;
    private int size;
    private double loadFactor;

    public HashTable(int capacity, int size, double loadFactor) {
        this.capacity = capacity;
        this.size = size;
        this.loadFactor = loadFactor;
    }

    protected int getIndex(K key) {
        int hashCode = key.hashCode();
        int index = (hashCode % capacity + capacity) % capacity;
        return index;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(double loadFactor) {
        this.loadFactor = loadFactor;
    }
}
