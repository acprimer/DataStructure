package interview;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        sort.quickSort(A, 0, A.length-1);
        System.out.println(Arrays.toString(A));
    }

    // [start, end]
    private void quickSort(int[] A, int start, int end) {
        if (end - start < 1) return;
        int idx = partition(A, start, end);
        quickSort(A, start, idx-1);
        quickSort(A, idx+1, end);
    }

    private int partition(int[] A, int start, int end) {
        int pivot = A[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (A[j] < pivot) {
                swap(A, ++i, j);
            }
        }
        swap(A, ++i, end);
        return i;
    }

    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
