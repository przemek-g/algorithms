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

    public static Integer[] wrappedAsIntegers(int... elements) {
        Integer[] integers = new Integer[elements.length];
        for (int i = 0; i < elements.length; i++) {
            integers[i] = elements[i];
        }
        return integers;
    }
}
