package sorters;

import java.util.Arrays;

/**
 * Type of sorting, that uses classic function from java library.
 * @see java.util.Arrays#sort(int[])
 */
public class AutoSort extends Sort {
    /**{@inheritDoc}
     *Sort method.
     * @param array array to be sorted
     * @return sorted array
     */
    @Override
    public int[] sort(int[] array) {
        Arrays.sort(array);
        return array;
    }
}
