package fillers;

import sorters.QuickSort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Random;

/**
 * Class, that includes methods for array filling.
 * Each filling method is annotated.
 * @see java.lang.annotation
 */
public class Fill {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Filler{}

    /**
     * Generates any random number, which lies in
     * interval from min to max.
     * @param min the smallest number that is allowed to be generated
     * @param max the biggest number that is allowed to be generated
     * @return any random number
     */
    private static int random(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt(max - min  + 1) + min;
    }

    /**
     * Reverse array from the start to the end.
     * @param array array to be reversed
     * @return reversed array
     */
    private static int[] reverse(int[] array){
        for(int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    /**
     * Generates simply sorted array in preseted length.
     * @see Fill#random(int, int)
     * @param length length of the array to be generated
     * @return generated array
     */
    @Filler
    public static int[] sortedArray(int length){
        int[] array = new int[length];
        for(int i=0; i<length; i++){
            array[i] = random(0, 100);
        }
        QuickSort sorter = new QuickSort();
        System.out.println("sorted");
        return sorter.sort(array);
    }

    /**
     * Generates simply sorted array in preseted length which
     * includes random number in the end of it.
     * @see Fill#random(int, int)
     * @param length length of the array to be generated
     * @return generated array
     */
    @Filler
    public static int[] sortedArrayPlusRan (int length){
        int[] array = new int[length];
        for(int i=0; i<length - 1; i++) {
            array[i] = random(0, 100);
        }
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        array[length - 1] = random(0, 100);
        System.out.println("sorted + ran");
        return array;
    }

    /**
     * Generates reverse sorted array in preseted length.
     * @see Fill#random(int, int)
     * @see Fill#reverse(int[])
     * @param length length of the array to be generated
     * @return generated array
     */
    @Filler
    public static int[] sortedReverseArray(int length){
        int[] array = new int[length];
        for(int i=0; i<length; i++){
            array[i] = random(0, 20);
        }
        QuickSort sorter = new QuickSort();
        sorter.sort(array);
        System.out.println("sorted reverse");
        return reverse(array);
    }

    /**
     * Generates random array in preseted length.
     * @see Fill#random(int, int)
     * @param length length of the array to be generated
     * @return generated array
     */
    @Filler
    public static int[] randomArray(int length){
        int[] array = new int[length];
        for(int i=0; i<length; i++){
            array[i] = random(0, 20);
        }
        System.out.println("random");
        return array;
    }
}
