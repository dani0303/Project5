package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {

    class Entry <K, V> {
        private K key;
        private V value;
        public Entry (K key, V value) {
            this.key = key; this.value = value;
        }
        public K getKey() {return key;}
        public V getValue() {return value;}
        public void setValue (V newValue) {value = newValue;}
    }

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
        for (int idx = numberOfEntries; idx > newPosition; idx --) {
            list[idx] = list[idx-1];
        }
    }

    private void removeGap(int key) {

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }

    @Override
    public boolean contains(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    
}
