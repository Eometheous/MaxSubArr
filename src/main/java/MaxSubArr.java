public class MaxSubArr {
    private int start, end;
    private int max, sum;
    static int[] maxSumArray;

    public MaxSubArr() {
        start = 0;
        end = 0;
        max = 0;
    }
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

    private void findMaxSum(int[] a, int low, int mid, int high) {
        int leftSum = 0, rightSum = 0;

        for (int i = low; i < mid; i++) {
            leftSum += a[i];
            if (max < leftSum) {
                max = leftSum;
                end = i;
            }
        }

        for (int i = mid + 1; i < high; i++) {
            rightSum += a[i];
            if (max < rightSum) {
                start = mid + 1;
                end = i;
            }
        }
    }

    private void findMaxCrossSum(int[] a, int low, int mid, int high) {
        int crossSum = 0;
        for (int i = mid; i > low; i--) {
            crossSum += a[i];
            if (max < crossSum) {
                max = crossSum;
                start = i;
            }
        }

        for (int i = mid + 1; i < high; i++) {
            crossSum += a[i];
            if (max < crossSum) {
                max = crossSum;
                end = i;
            }
        }
    }


    public void kadane(int[] a) {
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
            if (max < sum) {
                max = sum;
                end = i;
            }
        }
        copyArray(a);
    }

    private void copyArray(int[] a) {
        maxSumArray = new int[(end - start) + 1];
        int j = start;
        for (int i = 0; i < maxSumArray.length; i++) {
            maxSumArray[i] = a[j];
            j++;
        }
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int j : maxSumArray) {
            string.append(j).append(" ");
        }
        string.append("\nStarting day: ").append(start);
        string.append("\nEnding day: ").append(end);
        string.append("\nMax = ").append(max);
        return string.toString();
    }
}
