package lintcode;

public class Solution458 {
    public int lastPosition(int[] A, int target) {
        int left = 0, right = A.length;
        while (right - left > 1) {
            int mid = (left + right) >>> 1;
            if (A[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (left < A.length && A[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution458().lastPosition(
                new int[]{1, 2, 2, 4, 5, 5},
                6
        ));
    }
}
