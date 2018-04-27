package cn.longrise.www.androidprogress.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;
import cn.longrise.www.androidprogress.activity.callSystemFunction.CallSystemFunctionActivity;
import cn.longrise.www.androidprogress.activity.customDrawingActivity.CustomDrawingActivity;
import cn.longrise.www.androidprogress.activity.databaseActivity.DataBaseActivity;
import cn.longrise.www.androidprogress.activity.permission.PermissionActivity;
import cn.longrise.www.androidprogress.activity.widgetActivity.WidgetActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnWidget;
    private Button mbtnCustomDrawing;
    private Button mbtnUseDatabase;
    private Button mbtnPermission;
    private Button mbtnCallSystemFunction;

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
        mbtnUseDatabase = (Button) findViewById(R.id.btn_use_database);
        mbtnPermission = (Button) findViewById(R.id.btn_permission);
        mbtnCallSystemFunction = (Button) findViewById(R.id.btn_call_system_function);
    }

    private void initClick() {
        mbtnWidget.setOnClickListener(this);
        mbtnCustomDrawing.setOnClickListener(this);
        mbtnUseDatabase.setOnClickListener(this);
        mbtnPermission.setOnClickListener(this);
        mbtnCallSystemFunction.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnWidget) {
            startActivity(new Intent(this, WidgetActivity.class));
        } else if (v == mbtnCustomDrawing) {
            startActivity(new Intent(this, CustomDrawingActivity.class));
        } else if (v == mbtnPermission) {
            startActivity(new Intent(this, PermissionActivity.class));
        } else if (v == mbtnUseDatabase) {
            startActivity(new Intent(this, DataBaseActivity.class));
        } else if (v == mbtnCallSystemFunction) {
            startActivity(new Intent(this, CallSystemFunctionActivity.class));
        }
    }

}
