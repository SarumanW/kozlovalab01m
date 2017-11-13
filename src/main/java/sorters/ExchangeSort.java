package sorters;

/**
 * The exchange sort is similar to its cousin {@link BubbleSortUp} in that it
 * compares elements of the array and swaps those that are out of order.
 * The difference between these two sorts is the manner in which they compare
 * the elements. The exchange sort compares the first element with each following
 * element of the array, making any necessary swaps.
 */
public class ExchangeSort extends Sort {
    /**
     * {@inheritDoc}
     * Sort method.
     * @param array array to be sorted
     * @return sorted array
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    array[j] = swap(array[i], array[i] = array[j]);
                }
            }
        }
        return array;
    }
}
