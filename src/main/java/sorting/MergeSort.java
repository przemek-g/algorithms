package sorting;

public class MergeSort {

    private MergeSort() {
        throw new UnsupportedOperationException();
    }

    public static int[] mergeSort(int[] array) {
        if (array == null) {
            return null;
        }

        int[] helper = new int[array.length];

        mergeSort(array, 0, array.length-1, helper);
        return array;
    }

    private static void mergeSort(int[] array, int start, int endInclusive, int[] helper) {
        if (start >= endInclusive) {
            return;
        }

        int middle = start + (endInclusive - start)/2;
        mergeSort(array, start, middle, helper);
        mergeSort(array, middle + 1, endInclusive, helper);

        merge(array, start, middle, endInclusive, helper);
    }

    private static void merge(int[] array, int start, int middle, int endInclusive, int[] helper) {
        for (int i = start; i<= endInclusive; i++) {
            helper[i] = array[i];
        }

        int left = start, right = middle+1, currentTargetIndex = start;

        while (left <= middle && right <= endInclusive) {
            if (helper[left] < helper[right]) {
                array[currentTargetIndex++] = helper[left++];
            } else {
                array[currentTargetIndex++] = helper[right++];
            }
        }

        while (left <= middle) {
            array[currentTargetIndex++] = helper[left++];
        }
    }
}
