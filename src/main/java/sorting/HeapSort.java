package sorting;

import static datastructures.heap.Heap.makeMaxHeap;
import static datastructures.heap.Heap.maxHeapify;
import static utils.Arrays.swap;

import datastructures.heap.Heap;

public class HeapSort {

    private HeapSort() {
        throw new UnsupportedOperationException();
    }

    public static int[] heapSortAscending(int[] arrayToSort) {
        Heap heapToSort = Heap.of(arrayToSort);
        makeMaxHeap(heapToSort);

        while (heapToSort.getSize() > 1) {
            swap(heapToSort.getArray(), 1, heapToSort.getSize());
            heapToSort.decrementSize();
            maxHeapify(heapToSort, 1);
        }

        int[] arraySortedInAscendingOrder = heapToSort.copyArrayWithoutLeadingMarkerElement();
        return arraySortedInAscendingOrder;
    }
}
