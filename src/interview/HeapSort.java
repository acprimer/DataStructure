package interview;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        int[] A = new int[]{4,1,3,2,16,9,10,14,8,7};
        sort.heapSort(A);
        System.out.println(Arrays.toString(A));
    }

    private void heapSort(int[] A) {
        buildHeap(A);
        for (int i = 0; i < A.length; i++) {
            swap(A, 0, A.length - i - 1);
            adjust(A, 0, A.length - i - 1);
        }
    }

    private void buildHeap(int[] A) {
        for (int i = (A.length - 1) / 2; i >= 0; i--) {
            adjust(A, i, A.length);
        }
    }

    private void adjust(int[] A, int id, int size) {
        int large = id;
        int left = id * 2 + 1;
        int right = id * 2 + 2;
        if (left < size && A[left] > A[large]) {
            large = left;
        }
        if (right < size && A[right] > A[large]) {
            large = right;
        }
        if (large != id) {
            swap(A, large, id);
            adjust(A, large, size);
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
