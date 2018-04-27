package cn.longrise.www.androidprogress.activity.widgetActivity.barActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class BarActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnOriginalBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        initView();
        initClick();
    }

    private void initView() {
        mbtnOriginalBar = (Button) findViewById(R.id.btn_original_bar);
    }

    private void initClick() {
        mbtnOriginalBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnOriginalBar) {
            startActivity(new Intent(this, OriginalBarActivity.class));
        }
    }
}
