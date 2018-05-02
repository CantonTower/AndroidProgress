package cn.longrise.www.androidprogress.activity.widgetActivity.dialogActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.longrise.www.androidprogress.R;
import cn.longrise.www.androidprogress.utils.FullScreenDialog;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 在使用时,如果设置了Message，则不能设置单选框和复选框
     */

    private Button mbtnAlertDialog1;
    private Button mbtnAlertDialog2;
    private Button mbtnCustomDialog;
    private Button mbtnAddEditTextAlertDialog;
    private Button mbtnMultiChoiceAlertDialog;
    private Button mbtnSingleChoiceAlertDialog;
    private Button mbtnItemAlertDialog;
    private Button mbtnShowFullScreenDialog;
    private Button mbtnMNProgressHUD;

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
        mbtnCustomDialog.setOnClickListener(this);
        mbtnAddEditTextAlertDialog.setOnClickListener(this);
        mbtnMultiChoiceAlertDialog.setOnClickListener(this);
        mbtnSingleChoiceAlertDialog.setOnClickListener(this);
        mbtnItemAlertDialog.setOnClickListener(this);
        mbtnShowFullScreenDialog.setOnClickListener(this);
        mbtnMNProgressHUD.setOnClickListener(this);
    }

    private void initView() {
        mbtnAlertDialog1 = (Button) findViewById(R.id.btn_alert_dialog1);
        mbtnAlertDialog2 = (Button) findViewById(R.id.btn_alert_dialog2);
        mbtnCustomDialog = (Button) findViewById(R.id.btn_custom_dialog);
        mbtnAddEditTextAlertDialog = (Button) findViewById(R.id.btn_add_editText_AlertDialog);
        mbtnMultiChoiceAlertDialog = (Button) findViewById(R.id.btn_multi_choice_alert_dialog);
        mbtnSingleChoiceAlertDialog = (Button) findViewById(R.id.btn_single_choice_alert_dialog);
        mbtnItemAlertDialog = (Button) findViewById(R.id.btn_item_alert_dialog);
        mbtnShowFullScreenDialog = (Button) findViewById(R.id.btn_show_full_screen_dialog);
        mbtnMNProgressHUD = (Button) findViewById(R.id.btn_mn_progress_hud);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnAlertDialog1) {
            ShowAlertDialog1();
        } else if (v == mbtnAlertDialog2) {
            ShowAlertDialog2();
        } else if (v == mbtnCustomDialog) {
            showCustomDialog();
        } else if (v == mbtnAddEditTextAlertDialog) {
            showAddEditTextAlertDialog();
        } else if (v == mbtnMultiChoiceAlertDialog) {
            showMultiChoiceAlertDialog();
        } else if (v == mbtnSingleChoiceAlertDialog) {
            showSingleChoiceAlertDialog();
        } else if (v == mbtnItemAlertDialog) {
            showItemAlertDialog();
        } else if (v == mbtnShowFullScreenDialog) {
            showFullScreenDialog();
        } else if (v == mbtnMNProgressHUD) {
            startActivity(new Intent(this, MNProgressHUD.class));
        }
    }

    private void showFullScreenDialog() {
        FullScreenDialog dialog = new FullScreenDialog(this, R.layout.widget_full_screen_dialog);
        dialog.show();
    }

    private void showItemAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("列表框")
                .setItems(new String[]{"选项1", "选项2", "选项3"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击第" + (which + 1) + "个", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    private void showSingleChoiceAlertDialog() {
        final String[] strArr = new String[]{"公", "母"};
        new AlertDialog.Builder(this)
                .setTitle("单选框")
                .setSingleChoiceItems(strArr, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "你是" + strArr[which] + "的", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    private void showMultiChoiceAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("复选框")
                .setMultiChoiceItems(new String[]{"Item1", "Item2"}, new boolean[]{true, false}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(DialogActivity.this, "第" + (which + 1) + "个选项被选中", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DialogActivity.this, "第" + (which + 1) + "个选项被取消", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "已点击取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    private void showAddEditTextAlertDialog() {
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("输入文本")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "输入的文本是：" + editText.getText(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.widget_custom_dialog, null);
        dialog.setContentView(inflate);
        Button btnWidgetCustomDialog = (Button) inflate.findViewById(R.id.btn_widget_custom_dialog);
        btnWidgetCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogActivity.this, "已点击自定义的dialog的按钮", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
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
