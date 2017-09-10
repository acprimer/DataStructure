package data_strucutre.tree;

/**
 * Created by yaodh on 2017/9/10.
 */
public class BST<K extends Comparable<K>, V> {
    private Node root;

    public void insert(K key, V val) {
        Node p = null;
        Node x = root;
        while (x != null) {
            p = x;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.val = val;
                return;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        if (p == null) {
            root = new Node(key, val);
        } else {
            if (key.compareTo(p.key) < 0) {
                p.left = new Node(key, val, p);
            } else {
                p.right = new Node(key, val, p);
            }
        }
    }

    public V search(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    public void delete(K key) {
        Node z = getNode(key);
        Node y = z;
        if (z.left != null && z.right != null) y = successor(z);
        Node x = y.left != null ? y.left : y.right;
        if (x != null) x.parent = y.parent;

        if (y.parent == null) root = x;
        else if (y == y.parent.left) y.parent.left = x;
        else y.parent.right = x;

        if (y != z) z.val = y.val;
    }

    public Node getNode(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }


    public Node minimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Node maximum(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Node successor(Node x) {
        if (x.right != null) return minimum(x);
        Node p = x.parent;
        while (p != null && x == p.right) {
            x = p;
            p = p.parent;
        }
        return p;
    }

    public Node successor(K key) {
        return successor(getNode(key));
    }

    class Node {
        K key;
        V val;
        Node left, right, parent;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public Node(K key, V val, Node parent) {
            this.key = key;
            this.val = val;
            this.parent = parent;
        }

        public Node(K key, V val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void print() {
        if (root != null) {
            print(root);
            System.out.println();
        } else System.out.println("null");
    }

    public void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.print(x.val + " ");
        print(x.right);
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        bst.insert(12, 12);
        bst.insert(5, 5);
        bst.insert(2, 2);
        bst.insert(9, 9);
        bst.insert(18, 18);
        bst.insert(15, 15);
        bst.insert(17, 17);
        bst.insert(19, 19);
        bst.insert(13, 13);
        bst.insert(20, 20);
        bst.print();
//        bst.delete(13);
        bst.delete(19);
        bst.print();
    }
}
