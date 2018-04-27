package cn.longrise.www.androidprogress.activity.widgetActivity.buttonActivity;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.longrise.www.androidprogress.R;

/**
 * 下面中的方法一：方法一和下面使用的方法均可以
 * checkBox和RadioButton设置是否选中均使用setCheck(boolean)
 * checkBox点击事件获取继承CompoundButton.OnCheckedChangeListener
 * RadioButton点击事件获取继承RadioGroup.OnCheckedChangeListener
 */

public class SimpleCheckBoxAndRadioButton extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener/*,CompoundButton.OnCheckedChangeListener*/ {
    private CheckBox mcboxFooterBall;
    private CheckBox mcboxBasketBall;
    private CheckBox mcboxPingPangBall;
    private RadioGroup mrgButton;

    private ArrayList<String> mCheckList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_check_box);
        initView();
        initClick();
        initData();
    }

    private void initData() {
        mCheckList = new ArrayList<>();
    }

    private void initClick() {
        mcboxFooterBall.setOnCheckedChangeListener(mListener);
        mcboxBasketBall.setOnCheckedChangeListener(mListener);
        mcboxPingPangBall.setOnCheckedChangeListener(mListener);
        mrgButton.setOnCheckedChangeListener(this);
        // 使用的方法一
//        mcboxFooterBall.setOnCheckedChangeListener(this);
//        mcboxBasketBall.setOnCheckedChangeListener(this);
//        mcboxPingPangBall.setOnCheckedChangeListener(this);
    }

    private void initView() {
        mcboxFooterBall = (CheckBox) findViewById(R.id.cbox_football);
        mcboxBasketBall = (CheckBox) findViewById(R.id.cbox_basketball);
        mcboxPingPangBall = (CheckBox) findViewById(R.id.cbox_pingpangball);
        mrgButton = (RadioGroup) findViewById(R.id.rg_button);
    }

    private CompoundButton.OnCheckedChangeListener mListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (!mCheckList.contains(String.valueOf(buttonView.getText()))) {
                    mCheckList.add(String.valueOf(buttonView.getText()));
                }
            } else {
                if (mCheckList.contains(String.valueOf(buttonView.getText()))) {
                    mCheckList.remove(String.valueOf(buttonView.getText()));
                }
            }
            if (mCheckList.size() == 0) {
                Toast.makeText(SimpleCheckBoxAndRadioButton.this, "未选择", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SimpleCheckBoxAndRadioButton.this, mCheckList.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Toast.makeText(this, "第" + checkedId + "被选中", Toast.LENGTH_SHORT).show();
    }


    // 使用的方法一
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        // 当点击某个选项时，会出现这个选项的Id或Text(如：足球。以及这个Id当前的是否选择isChecked)
//        CharSequence text = buttonView.getText();
//        int id = buttonView.getId();
//    }
}
