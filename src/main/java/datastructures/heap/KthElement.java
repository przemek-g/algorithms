package datastructures.heap;

public class KthElement {

    private KthElement() {
        throw new UnsupportedOperationException();
    }

    public static boolean isKthElementGreaterThanOrEqualTo(Heap heap, int valueToCompareAgainst, int k) {
        return countSmaller(heap, valueToCompareAgainst, k, 1) > 0;
    }

    private static int countSmaller(Heap heap, int valueToCompareAgainst, int count, int index) {
        if (count <= 0 || index > heap.getSize()) {
            return count;
        }

        if (heap.getArray()[index] < valueToCompareAgainst) {
            count = countSmaller(heap, valueToCompareAgainst, count-1, 2*index);
            count = countSmaller(heap, valueToCompareAgainst, count, 2*index + 1);
        }

        return count;
    }
}
