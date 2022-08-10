package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;

    transient Node<E> first;

    transient Node<E> last;

    private int modCount;

    private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> lastNode = last;
        final Node<E> newNode = new Node<>(lastNode, value, null);
        last = newNode;
        size++;
        modCount++;
        if (first == null) {
            first = newNode;
        }
        if (first != last) {
            newNode.prev.next = newNode;
        }
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = first;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> currentObject = first;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentObject != null;
            }

            public E next() {
                if (hasNext()) {
                    if (currentObject == null) {
                        throw new NoSuchElementException();
                    }
                    E result = currentObject.item;
                    currentObject = currentObject.next;
                    return result;
                }
                return null;
            }
        };
    }
}