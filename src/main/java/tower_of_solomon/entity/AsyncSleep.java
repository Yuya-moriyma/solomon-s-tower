package tower_of_solomon.entity;

import android.os.AsyncTask;

import java.util.concurrent.CountDownLatch;

public class AsyncSleep extends AsyncTask<Void, Void, Void> {

    private CountDownLatch latch;

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(3000);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

    public void setLatch(CountDownLatch latch){
        this.latch = latch;
    }
}
