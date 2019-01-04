package interview;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        InsertSort sort = new InsertSort();
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        sort.insertSort(A);
        System.out.println(Arrays.toString(A));
    }

    private void insertSort(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int temp = A[i];
            int j = i - 1;
            for (; j >= 0 && A[j] >= temp; j--) {
                A[j + 1] = A[j];
            }
            A[j+1] = temp;
        }
    }
}
