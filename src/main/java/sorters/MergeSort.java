package sorters;

/**
 * Like {@link QuickSort}, Merge Sort is a Divide and Conquer algorithm.
 * It divides input array in two halves, calls itself for the two halves
 * and then merges the two sorted halves.
 */
public class MergeSort extends Sort {
    /**
     * {@inheritDoc}
     * Sort method.
     * @param array array to be sorted
     * @return sorted array
     */
    @Override
    public int[] sort(int[] array) {
        if(array.length<=1)
            return array;
        int[] arr1 = new int[array.length/2];
        int[] arr2 = new int[array.length - array.length/2];
        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, array.length/2, arr2, 0, arr2.length);
        return merge(sort(arr1), sort(arr2));
    }

    /**
     * Function that merges two subarrays, that are presumably sorted in
     * {@link #sort(int[])} method.
     * @param arr1 first array to be merged
     * @param arr2 second array to be merged
     * @return merged array from sorted subarrays
     */
    private int[] merge(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        for(int k = 0; k < result.length; k++){
            if(i >= arr1.length) {
                result[k] = arr2[j++];
            } else if(j >= arr2.length) {
                result[k] = arr1[i++];
            } else if(arr1[i] <= arr2[j]) {
                result[k] = arr1[i++];
            } else {
                result[k] = arr2[j++];
            }
        }

        return result;
    }
}
