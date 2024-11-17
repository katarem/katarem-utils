package io.github.katarem;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Katarem
 * @since 17/11/2024
 */
public class ListUtils {
    
    /**
     * @param <E> List type
     * @param list List to iterate
     * @param action Will execute this action with the object and its index inside the list
     * @author katarem
     */
    public static <E> void forEachIndexed(List<E> list, BiConsumer<Integer, E> action){
        for (int i = 0; i < list.size(); i++) {
            action.accept(Integer.valueOf(i), list.get(i));
        }
    }

    /**
     * @param <E> List type
     * @param list List to iterate
     * @param condition Condition to compare the elements
     * @author katarem
     * @return If any of the elements of the list meets the conditions
     */
    public static <E> boolean any(List<E> list, Function<E, Boolean> condition){
        boolean has = false;
        for (E element : list) {
            if(has) break;
            has = condition.apply(element);
        }
        return has;
    }

    /**
     * @param <E> List type
     * @param list List to iterate
     * @param condition Condition to compare the elements
     * @author katarem
     * @return If all the elements of the list meet the conditions
     */
    public static <E> boolean all(List<E> list, Function<E, Boolean> action){
        boolean has = false;
        for (E element : list) {
            has = action.apply(element);
        }
        return has;
    }

    /**
     * @param <E> List type
     * @param list List to iterate
     * @param condition Condition to compare the elements
     * @author katarem
     * @return If there aren't any element of the list that meet the conditions
     */
    public static <E> boolean none(List<E> list, Function<E, Boolean> action){
        boolean has = false;
        for (E element : list) {
            has = action.apply(element);
        }
        return !has;
    }

    /**
     * @param <E> List type
     * @param list List to iterate
     * @param condition Condition to compare the elements
     * @author katarem
     * @return The first element matching the filter or null if there are no matches.
     */
    public static <E> E firstOrNull(List<E> list, Function<E, Boolean> filter){
        E first = null;
        for (E element : list) {
            var applies = filter.apply(element);
            if(applies){
                first = element;
                break;
            }
        }
        return first;
    }

    /**
     * Takes all the list's elements and joins them into a String
     * @param <E> List type
     * @param list List to convert
     * @return List elements concatenated by commas
     */
    public static <E> String jointoString(List<E> list){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        list.forEach(element -> sb.append(element + ","));
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns last index from the given list
     */
    public static int lastIndex(@SuppressWarnings("rawtypes") List list){
        return list.size() - 1;
    }

}