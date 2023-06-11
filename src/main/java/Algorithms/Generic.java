package Algorithms;

public class Generic<K, V> implements Comparable<Generic<K, V>> {
    private K key;
    private V value;

    public Generic(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) { this.key = key; }
    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) { this.value = value; }

    @Override
    public int compareTo(Generic o) {
        if(this.key instanceof Integer){
            return((Integer) this.key).compareTo((Integer) o.getKey());
        }else if(this.key instanceof String){
            return((String) this.key).compareTo((String) o.getKey());
        }else{
            return((Double) this.key).compareTo((Double) o.getKey());
        }
    }
}