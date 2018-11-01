package datastructures.heap;

import static lombok.AccessLevel.PRIVATE;
import static utils.Arrays.swap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = PRIVATE)
@Getter
public class Heap {

    private static final int NO_ELEMENT_MARKER = -1;
    private int[] array;
    private int size;

    public static Heap of(int... elements) {
        int arraySize = elements.length + 1;
        int[] array = new int[arraySize];
        array[0] = NO_ELEMENT_MARKER;
        for (int i=0; i<elements.length; i++) {
            array[i+1] = elements[i];
        }
        return new Heap(array, elements.length);
    }

    public int[] copyArrayWithoutLeadingMarkerElement() {
        int[] arraySortedInAscendingOrder = new int[this.array.length-1];
        for (int i = 0; i < arraySortedInAscendingOrder.length; i++) {
            arraySortedInAscendingOrder[i] = this.array[i+1];
        }
        return arraySortedInAscendingOrder;
    }

    public void decrementSize() {
        this.size--;
    }

    public static void makeMaxHeap(Heap heapToSort) {
        for (int i = heapToSort.size; i >= 1; i--) {
            maxHeapify(heapToSort, i);
        }
    }

    public static void maxHeapify(Heap heap, int indexOfRootToStartFrom) {
        int maxIndex = chooseIndexOfMaxElementAmongNodeAndItsImmediateSuccessors(heap, indexOfRootToStartFrom);

        if (maxIndex != indexOfRootToStartFrom) {
            swap(heap.array, maxIndex, indexOfRootToStartFrom);
            maxHeapify(heap, maxIndex);
        }
    }

    private static int chooseIndexOfMaxElementAmongNodeAndItsImmediateSuccessors(Heap heap, int indexOfRootToStartFrom) {
        int maxIndex = indexOfRootToStartFrom;

        for (int i=0; i<=1; i++) {
            int childIndex = 2*indexOfRootToStartFrom + i;
            if (childIndex <= heap.size && heap.array[childIndex] > heap.array[maxIndex]) {
                maxIndex = childIndex;
            }
        }
        return maxIndex;
    }
}
