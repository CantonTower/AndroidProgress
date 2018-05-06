package cn.longrise.www.androidprogress.activity.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class UIActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnListView.setOnClickListener(this);
    }

    private void initView() {
        mbtnListView = (Button) findViewById(R.id.btn_listView);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnListView) {
            startActivity(new Intent(this, ListViewActivity.class));
        }
    }
}
