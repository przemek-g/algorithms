package sorting;

import static sorting.HeapSort.heapSortAscending;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;

import utils.Arrays;

import org.junit.jupiter.api.Test;

public class HeapSortUnitTest {

    @Test
    public void shouldPreserveOrderInAnArrayThatIsAlreadySorted() {
        int[] alreadySortedArray = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        Integer[] heapSortedArray = Arrays.wrappedAsIntegers(heapSortAscending(alreadySortedArray));

        assertThat(heapSortedArray, arrayContaining(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void shouldSortArrayInAscendingOrderWhenItIsSortedInDescendingOrder() {
        int[] arraySortedInDescendingOrder = new int[]{ 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        Integer[] heapSortedArray = Arrays.wrappedAsIntegers(heapSortAscending(arraySortedInDescendingOrder));

        assertThat(heapSortedArray, arrayContaining(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void shouldSortARandomlyOrderedArrayInAscendingOrder() {
        int[] randomlyOrderedArray = new int[]{ 10, 9, 8, 6, 7, 4, 5, 3, 1, 2 };

        Integer[] heapSortedArray = Arrays.wrappedAsIntegers(heapSortAscending(randomlyOrderedArray));

        assertThat(heapSortedArray, arrayContaining(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void shouldSortAnArrayThatConsistsOfOneElementOnly() {
        int[] oneElementArray = new int[]{ 10 };

        Integer[] heapSortedArray = Arrays.wrappedAsIntegers(heapSortAscending(oneElementArray));

        assertThat(heapSortedArray, arrayContaining(10));
    }

    @Test
    public void shouldSortAnArrayThatConsistsOfTwoElements() {
        int[] oneElementArray = new int[]{ 10, 9 };

        Integer[] heapSortedArray = Arrays.wrappedAsIntegers(heapSortAscending(oneElementArray));

        assertThat(heapSortedArray, arrayContaining(9, 10));
    }

    @Test
    public void shouldSortAnArrayThatConsistsOfThreeElements() {
        int[] oneElementArray = new int[]{ 10, 8, 9 };

        Integer[] heapSortedArray = Arrays.wrappedAsIntegers(heapSortAscending(oneElementArray));

        assertThat(heapSortedArray, arrayContaining(8, 9, 10));
    }

}
