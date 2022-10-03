import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaxSubArrTest {
    int[] testArray = {-9, 10, -8, 10, 5, -4, -2, 5};
    MaxSubArr maxSubArr;

    @BeforeEach
    public void setUp() {
        maxSubArr = new MaxSubArr();
    }

    @Test
    public void testBruteForce() {
        maxSubArr.bruteForce(testArray);
        System.out.println(maxSubArr);
    }

    @Test
    public void testDivideAndConquer() {
        maxSubArr = new MaxSubArr();
        maxSubArr.divideAndConquer(testArray);
        System.out.println(maxSubArr);
    }

    @Test
    public void testKadane() {
        maxSubArr = new MaxSubArr();
        maxSubArr.kadane(testArray);
        System.out.println(maxSubArr);
    }
}
