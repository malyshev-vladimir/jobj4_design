package collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeOut == 0 && sizeIn == 0) {
            throw new NoSuchElementException();
        }
        if (sizeOut == 0) {
            while (sizeIn > 0) {
                out.push(in.pop());
                sizeIn--;
                sizeOut++;
            }
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}