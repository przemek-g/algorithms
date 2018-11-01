package datastructures.heap;

import static datastructures.heap.KthElement.isKthElementGreaterThanOrEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class KthElementUnitTest {

    @Test
    public void shouldFindThatTheKthElementIsNotGreaterThanOrEqualToX() {
        Heap heap = Heap.of(1, 10, 13, 11, 22, 21, 23, 24, 26);

        int k = 5;
        int value = 22;
        boolean kthElementGreaterThanOrEqualTo = isKthElementGreaterThanOrEqualTo(heap, value, k);

        assertThat(kthElementGreaterThanOrEqualTo, is(false));
    }

    @Test
    public void shouldFindThatTheKthElementIsGreaterThanOrEqualToX() {
        Heap heap = Heap.of(1, 10, 13, 11, 22, 21, 23, 24, 26);

        int k = 5;
        int value = 20;
        boolean kthElementGreaterThanOrEqualTo = isKthElementGreaterThanOrEqualTo(heap, value, k);

        assertThat(kthElementGreaterThanOrEqualTo, is(true));
    }

    @Test
    public void shouldFindThatTheKthElementIsEqualToX() {
        Heap heap = Heap.of(1, 10, 13, 11, 22, 21, 23, 24, 26);

        int k = 5;
        int value = 21;
        boolean kthElementGreaterThanOrEqualTo = isKthElementGreaterThanOrEqualTo(heap, value, k);

        assertThat(kthElementGreaterThanOrEqualTo, is(true));
    }

}
