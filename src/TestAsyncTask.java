/**
 * Created by yaodh on 2017/2/8.
 */
public class TestAsyncTask {
    public static void main(String[] args) {
        DownloadTask task1 = new DownloadTask();
        DownloadTask task2 = new DownloadTask();
        System.out.println(Thread.currentThread().getId());
        task1.execute("Hello");
//        task2.execute("world");
    }
}
