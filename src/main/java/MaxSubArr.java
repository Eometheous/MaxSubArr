/**
 * MaxSubArr calculates the maximum possible sum of a given array
 * with three different algorithms, brute force, divide and conquer, and Kadane's algorithm
 */
public class MaxSubArr {
    private int start, end;     // starting and ending days
    private int max, currentSum;       // max sum and current sum
    static int[] maxSumArray;   // array from start and end index

    public MaxSubArr() {
        start = 0;
        end = 0;
        max = 0;
    }

    /**
     * Finds the maximum possible sum of array a
     * using a brute force method.
     * @param a is the array we are finding the max sum of
     */
    public void bruteForce(int[] a) {
        for (int startingDay = 0; startingDay < a.length; startingDay++) {
            for (int currentDay = startingDay; currentDay < a.length; currentDay++) {
                currentSum += a[currentDay];
                if (currentSum > max) {
                    max = currentSum;
                    start = startingDay;
                    end = currentDay;
                }
            }
            currentSum = 0;
        }
        copyArray(a);
    }

    /**
     * Finds the maximum possible sum of array a
     * using a divide and conquer method.
     * @param a is the array we are finding the max sum of
     */
    public void divideAndConquer(int[] a) {
        int high = a.length - 1;
        int low = 0;
        divide(a, low, high);
        copyArray(a);
    }

    /**
     * Divides the array recursively and then finds the max sum of each division
     * and the max sum of both division
     * @param a is the array we are dividing
     * @param low is the lower index
     * @param high is the upper index
     */
    private void divide(int[] a, int low, int high) {
        int mid;
        if (low < high) {
            mid = (high + low) / 2;
            divide(a, low, mid);
            divide(a, mid + 1, high);
            findMaxSum(a, low, mid, high);
            findMaxCrossSum(a, low, mid, high);
        }
    }

    /**
     * Finds the max sum of a divided portion of the array
     * @param a is the array we are finding the max sum of
     * @param low is the lower index
     * @param mid is the middle index of the lower and upper index
     * @param high is the upper index
     */
    private void findMaxSum(int[] a, int low, int mid, int high) {
        currentSum = 0;
        for (int currentDay = low; currentDay <= mid; currentDay++) {
            currentSum += a[currentDay];
            if (max < currentSum) {
                max = currentSum;
                start = low;
                end = currentDay;
            }
        }
        currentSum = 0;
        for (int currentDay = mid + 1; currentDay <= high; currentDay++) {
            currentSum += a[currentDay];
            if (max < currentSum) {
                max = currentSum;
                start = mid + 1;
                end = currentDay;
            }
        }
    }

    /**
     * Finds the max sum of crossing two divided sections
     * @param a the array we are finding the max sum of
     * @param low the lower index
     * @param mid the middle index of low and high
     * @param high the upper index
     */
    private void findMaxCrossSum(int[] a, int low, int mid, int high) {
        currentSum = 0;
        int tempStart = mid;
        int tempEnd = mid + 1;
        int tempMax = 0;
        for (int currentDay = mid; currentDay >= low; currentDay--) {
            currentSum += a[currentDay];
            if (tempMax < currentSum) {
                tempMax = currentSum;
                tempStart = currentDay;
            }
        }
        currentSum = tempMax;
        for (int currentDay = mid + 1; currentDay <= high; currentDay++) {
            currentSum += a[currentDay];
            if (tempMax < currentSum) {
                tempMax = currentSum;
                tempEnd = currentDay;
            }
        }
        if (max < tempMax) {
            max = tempMax;
            start = tempStart;
            end = tempEnd;
        }

    }

    /**
     * Finds the maximum possible sum of array a
     * @param a the array we are finding the max sum of
     */
    public void kadane(int[] a) {
        int tempStart = 0;
        for (int currentDay = 0; currentDay < a.length; currentDay++) {
            currentSum += a[currentDay];
            if (currentSum < 0) {
                currentSum = 0;
                tempStart = currentDay + 1;
            }
            if (max < currentSum) {
                max = currentSum;
                start = tempStart;
                end = currentDay;
            }
        }
        copyArray(a);
    }

    /**
     * Copies the array to the maxSumArray with the starting and ending
     * days
     * @param a the array we are copying
     */
    private void copyArray(int[] a) {
        maxSumArray = new int[(end - start) + 1];
        int j = start;
        for (int i = 0; i < maxSumArray.length; i++) {
            maxSumArray[i] = a[j];
            j++;
        }
    }

    public int getMax() { return max;}
    public int getStart() { return start;}
    public int getEnd() {return end;}

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int j : maxSumArray) {
            string.append(j).append(" ");
        }
        string.append("\nStarting day: ").append(start);
        string.append("\nEnding day: ").append(end);
        string.append("\nMax = ").append(max);
        string.append("\n\n");
        return string.toString();
    }
}
