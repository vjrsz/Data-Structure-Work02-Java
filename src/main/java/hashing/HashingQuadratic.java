package hashing;

public class HashingQuadratic<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
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
        this.table = new Entry[capacity];
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

        table[index] = new Entry<>(key, value);
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
        return Math.abs(key.hashCode() % capacity);
    }

    private void resizeTable() {
        capacity *= 2;
        Entry<K, V>[] newTable = new Entry[capacity];
        size = 0;

        for (Entry<K, V> entry : table) {
            if (entry != null)
                putInNewTable(newTable, entry.getKey(), entry.getValue());
        }

        table = newTable;
    }

    private void putInNewTable(Entry<K, V>[] table, K key, V value) {
        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        while (table[index] != null) {
            index = (initialIndex + i * i) % capacity;
            i++;
        }

        table[index] = new Entry<>(key, value);
        size++;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
