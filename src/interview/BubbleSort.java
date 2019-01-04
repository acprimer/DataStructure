package interview;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        sort.bubbleSort(A);
        System.out.println(Arrays.toString(A));
    }

    private void bubbleSort(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (A[j] > A[j + 1]) swap(A, j, j + 1);
            }
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
