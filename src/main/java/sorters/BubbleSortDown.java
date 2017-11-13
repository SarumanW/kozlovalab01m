package sorters;


/**
 * The relative to {@link BubbleSortUp} sorting algorithm,
 * but in this type element go under to the bottom of array.
 */
public class BubbleSortDown extends BubbleSort {
    /**
     * {@inheritDoc}
     * Sort method.
     * @param array array to be sorted
     * @return sorted array
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = i; j > 0 ; j--) {
                if (array[j] < array[j-1]) {
                    array[j-1] = swap(array[j], array[j] = array[j-1]);
                }
            }
        }
        return array;
    }
}
