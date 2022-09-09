package tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> foundParent = findBy(parent);
        boolean rsl = foundParent.isPresent() && findBy(child).isEmpty();
        if (rsl) {
            foundParent.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(e -> e.value.equals(value));
    }

    public boolean isBinary() {
        return findByPredicate(e -> e.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }
}