package cn.longrise.www.androidprogress.activity.widgetActivity.barActivity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.longrise.www.androidprogress.R;

public class OriginalBarActivity extends AppCompatActivity {
    private ProgressBar mpbOriginalBar;
    private TextView mtvOriginalBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_bar);
        initView();
        initThread();
    }

    private void initThread() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i = 1;i <= 100;i ++ ) {
                    SystemClock.sleep(100);
                    final int j = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mpbOriginalBar.setProgress(j);
                            mtvOriginalBar.setText(String.valueOf(j + "%"));
                        }
                    });
                }
            }
        }.start();
    }

    private void initView() {
        mpbOriginalBar = (ProgressBar) findViewById(R.id.pb_original);
        mtvOriginalBar = (TextView) findViewById(R.id.tv_progressBar_original);
    }
}
