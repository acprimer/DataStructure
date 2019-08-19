package interview;

import java.util.ArrayDeque;
import java.util.ArrayList;

// https://www.nowcoder.com/discuss/105677
public class TreeLevelCount {
    static class TreeNode {
        int x;
        TreeNode left, right;

        TreeNode(int x) {
            this.x = x;
        }
    }

    public static int countLevel(TreeNode root, int depth, int level) {
        if (root == null) return 0;
        if (depth == level) return 1;
        return countLevel(root.left, depth + 1, level)
                + countLevel(root.right, depth + 1, level);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(countLevel(root, 0, 2));

    }
}
