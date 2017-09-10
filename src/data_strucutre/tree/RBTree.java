package data_strucutre.tree;

/**
 * Created by yaodh on 2017/9/7.
 */
public class RBTree<K extends Comparable<K>, V> {
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
}
