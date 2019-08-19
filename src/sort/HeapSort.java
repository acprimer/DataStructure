package sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        sort.heapSort(A);
        System.out.println(Arrays.toString(A));
    }

    private void maxHeapify(int[] A, int size, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        if (left < size && A[left] > A[i]) {
            largest = left;
        }
        if (right < size && A[right] > A[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(A, i, largest);
            maxHeapify(A, size, largest);
        }
    }

    private void buildMaxHeap(int[] A) {
        for (int i = (A.length - 1) / 2; i >= 0; i--) {
            maxHeapify(A, A.length, i);
        }
    }

    private void heapSort(int[] A) {
        buildMaxHeap(A);
        for (int i = A.length - 1; i > 0; i--) {
            swap(A, 0, i);
            maxHeapify(A, i, 0);
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
