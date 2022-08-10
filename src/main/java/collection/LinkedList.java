package collection;

public interface LinkedList<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}