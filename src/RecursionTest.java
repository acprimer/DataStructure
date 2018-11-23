/**
 * Created by yaodh on 2018/4/17.
 */
public class RecursionTest {
    /**
     * 测试递归调用多少次
     * 结果：2K~5K多次
     * @param d
     */
    private void dfs(int d) {
        System.out.println(d);
        dfs(d+1);
    }

    public static void main(String[] args) {
        new RecursionTest().dfs(0);
    }
}
