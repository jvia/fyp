package org.bham.aucom.util;

import java.io.Serializable;
import java.util.*;


public class HashMatrix<T, U, V> implements Serializable {
    private static final long serialVersionUID = 0L;
    HashMap<T, HashMap<U, V>> content;

    public HashMatrix() {
        content = new LinkedHashMap<T, HashMap<U, V>>();
    }

    public List<V> values() {
        ArrayList<V> out = new ArrayList<V>();
        for (T t : content.keySet()) {
            for (U u : content.get(t).keySet()) {
                out.add(content.get(t).get(u));
            }
        }
        return out;
    }

    public void put(T indexOne, U indexTwo, V value) {
        // TODO :: Perhaps reducer can go in here and just take care of things invisibly
        HashMap<U, V> tmp = get(indexOne);

        // make sure theres is a row hashmap available
        if (!containsFirstKey(indexOne)) {
            tmp = new HashMap<U, V>();
            content.put(indexOne, tmp);
        }
        // put the value in the specific column
        HashMap<U, V> tmp_row = content.get(indexOne);
        tmp_row.put(indexTwo, value);

    }

    public HashMap<U, V> get(T indexOne) {
        return content.get(indexOne);
    }

    public V get(T indexOne, U indexTwo) {
        //System.out.println("contains index one " + content.containsKey(indexOne));
        if (!content.containsKey(indexOne)) {
            return null;
        }
        //System.out.println("contains index two " + content.get(indexOne).containsKey(indexTwo));
        return content.get(indexOne).get(indexTwo);
    }

    public boolean containsFirstKey(T indexOne) {
        return content.containsKey(indexOne);
    }

    public boolean containsKey(T indexOne, U indexTwo) {
        boolean contains = content.containsKey(indexOne);
//		System.out.println("1: " +contains);
        if (contains)
            contains = contains & content.get(indexOne).containsKey(indexTwo);
//		System.out.println("2: " +contains);
        return contains;
    }

    public ArrayList<Tuple<T, U>> keySet() {
        ArrayList<Tuple<T, U>> keySet = new ArrayList<Tuple<T, U>>();
        Set<T> keysIndexOne = content.keySet();
        for (T key : keysIndexOne) {
            Set<U> keysIndexTwo = content.get(key).keySet();
            for (U key2 : keysIndexTwo) {
                keySet.add(new Tuple<T, U>(key, key2));
            }
        }
        return keySet;
    }

    public int size() {
        int out = 0;
        for (HashMap<U, V> rows : content.values()) {
            out += rows.size();
        }
        return out;
    }

    public int[] dimension() {
        int[] out = new int[2];
        out[0] = content.size();
        out[1] = content.values().iterator().next().size();
        return out;
    }

    @Override
    public String toString() {
        String out = "";
        for (T rowKey : content.keySet()) {
            for (U colKey : content.get(rowKey).keySet()) {
                out += "[" + rowKey.toString() + ">" + colKey + "]\n";
                out += content.get(rowKey).get(colKey) + "\n";
            }
            out += "\n";
        }
        return out;
    }

}
