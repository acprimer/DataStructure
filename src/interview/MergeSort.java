package interview;

import java.util.Arrays;

public class MergeSort {
    private int[] B;
    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        int[] A = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        sort.mergeSort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));
    }

    private void mergeSort(int[] A, int start, int end) {
        if (B == null) B = new int[A.length];
        if (end - start < 1) return;
        int mid = (start + end) >>> 1;
        mergeSort(A, start, mid);
        mergeSort(A, mid + 1, end);
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (A[i] < A[j]) {
                B[k++] = A[i++];
            } else {
                B[k++] = A[j++];
            }
        }
        while (i <= mid) B[k++] = A[i++];
        while (j <= end) B[k++] = A[j++];
        System.arraycopy(B, 0, A, start, k);
    }
}
