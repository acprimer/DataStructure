package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] A = new int[]{2, 8, 7, 1, 3, 5, 6, 4};
        QuickSort sort = new QuickSort();
        sort.quickSort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));

        sort.arrange(new int[]{10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35});
    }

    private void quickSort(int[] A, int left, int right) {
        if (left >= right) return;
        int r = partition(A, left, right);
        quickSort(A, left, r - 1);
        quickSort(A, r + 1, right);
    }

    private int partition(int[] A, int left, int right) {
        int x = A[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (A[j] <= x) swap(A, ++i, j);
        }
        swap(A, ++i, right);
        return i;
    }

    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    private void arrange(int[] A) {
        // 10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35
        int i = -1;
        for (int j = 0; j < A.length; j++) {
            if (A[j] < 0) {
                swap(A, ++i, j);
            }
        }
        System.out.println(Arrays.toString(A));
    }
}
