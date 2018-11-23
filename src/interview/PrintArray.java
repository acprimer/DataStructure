package interview;

import java.util.ArrayList;
import java.util.Arrays;

// 剑指Offer
public class PrintArray {

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] vis = new boolean[n][m];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = -1;
        int cnt = n * m;
        int current = 0;
        while (cnt-- > 0) {
            int tx = x + dir[current][0];
            int ty = y + dir[current][1];
            if (!(inBox(matrix, tx, ty) && !vis[tx][ty])) {
                current = (current + 1) % dir.length;
                cnt++;
                continue;
            }

            vis[tx][ty] = true;
            ans.add(matrix[tx][ty]);
            x = tx;
            y = ty;
        }
        return ans;
    }

    private boolean inBox(int[][] A, int x, int y) {
        return x >= 0 && x < A.length && y >= 0 && y < A[0].length;
    }

    // 1 2 3
    // 4 5 6
    // 7 8 9
    //  1  2  3  4
    //  5  6  7  8
    //  9 10 11 12
    // 13 14 15 16
    public static void main(String[] args) {
//        new PrintArray().print(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
    }
}
