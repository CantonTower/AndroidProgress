package cn.longrise.www.androidprogress.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnDialog.setOnClickListener(this);
    }

    private void initView() {
        mbtnDialog = (Button) findViewById(R.id.btn_dialog);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnDialog) {
            startActivity(new Intent(this, DialogActivity.class));
        }
    }
}
