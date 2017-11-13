package sorters;

/**
 *  Sorting algorithm that repeatedly steps through the list to be sorted,
 *  compares each pair of adjacent items and swaps them if they are in the
 *  wrong order. The pass through the list is repeated until no swaps are
 *  needed, which indicates that the list is sorted. Unlike his relative
 *  {@link BubbleSortDown} type of sorting, in {@link BubbleSortDown}
 *  element pops up to the top of array.
 */
public class BubbleSortUp extends BubbleSort {
    /**
     * {@inheritDoc}
     * Sort method.
     * @param array array to be sorted
     * @return sorted array
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    array[j+1] = swap(array[j], array[j] = array[j+1]);
                }
            }
        }
        return array;
    }
}
