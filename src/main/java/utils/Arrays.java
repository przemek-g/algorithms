package utils;

public class Arrays {

    private Arrays() {
        throw new UnsupportedOperationException();
    }

    public static void swap(int[] array, int first, int second) {
        int copyOfFirst = array[first];
        array[first] = array[second];
        array[second] = copyOfFirst;
    }
}
