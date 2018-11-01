package sorting;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static sorting.MergeSort.mergeSort;
import static utils.Arrays.wrappedAsIntegers;

import org.junit.jupiter.api.Test;

public class MergeSortUnitTest {

    @Test
    public void shouldSortAnAlreadySortedArray() {
        int[] alreadySortedArray = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        int[] mergeSortedArray = mergeSort(alreadySortedArray);

        assertThat(wrappedAsIntegers(mergeSortedArray), arrayContaining(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void shouldSortAnArrayThatIsSortedInReverseOrder() {
        int[] arraySortedInReverseOrder = new int[]{ 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        int[] mergeSortedArray = mergeSort(arraySortedInReverseOrder);

        assertThat(wrappedAsIntegers(mergeSortedArray), arrayContaining(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void shouldSortAnArrayContainingOneElement() {
        int[] oneElementArray = new int[]{7};

        int[] mergeSortedArray = mergeSort(oneElementArray);

        assertThat(wrappedAsIntegers(mergeSortedArray), arrayContaining(7));
    }

    @Test
    public void shouldSortAnArrayContainingTwoElements() {
        int[] twoElementsArray = new int[]{7, 6};

        int[] mergeSortedArray = mergeSort(twoElementsArray);

        assertThat(wrappedAsIntegers(mergeSortedArray), arrayContaining(6, 7));
    }

    @Test
    public void shouldSortAnArrayContainingAnOddNumberOfElements() {
        int[] arrayWithAnOddNumberOfElements = new int[]{7, 6, 8, 1, 9};

        int[] mergeSortedArray = mergeSort(arrayWithAnOddNumberOfElements);

        assertThat(wrappedAsIntegers(mergeSortedArray), arrayContaining(1, 6, 7, 8, 9));
    }

    @Test
    public void shouldReturnNullWhenTryingToSortANullArray() {
        assertThat(mergeSort(null), is(nullValue()));
    }

    @Test
    public void shouldReturnAnEmptyArrayWhenTryingToSortAnEmptyArray() {
        int[] emptyArray = {};
        assertThat(mergeSort(emptyArray), is(emptyArray));
    }

}
