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
