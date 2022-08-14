package collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;
    private ForwardLinked<Integer> AnotherLinked;

    @BeforeEach
    public void init() {
        linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        AnotherLinked = new ForwardLinked<>();
    }

    @Test
    void whenDeleteFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDelete() {
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(AnotherLinked.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        AnotherLinked.add(1);
        assertThat(AnotherLinked.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        AnotherLinked.add(1);
        AnotherLinked.add(2);
        AnotherLinked.add(3);
        AnotherLinked.add(4);
        assertThat(AnotherLinked).containsSequence(1, 2, 3, 4);
        assertThat(AnotherLinked.revert()).isTrue();
        assertThat(AnotherLinked).containsSequence(4, 3, 2, 1);
    }
}