package cn.longrise.www.androidprogress.activity.widgetActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnDialog;
    private Button mbtnSwitchInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnDialog.setOnClickListener(this);
        mbtnSwitchInterface.setOnClickListener(this);
    }

    private void initView() {
        mbtnDialog = (Button) findViewById(R.id.btn_dialog);
        mbtnSwitchInterface = (Button) findViewById(R.id.btn_switch_interface);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnDialog) {
            startActivity(new Intent(this, DialogActivity.class));
        } else if (v == mbtnSwitchInterface) {
            startActivity(new Intent(this, SwitchInterfaceActivity.class));
        }
    }
}
