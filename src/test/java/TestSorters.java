import org.junit.Test;
import sorters.*;

import static org.junit.Assert.assertArrayEquals;

public class TestSorters {
    private int[] startArray = {5, 8, 2, 9, 7, 6};
    private int[] sortedArray = {2, 5, 6, 7, 8, 9};

    @Test
    public void testAutoSort(){
        Sort sorter = new AutoSort();
        int[] result = sorter.sort(startArray);
        assertArrayEquals(sortedArray, result);
    }

    @Test
    public void testBubbleSortUp(){
        Sort sorter = new BubbleSortUp();
        int[] result = sorter.sort(startArray);
        assertArrayEquals(sortedArray, result);
    }

    @Test
    public void testBubbleSortDown(){
        Sort sorter = new BubbleSortDown();
        int[] result = sorter.sort(startArray);
        assertArrayEquals(sortedArray, result);
    }

    @Test
    public void testExchangeSort(){
        Sort sorter = new ExchangeSort();
        int[] result = sorter.sort(startArray);
        assertArrayEquals(sortedArray, result);
    }

    @Test
    public void testMergeSort(){
        Sort sorter = new MergeSort();
        int[] result = sorter.sort(startArray);
        assertArrayEquals(sortedArray, result);
    }

    @Test
    public void testQuickSort(){
        Sort sorter = new QuickSort();
        int[] result = sorter.sort(startArray);
        assertArrayEquals(sortedArray, result);
    }

}
