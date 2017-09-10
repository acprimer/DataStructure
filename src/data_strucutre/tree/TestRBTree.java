package data_strucutre.tree;

/**
 * Created by yaodh on 2017/8/31.
 */
public class TestRBTree {
    public static void main(String[] args) {
        LLRBTree<String, String> tree = new LLRBTree<>();
        tree.put("S", "");
        tree.put("E", "");
        tree.put("A", "");
        tree.put("R", "");
        tree.put("C", "");
        tree.put("H", "");
        tree.put("X", "");
        tree.put("M", "");
        tree.put("P", "");
        tree.put("L", "");

        System.out.println(tree);
        System.out.println(1.0/0.9);
        System.out.println(1.0/0.8);
        System.out.println(1.0/0.7);
    }
}
