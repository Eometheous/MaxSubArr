import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSubArrTest {
    private static final int[][] testArrays = new int[10][100];
    MaxSubArr maxSubArr;
    double startTime, endTime, totalTime;
    private final static int[] expectedMax = new int[10];
    private final static int[] expectedStart = new int[10];
    private final static int[] expectedEnd = new int[10];
    @BeforeAll
    static void beforeAll() {

        final String filePath = "/Desktop/MaxSubArrayTest/maxSumtest.txt";
        String homePath = System.getenv("HOMEPATH");
        if (homePath == null) homePath = System.getenv("HOME");
        String filename = homePath + filePath;

        try {
            Scanner scannerTextFile = new Scanner(new File(filename));
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 100; j++) {
                    testArrays[i][j] = scannerTextFile.nextInt();
                }
                expectedMax[i] = scannerTextFile.nextInt();
                expectedStart[i] = scannerTextFile.nextInt();
                expectedEnd[i] = scannerTextFile.nextInt();
            }
            scannerTextFile.close();
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
    public void test200Integers() {
        int[] array = RandomArrayUtil.randomArray(200, 100);
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
    public void test2000Integers() {
        int[] array = RandomArrayUtil.randomArray(2000, 100);
        maxSubArr = new MaxSubArr();
        testRunTime(array);
    }
    @Test
    public void test5000Integers() {
        int[] array = RandomArrayUtil.randomArray(5000, 100);
        maxSubArr = new MaxSubArr();
        testRunTime(array);
    }

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
