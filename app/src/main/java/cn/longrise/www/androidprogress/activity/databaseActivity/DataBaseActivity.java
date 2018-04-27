package cn.longrise.www.androidprogress.activity.databaseActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnSQLDataBase;
    private Button mbtnTraditionDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        initView();
        initClick();
    }

    private void initView() {
        mbtnSQLDataBase = (Button) findViewById(R.id.btn_sql_database);
        mbtnTraditionDataBase = (Button) findViewById(R.id.btn_tradition_database);
    }

    private void initClick() {
        mbtnSQLDataBase.setOnClickListener(this);
        mbtnTraditionDataBase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mbtnTraditionDataBase){

        } else if (v == mbtnSQLDataBase) {
            startActivity(new Intent(this, SQLDataBaseActivity.class));
        }
    }
}
