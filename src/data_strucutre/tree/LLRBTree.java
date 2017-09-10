package data_strucutre.tree;

/**
 * Created by yaodh on 2017/8/31.
 */
public class LLRBTree<K extends Comparable<K>, V> {
    private Node root;

    public void put(K key, V val) {
        root = put(root, key, val);
        root.color = RED;
    }

    private Node put(Node h, K key, V val) {
        if (h == null) {
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);

        if (cmp < 0)      h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else              h.val = val;

        if (isRed(h.right) && !isRed(h.left))     h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))  h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))      flipColors(h);

        return h;
    }

    // rotate left
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.cnt = h.cnt;
        h.cnt = size(h.left) + size(h.right) + 1;
        return x;
    }

    // rotate right
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.cnt = h.cnt;
        h.cnt = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip colors
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.cnt;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "root = null";
        }
        return root.toString();
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V val;
        Node left, right;
        int cnt;
        boolean color;

        Node(K key, V val, int cnt, boolean color) {
            this.key = key;
            this.val = val;
            this.cnt = cnt;
            this.color = color;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }
}
