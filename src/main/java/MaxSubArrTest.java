import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Runs multiple tests on MaxSubArr.java to make sure
 * actual results are the same as the expected results from the
 * given text file
 */
public class MaxSubArrTest {
    private static final int[][] testArrays = new int[10][100]; // an array of 10 arrays holding 100 integers
    MaxSubArr maxSubArr;                                        // MaxSubArr object to find max sum of an array
    double startTime, endTime, totalTime;                       // used for calculating runtimes
    private final static int[] expectedMax = new int[10];       // an array containing the expected max sums
    private final static int[] expectedStart = new int[10];     // an array containing the expected start days
    private final static int[] expectedEnd = new int[10];       // an array containing the expected end days
    @BeforeAll
    static void beforeAll() {

        String filename = "maxSumtest.txt";     // file we are reading to get the test arrays

        try {
            Scanner scannerTextFile = new Scanner(new File(filename));
            for (int i = 0; i < 10; i++) {      // file contains 10 arrays
                for (int j = 0; j < 100; j++) { // read the 100 integers for the array
                    testArrays[i][j] = scannerTextFile.nextInt();   // store the integers into the test array
                }

                // last three integers in each line are the expected max, start day, and end day
                // read them and store them into their array
                expectedMax[i] = scannerTextFile.nextInt();
                expectedStart[i] = scannerTextFile.nextInt();
                expectedEnd[i] = scannerTextFile.nextInt();
            }
            scannerTextFile.close();    // we are done reaidng the file
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testBruteForce() {
        for (int i = 0; i < 10; i++) {
            maxSubArr = new MaxSubArr();
            maxSubArr.bruteForce(testArrays[i]);
            System.out.println(maxSubArr);
            assertEquals(expectedMax[i], maxSubArr.getMax());
            assertEquals(expectedStart[i], maxSubArr.getStart());
            assertEquals(expectedEnd[i], maxSubArr.getEnd());
        }
    }

    @Test
    public void testDivideAndConquer() {
        for (int i = 0; i < 10; i++) {
            maxSubArr = new MaxSubArr();
            maxSubArr.divideAndConquer(testArrays[i]);
            System.out.println(maxSubArr);
            assertEquals(expectedMax[i], maxSubArr.getMax());
            assertEquals(expectedStart[i], maxSubArr.getStart());
            assertEquals(expectedEnd[i], maxSubArr.getEnd());
        }
    }

    @Test
    public void testKadane() {
        for (int i = 0; i < 10; i++) {
            maxSubArr = new MaxSubArr();
            maxSubArr.kadane(testArrays[i]);
            System.out.println(maxSubArr);
            assertEquals(expectedMax[i], maxSubArr.getMax());
            assertEquals(expectedStart[i], maxSubArr.getStart());
            assertEquals(expectedEnd[i], maxSubArr.getEnd());
        }
    }
    @Test
    public void test100Integers() {
        int[] array = RandomArrayUtil.randomArray(100, 100);
        maxSubArr = new MaxSubArr();
        testRunTime(array);
    }
    @Test
    public void test500Integers() {
        int[] array = RandomArrayUtil.randomArray(500, 100);
        maxSubArr = new MaxSubArr();
        testRunTime(array);
    }
    @Test
    public void test1000Integers() {
        int[] array = RandomArrayUtil.randomArray(1000, 100);
        maxSubArr = new MaxSubArr();
        testRunTime(array);
    }
    @Test
    public void test5000Integers() {
        int[] array = RandomArrayUtil.randomArray(5000, 100);
        maxSubArr = new MaxSubArr();
        testRunTime(array);
    }
    @Test
    public void test10000Integers() {
        int[] array = RandomArrayUtil.randomArray(10000, 100);
        maxSubArr = new MaxSubArr();
        testRunTime(array);
    }

    // Only run the tests below if you have a fast machine
//    @Test
//    public void test50000Integers() {
//        int[] array = RandomArrayUtil.randomArray(50000, 100);
//        maxSubArr = new MaxSubArr();
//        testRunTime(array);
//    }
//
//    @Test
//    public void test100000Integers() {
//        int[] array = RandomArrayUtil.randomArray(100000, 100);
//        maxSubArr = new MaxSubArr();
//        testRunTime(array);
//    }

    private void testRunTime(int[] array) {
        for (int i = 0; i < 10; i++) {
            startTime = System.nanoTime();
            maxSubArr.bruteForce(array);
            endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }
        totalTime /= 10;
        System.out.printf("Brute Force: %.4f\n", (totalTime) / 1000000);

        for (int i = 0; i < 10; i++) {
            startTime = System.nanoTime();
            maxSubArr.divideAndConquer(array);
            endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }
        totalTime /= 10;
        System.out.printf("Divide and Conquer: %.4f\n", (totalTime) / 1000000);

        for (int i = 0; i < 10; i++) {
            startTime = System.nanoTime();
            maxSubArr.kadane(array);
            endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }
        totalTime /= 10;
        System.out.printf("Kadane: %.4f\n", (totalTime) / 1000000);
    }
}
