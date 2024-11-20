package io.github.katarem.collections;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface KCollection<T> extends Collection<T> {

    /**
     * @param <T> Collection's type
     * @param action Action to be executed by all the collection's elements
     */
    default void forEachIndexed(BiConsumer<Integer, T> action){
        int count = 0;
        for (T element : this) {
            action.accept(count, element);
            count++;
        }
    }

    /**
     * @param <T> Collection's type
     * @param condition Condition to compare the elements
     * @return If any of the elements of the list meets the conditions
     */
    default boolean any(Function<T, Boolean> condition){
        boolean matches = false;
        for(T element : this){
            matches = condition.apply(element);
            if(matches) break;
        }
        return matches;
    }

    /**
     * @param <T> Collection's type
     * @param condition Condition to compare the elements
     * @return If all the elements of the list meet the conditions
     */
    default boolean all(Function<T, Boolean> condition){
        boolean matches = false;
        for(T element : this){
            matches = condition.apply(element);
        }
        return matches;
    }

    /**
     * @param <T> Collection's type
     * @param condition Condition to compare the elements
     * @return If there aren't any element of the list that meet the conditions
     */
    default boolean none(Function<T, Boolean> condition){
        boolean matches = false;
        for(T element : this){
            matches = condition.apply(element);
            if(matches) break;
        }
        return !matches;
    }

    /**
     * Takes all the collection's elements and joins them into a String
     * @return Collection's elements concatenated by commas
     */
    default String joinToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        this.forEach(element -> sb.append(element + ","));
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @param <T> Collection's type
     * @param condition Condition to compare the elements
     * @return The first element matching the filter or null if there are no matches.
     */
    default T firstOrNull(Function<T, Boolean> condition){
        T matchingElement = null;
        for(T element : this){
            boolean matches = condition.apply(element);
            if(matches){
                matchingElement = element;
                break;
            }
        }
        return matchingElement;
    }
}
