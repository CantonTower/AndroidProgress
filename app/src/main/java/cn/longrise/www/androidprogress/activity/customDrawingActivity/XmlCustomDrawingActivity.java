package cn.longrise.www.androidprogress.activity.customDrawingActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class XmlCustomDrawingActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnRingDrawing;
    private Button mbtnRectangleDrawing;
    private Button mbtnOvalDrawing;
    private Button mbtnLineDrawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_custom_drawing);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnRingDrawing.setOnClickListener(this);
        mbtnRectangleDrawing.setOnClickListener(this);
        mbtnOvalDrawing.setOnClickListener(this);
        mbtnLineDrawing.setOnClickListener(this);
    }

    private void initView() {
        mbtnRingDrawing = (Button) findViewById(R.id.btn_ring_drawing);
        mbtnRectangleDrawing = (Button) findViewById(R.id.btn_rectangle_drawing);
        mbtnOvalDrawing = (Button) findViewById(R.id.btn_oval_drawing);
        mbtnLineDrawing = (Button) findViewById(R.id.btn_line_drawing);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnRingDrawing) {
            startActivity(new Intent(this, RingActivity.class));
        } else if (v == mbtnRectangleDrawing) {
            startActivity(new Intent(this, RectangleActivity.class));
        } else if (v == mbtnOvalDrawing) {
            startActivity(new Intent(this, OvalActivity.class));
        } else if (v == mbtnLineDrawing) {
            startActivity(new Intent(this, LineActivity.class));
        }
    }
}
