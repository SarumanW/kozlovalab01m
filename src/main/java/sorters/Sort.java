package sorters;

/**
 * Abstract class for sorting array in different ways.
 * @author Kozlova
 */
public abstract class Sort {
    /**
     * @author Kozlova
     * Abstract sorting method, which is realized in classes, that extend parent {@link Sort} class.
     * @param array array to be sorted
     * @return sorted array
     */
    public abstract int[] sort(int[] array);

    /**
     * Function that swaps two elements values.
     * Is used in every inheritor of {@link Sort} class.
     * @param a first value to swap
     * @param b second value to swap
     * @return first value
     */
    protected static int swap (int a, int b){
        return a;
    }

}
