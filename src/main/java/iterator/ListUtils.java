package iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index + 1) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        Objects.requireNonNull(filter);
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
                break;
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        Objects.requireNonNull(filter);
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
                iterator.add(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T currentValue = iterator.next();
            for (T element: elements) {
                if (element.equals(currentValue)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }
}