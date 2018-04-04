package cn.longrise.www.androidprogress;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.activity.customDrawingActivity.CustomDrawingActivity;
import cn.longrise.www.androidprogress.activity.widgetActivity.WidgetActivity;
import cn.longrise.www.androidprogress.utils.FullScreenDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnWidget;
    private Button mbtnCustomDrawing;
    private ProgressDialog mDialog;
    private Handler mHandler;
    Thread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    private void initView() {
        mbtnWidget = (Button) findViewById(R.id.btn_widget);
        mbtnCustomDrawing = (Button) findViewById(R.id.btn_custom_drawing);
    }

    private void initClick() {
        mbtnWidget.setOnClickListener(this);
        mbtnCustomDrawing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnWidget) {
            startActivity(new Intent(this, WidgetActivity.class));
        } else if (v == mbtnCustomDrawing) {
            startActivity(new Intent(this, CustomDrawingActivity.class));
        }
    }

}
