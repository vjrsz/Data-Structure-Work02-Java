package hashing;

public class Generic<Key , Value> implements Comparable<Generic<Key, Value>> {
    private Key key;
    private Value value;

    public Generic(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return this.key;
    }

    public Value getValue() {
        return this.value;
    }

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