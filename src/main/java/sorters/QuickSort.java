package sorters;

/**
 *Like {@link MergeSort} QuickSort is a Divide and Conquer algorithm.
 *It picks an element as pivot and partitions the given array
 * around the picked pivot.
 */
public class QuickSort extends Sort {
    /**
     * {@inheritDoc}
     * Sort method.
     * @param array array to be sorted
     * @return sorted array
     */
    @Override
    public int[] sort(int[] array) {
        quick(array, 0, array.length - 1);
        return array;
    }

    /**
     * This function takes last element as pivot,places the pivot element
     * at its correct position in sorted array, and places all smaller
     * (smaller than pivot) to left of pivot and all greater elements
     * to right of pivot.
     * @param array array to be parted
     * @param start element, which pass will be started from
     * @param end the end position of pass process
     * @return the index of pivot element
     */
    private int partition(int[] array, int start, int end){
        int pivot = array[end];
        int pIndex = start;
        for(int i = start; i < end; i++){
            if(array[i] <= pivot){
                array[i] = swap(array[pIndex],array[pIndex] = array[i]);
                pIndex++;
            }
        }
        array[pIndex] = swap(array[end], array[end] = array[pIndex]);
        return pIndex;
    }

    /**
     * The main function, that recursively calls itself, using
     * {@link #partition(int[], int, int)} function.
     * @param array array to be sorted
     * @param start element, which pass will be started from
     * @param end the end position of pass process
     */
    private void quick (int[] array, int start, int end){
        if(start>=end)
            return;
        int pivot = partition(array, start, end);
        quick(array, start, pivot-1);
        quick(array, pivot+1, end);
    }
}
