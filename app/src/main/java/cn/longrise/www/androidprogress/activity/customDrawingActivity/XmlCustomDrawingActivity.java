package cn.longrise.www.androidprogress.activity.customDrawingActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class XmlCustomDrawingActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnRingDrawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_custom_drawing);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnRingDrawing.setOnClickListener(this);
    }

    private void initView() {
        mbtnRingDrawing = (Button) findViewById(R.id.btn_ring_drawing);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnRingDrawing) {
            startActivity(new Intent(this, RingActivity.class));
        }
    }
}
