package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
    private Entry <K, V>[] entries;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 50;


    public SortedArrayDictionary() {
        this (DEFAULT_CAPACITY);
    }

    public SortedArrayDictionary(int capacity){
        numberOfEntries = 0;
        entries = (Entry<K, V>[]) new Entry [capacity];
    }

    private void ensureCapacity() {
        if (numberOfEntries == entries.length) {// array is full
            entries = Arrays.copyOf(entries, 2*numberOfEntries);
        }
    }

    private int locateIndex (K key){
        int index = 0;
        while ((index < numberOfEntries) && key.compareTo(entries[index].getKey()) > 0) {
            index ++;
        }
        return index;
    }

    private void  makeRoom(int keyIndex) {
        assert (keyIndex >= 0 && keyIndex <= numberOfEntries);
        for (int idx = numberOfEntries; idx > keyIndex; idx --) {
            entries[idx] = entries[idx-1];
        }
    }

    private void removeGap(int givenPosition) {
        assert (givenPosition >= 0 && givenPosition < numberOfEntries);
        for (int index = givenPosition; index < numberOfEntries; index ++) {
            entries[index] = entries[index+1];
        }
    }

    @Override
    public V add(K key, V value) {
        if (key == null || value == null)
            throw new IllegalArgumentException();
        V oldValue = null;
        int keyIndex = locateIndex (key);
        if (keyIndex < numberOfEntries && key.compareTo(entries[keyIndex].getKey())== 0) {
            oldValue = entries[keyIndex].getValue();
            entries[keyIndex].setValue(value); // replacing
        }
        else {
            makeRoom (keyIndex);
            entries[keyIndex] = new Entry(key, value); // adding
            ensureCapacity();
        }
        return oldValue;
    }

    @Override
    public V remove(K key) {
        int index = locateIndex(key);
        if (index == numberOfEntries || key.compareTo (entries[index].getKey()) < 0) // no removal
            return null;
        V oldValue = entries[index].getValue();
        removeGap (index);
        return oldValue;
    }

    @Override
    public V getValue(K key) {
        int index = locateIndex (key);
        if (index < numberOfEntries && key.compareTo(entries[index].getKey()) == 0) {
            return entries [index].getValue();
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        int low = 0, high = numberOfEntries - 1;
        int mid;
        int compValue;
        while (low <= high) {
            mid = (low + high)/2;
            compValue = key.compareTo (entries[mid].getKey());
            if (compValue == 0) {
                return true;
            }
            else if (compValue > 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return false;
    }

    @Override
    public Iterator<K> getKeyIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKeyIterator'");
    }

    @Override
    public Iterator<V> getValueIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValueIterator'");
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
       return numberOfEntries == 0;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
    
    public class KeyIterator implements Iterator <K> {
        int cursor;

        public KeyIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < numberOfEntries;
        }

        @Override
        public K next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            K out = entries[cursor ++].getKey();
            return out;
        }
    }

    public Iterator<K> getKIterator() {
        return new KeyIterator();
    }

    private class ValueIterator implements Iterator <V> {
        int cursor;

        public ValueIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < numberOfEntries;
        }

        @Override
        public V next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            } 
            V out = entries[cursor ++].getValue();
            return out;
        }
    }

    public Iterator<V> getValIterator() {
        return new ValueIterator();
    }
}
