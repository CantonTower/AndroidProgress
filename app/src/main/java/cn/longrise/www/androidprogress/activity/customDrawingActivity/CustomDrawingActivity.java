package cn.longrise.www.androidprogress.activity.customDrawingActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class CustomDrawingActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtn_xml_custom_drawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawing);
        initView();
        initClick();
    }

    private void initClick() {
        mbtn_xml_custom_drawing.setOnClickListener(this);
    }

    private void initView() {
        mbtn_xml_custom_drawing = (Button) findViewById(R.id.btn_xml_custom_drawing);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtn_xml_custom_drawing) {
            startActivity(new Intent(this, XmlCustomDrawingActivity.class));
        }
    }
}
