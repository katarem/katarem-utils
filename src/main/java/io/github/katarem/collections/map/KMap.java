package io.github.katarem.collections.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;

import io.github.katarem.Pair;

public interface KMap<K, V> extends Map<K, V> {
    default boolean any(BiFunction<K, V, Boolean> action) {
        boolean matches = false;
        K key;
        V value;
        for (Iterator<Entry<K, V>> i = this.entrySet().iterator(); i.hasNext(); matches = action.apply(key, value)) {
            Entry<K, V> entry = i.next();
            try {
                key = entry.getKey();
                value = entry.getValue();
            } catch (IllegalStateException ex) {
                throw new ConcurrentModificationException(ex);
            }
            if(matches) break;
        }
        return matches;
    }

    default boolean all(BiFunction<K, V, Boolean> action) {
        boolean matches = false;
        K key;
        V value;
        for (Iterator<Entry<K, V>> i = this.entrySet().iterator(); i.hasNext(); matches = action.apply(key, value)) {
            Entry<K, V> entry = i.next();
            try {
                key = entry.getKey();
                value = entry.getValue();
            } catch (IllegalStateException ex) {
                throw new ConcurrentModificationException(ex);
            }
        }
        return matches;
    }

    default boolean none(BiFunction<K, V, Boolean> action) {
        boolean matches = false;
        K key;
        V value;
        for (Iterator<Entry<K, V>> i = this.entrySet().iterator(); i.hasNext(); matches = action.apply(key, value)) {
            Entry<K, V> entry = i.next();
            try {
                key = entry.getKey();
                value = entry.getValue();
            } catch (IllegalStateException ex) {
                throw new ConcurrentModificationException(ex);
            }
            if(matches) break;
        }
        return !matches;
    }

    default Integer lastIndex() {
        return this.size() - 1;
    }

    default String joinToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        this.forEach((key, value) -> 
        sb.append(new Pair<K,V>(key, value) + ","));
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    default Pair<K, V> firstOrNull(BiFunction<K, V, Boolean> condition) {
        Pair<K,V> matchingElement = null;
        boolean matches = false;
        K key;
        V value;
        for (Iterator<Entry<K, V>> i = this.entrySet().iterator(); i.hasNext(); matches = condition.apply(key, value)) {
            Entry<K, V> entry = i.next();
            try {
                key = entry.getKey();
                value = entry.getValue();
                
            } catch (IllegalStateException ex) {
                throw new ConcurrentModificationException(ex);
            }
            if (matches) {
                matchingElement = new Pair<K,V>(key, value);
                break;
            }
        }
        return matchingElement;
    }
}
