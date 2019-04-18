package tower_of_solomon.entity;

import java.util.concurrent.CountDownLatch;

public class AsyncThread extends Thread {

    //region フィールド

    private CountDownLatch latch;
    private long second;

    //endregion

    //region コンストラクタ

    /**
     * コンストラクタ
     *
     * @param latch
     */
    public AsyncThread(CountDownLatch latch, long second) {
        this.latch = latch;
        this.second = second;
    }

    //endregion

    //region メソッド

    @Override
    public void run() {
        try {
            sleep(this.second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
    //endregion
}
