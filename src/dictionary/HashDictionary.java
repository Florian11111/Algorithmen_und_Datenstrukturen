package dictionary;

import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

import java.util.LinkedList;


public class HashDictionary<K, V> implements Dictionary<K, V> {
    private int size;
    private int elemente;
    private LinkedList<Entry<K, V>>[] data;

        public HashDictionary(int m) {
            if (isNotPrime(m))
                throw new IllegalArgumentException("Number must be a Prime Number!");
            size = m;
            elemente = 0;
            data = new LinkedList[m];
            for (int i = 0; i < m; i++) {
                data[i] = new LinkedList<>();
            }
        }

    // isPrime checkt ob es sich um eine Prime zahl handelt
    // code von: https://stackoverflow.com/questions/46877785/java-prime-number-check-with-user-input
    private boolean isNotPrime(final int in) {
        if (in < 2)
            return true;
        for (int i = 2; i <= Math.sqrt(in); i++){
            if (in % i == 0){
                return true;
            }
        }
        return false;
    }

    private int nextPrime(int i) {
        i++;
        while (isNotPrime(i)) {
            i++;
        }
        return i;
    }

    @Override
    public V insert(K key, V value) {
        int i = searchKey(key);
        for (Entry<K, V> x : data[i]) {
            if (x.getKey().equals(key)) {
                V temp = x.getValue();
                x.setValue(value);
                return temp;
            }
        }
        data[i].add(new Entry<>(key, value));
        elemente++;
        if (elemente / size >= 2) {
            reSize();
        }
        // workload berechnen und moeglicherweisse vergrößern!
        return null;
    }

    private void reSize() {
        LinkedList<Entry<K, V>>[] temp = data;
        int newSize = nextPrime(size * 2);
        size = newSize;
        elemente = 0;
        data = new LinkedList[newSize];
        for (int i = 0; i < newSize; i++) {
            data[i] = new LinkedList<>();
        }
        for (var x : temp) {
            for (var y : x) {
                insert(y.getKey(), y.getValue());
            }
        }
    }

    private int searchKey(K key) {
        int x = key.hashCode() % size;
        return x > 0 ? x : -x;
    }

    public V search(K key) {
        int i = searchKey(key);
        if (i < 0)
            return null;
        for (Entry<K, V> x : data[i]) {
            if (x.getKey().equals(key)) {
                return x.getValue();
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = searchKey(key);
        if (index < 0) {
            return null; // Schlüssel nicht gefunden
        }
        for (Entry<K, V> x : data[index]) {
            if (x.getKey().equals(key)) {
                data[index].remove(x);
                elemente--;
                return x.getValue();
            }
        }
        return null; // Schlüssel nicht gefunden
    }


    @Override
    public int size() {
        return elemente;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int i = 0;
            private int j = 0;

            @Override
            public boolean hasNext() {
                while (i < size) {
                    if (j < data[i].size()) {
                        return true;
                    }
                    i++;
                    j = 0;
                }
                return false;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Entry<K, V> entry = data[i].get(j);
                j++; // Erhöhen Sie j, um zum nächsten Element in data[i] zu wechseln
                return entry;
            }
        };
    }


}
