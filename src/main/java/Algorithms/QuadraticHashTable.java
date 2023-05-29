package Algorithms;

public class QuadraticHashTable<K, V> extends HashTable<K, V> {
    private Generic<K, V>[] table;

    public QuadraticHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public QuadraticHashTable(int capacity, double loadFactor) {
        super(capacity, 0, loadFactor);
        this.table = new Generic[capacity];
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        if (getSize() >= getCapacity() * getLoadFactor())
            resizeTable();

        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        // Se key for igual a key que ja estar na posição index ele sobreescreve o valor
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (initialIndex + i * i) % getCapacity();
            i++;
        }

        if (table[index] == null)
            setSize(getSize() + 1);

        table[index] = new Generic<>(key, value);
    }

    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (initialIndex + i * i) % getCapacity();
            i++;
        }

        if (table[index] != null && table[index].getKey().equals(key))
            return table[index].getValue();

        return null;
    }

    public Generic<K, V>[] getAll() {
        Generic[] generics = new Generic[getSize()];

        int i = 0;
        for (Generic g : this.table) {
            if (g != null) {
                generics[i++] = g;
            }
        }

        return generics;
    }

    public boolean remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        int index = getIndex(key);
        int i = 1;
        int initialIndex = index;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (initialIndex + i * i) % getCapacity();
            i++;
        }

        if (table[index] != null && table[index].getKey().equals(key)) {
            table[index] = null;
            setSize(getSize() - 1);

            return true;
        }

        return false;
    }

    private void resizeTable() {
        setCapacity(getCapacity() * 2);
        setSize(0);
        Generic<K, V>[] newTable = new Generic[getCapacity()];

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
            index = (initialIndex + i * i) % getCapacity();
            i++;
        }

        table[index] = new Generic<>(key, value);
        setSize(getSize() + 1);
    }
}
