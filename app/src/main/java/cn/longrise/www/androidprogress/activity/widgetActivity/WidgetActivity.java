package cn.longrise.www.androidprogress.activity.widgetActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;
import cn.longrise.www.androidprogress.activity.widgetActivity.barActivity.BarActivity;
import cn.longrise.www.androidprogress.activity.widgetActivity.buttonActivity.ButtonActivity;
import cn.longrise.www.androidprogress.activity.widgetActivity.dialogActivity.DialogActivity;
import cn.longrise.www.androidprogress.activity.widgetActivity.switchInterface.SwitchInterfaceActivity;

public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnDialog;
    private Button mbtnSwitchInterface;
    private Button mbtnButton;
    private Button mbtnBar;

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
        mbtnButton.setOnClickListener(this);
        mbtnBar.setOnClickListener(this);
    }

    private void initView() {
        mbtnDialog = (Button) findViewById(R.id.btn_dialog);
        mbtnSwitchInterface = (Button) findViewById(R.id.btn_switch_interface);
        mbtnButton = (Button) findViewById(R.id.btn_button);
        mbtnBar = (Button) findViewById(R.id.btn_bar);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnDialog) {
            startActivity(new Intent(this, DialogActivity.class));
        } else if (v == mbtnSwitchInterface) {
            startActivity(new Intent(this, SwitchInterfaceActivity.class));
        }else if(v == mbtnButton){
            startActivity(new Intent(this, ButtonActivity.class));
        } else if (v == mbtnBar) {
            startActivity(new Intent(this, BarActivity.class));
        }
    }
}
