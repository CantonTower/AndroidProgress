package cn.longrise.www.androidprogress.activity.widgetActivity.buttonActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnSimpleCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnSimpleCheckBox.setOnClickListener(this);
    }

    private void initView() {
        mbtnSimpleCheckBox = (Button) findViewById(R.id.btn_simple_checkBox);
    }

    @Override
    public void onClick(View v) {
        if(v == mbtnSimpleCheckBox){
            startActivity(new Intent(this, SimpleCheckBoxAndRadioButton.class));
        }
    }
}
