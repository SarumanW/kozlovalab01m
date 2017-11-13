import org.junit.Test;

import static fillers.Fill.randomArray;
import static org.junit.Assert.assertArrayEquals;

public class TestFill {
    @Test
    public void testRandomArray(){
        int[] testArray = randomArray(10);
        for(int i = 0; i<100; i++){
            int[] array = randomArray(10);
            assertArrayEquals(array, testArray);
            testArray = array;
        }
    }
}
