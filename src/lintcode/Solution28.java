package lintcode;

import java.util.Arrays;

public class Solution28 {
//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix.length <= 0 || matrix[0].length <= 0) {
//            return false;
//        }
//        int n = matrix.length;
//        int m = matrix[0].length;
//
//    }
//
//    private int searchVertical(int[][] matrix, int target) {
//
//    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        int n = heights.length + 1;
        heights = Arrays.copyOf(heights, n);
        int[] stack = new int[n];
        int top = -1;
        int ans = 0;
        stack[++top] = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && heights[i] <= stack[top]) {
                ans = Math.max(ans, stack[top] * (i - stack[top]));
                top--;
            }
            stack[++top] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution28().largestRectangleArea(
                new int[]{0, 1, 0, 1}));
    }
}
