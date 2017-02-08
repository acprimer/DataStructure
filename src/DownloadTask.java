import java.util.concurrent.Executor;

/**
 * Created by yaodh on 2017/2/8.
 */
public class DownloadTask extends AsyncTask<String, Void, String> {

    public DownloadTask() {
    }

    @Override
    protected String doInBackground(String... params) {
        System.out.println(Thread.currentThread().getId() + " doInBackground " + params[0]);
        return "I'm returned from doInBackground.";
    }

    @Override
    protected void onPreExecute() {
        System.out.println(Thread.currentThread().getId() + " onPreExecute");
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(Thread.currentThread().getId() + " onPostExecute");
//        System.out.println(s);
    }
}
