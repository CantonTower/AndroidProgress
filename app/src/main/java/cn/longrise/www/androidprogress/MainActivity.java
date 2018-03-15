package cn.longrise.www.androidprogress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.activity.WidgetActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    private void initView() {
        mbtnWidget = (Button) findViewById(R.id.btn_widget);
    }

    private void initClick() {
        mbtnWidget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnWidget) {
            startActivity(new Intent(this, WidgetActivity.class));
        }
    }
}
