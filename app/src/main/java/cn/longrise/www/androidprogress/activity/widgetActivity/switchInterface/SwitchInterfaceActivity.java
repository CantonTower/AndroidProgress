package cn.longrise.www.androidprogress.activity.widgetActivity.switchInterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class SwitchInterfaceActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_interface);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnViewFlipper.setOnClickListener(this);
    }

    private void initView() {
        mbtnViewFlipper = (Button) findViewById(R.id.btn_view_flipper);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnViewFlipper) {
            startActivity(new Intent(this, ViewFlipperActivity.class));
        }
    }
}
