package cn.longrise.www.androidprogress.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.longrise.www.androidprogress.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnAlertDialog1;
    private Button mbtnAlertDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnAlertDialog1.setOnClickListener(this);
        mbtnAlertDialog2.setOnClickListener(this);
    }

    private void initView() {
        mbtnAlertDialog1 = (Button) findViewById(R.id.btn_alert_dialog1);
        mbtnAlertDialog2 = (Button) findViewById(R.id.btn_alert_dialog2);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnAlertDialog1) {
            ShowAlertDialog1();
        } else if (v == mbtnAlertDialog2) {
            ShowAlertDialog2();
        }
    }

    private void ShowAlertDialog2() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("喝酒调查")
                .setMessage("你喜欢喝酒吗?")
                .setPositiveButton("喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击喜欢", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击不喜欢", Toast.LENGTH_SHORT).show();
                    }
                }).setNeutralButton("一般般", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击一般般", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.show();
    }

    private void ShowAlertDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认退出吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "已点击确认", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "已点击取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
