package map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        int hash = hash(key);
        int index = indexFor(hash);
        if (table[index] == null) {
            table[index] = new MapEntry(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int index = 0; index < table.length; index++) {
            if (table[index] != null) {
                int hash = hash(table[index].key);
                int i = indexFor(hash);
                if (newTable[i] == null) {
                    newTable[i] = table[index];
                }
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        if (key == null) {
            return (table[0] != null && table[0].key == null) ? table[0].value : null;
        }
        int hash = hash(key);
        int index = indexFor(hash);
        if (table[index] != null) {
            if (hash(table[index].key) == hash && table[index].key == key) {
                result = table[index].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hash = hash(key);
        int index = indexFor(hash);
        if (table[index] != null) {
            if (hash(table[index].key) == hash && table[index].key == key) {
                table[index] = null;
                count--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {

        int expectedModCount = modCount;

        return new Iterator<K>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (currentIndex < table.length && table[currentIndex] == null) {
                    currentIndex++;
                }
                return currentIndex < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[currentIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;

        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}