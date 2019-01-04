package interview;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        sort.selectionSort(A);
        System.out.println(Arrays.toString(A));
    }

    private void selectionSort(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            int min = A[i];
            for (int j = i + 1; j < n; j++) {
                if (A[j] < min) {
                    min = A[minIndex = j];
                }
            }
            swap(A, i, minIndex);
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
