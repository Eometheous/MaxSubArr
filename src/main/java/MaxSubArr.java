/**
 * MaxSubArr calculates the maximum possible sum of a given array
 * with three different algorithms, brute force, divide and conquer, and Kadane's algorithm
 */
public class MaxSubArr {
    private int start, end;
    private int max, sum;
    static int[] maxSumArray;

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
        start = 0;
        end = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                if (sum > max) {
                    max = sum;
                    start = i;
                    end = j;
                }
            }
            sum = 0;
        }
        copyArray(a);
    }

    /**
     * Finds the maximum possible sum of array a
     * using a divide and conquer method.
     * @param a is the array we are finding the max sum of
     */
    public void divideAndConquer(int[] a) {
        start = 0;
        end = 0;
        sum = 0;
        max = 0;
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
        int leftSum = 0, rightSum = 0;

        for (int i = low; i <= mid; i++) {
            leftSum += a[i];
            if (max < leftSum) {
                max = leftSum;
                start = low;
                end = i;
            }
        }

        for (int i = mid + 1; i <= high; i++) {
            rightSum += a[i];
            if (max < rightSum) {
                max = rightSum;
                start = mid + 1;
                end = i;
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
        int crossSum = 0;
        int tempStart = mid;
        int tempEnd = mid + 1;
        int tempMax = 0;
        for (int i = mid; i >= low; i--) {
            crossSum += a[i];
            if (tempMax < crossSum) {
                tempMax = crossSum;
                tempStart = i;
            }
        }
        crossSum = tempMax;
        for (int i = mid + 1; i <= high; i++) {
            crossSum += a[i];
            if (tempMax < crossSum) {
                tempMax = crossSum;
                tempEnd = i;
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
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum < 0) {
                sum = 0;
                tempStart = i + 1;
            }
            if (max < sum) {
                max = sum;
                start = tempStart;
                end = i;
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
