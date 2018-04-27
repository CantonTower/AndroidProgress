package cn.longrise.www.androidprogress.activity.databaseActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.longrise.www.androidprogress.R;
import cn.longrise.www.androidprogress.data.SQLHelper;

/**
 * 此类中的代码，作为开发参考，在此项目中并不应用
 */

public class SQLDataBaseActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnSQLAdd;
    private SQLHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqldata_base);
        initView();
        initClick();
        getDBHelper();
    }

    private SQLHelper getDBHelper() {
        if (mDBHelper != null) {
            return mDBHelper;
        } else {
            mDBHelper = new SQLHelper(this);
            return mDBHelper;
        }
    }

    private void initView() {
        mbtnSQLAdd = (Button) findViewById(R.id.btn_sql_add);
    }

    private void initClick() {
        mbtnSQLAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnSQLAdd) {
            Toast.makeText(this, "代码作参考，并不实际使用", Toast.LENGTH_SHORT).show();
            //sqlAdd();
        }
    }

    private void sqlAdd() {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "张三");
        values.put("password", "123456");
        values.put("phone", "12345678");
        values.put("email", "1111111");
        db.insert("user", null, values);

        values.clear();
        values.put("name", "李四");
        values.put("password", "123456");
        values.put("phone", "12345678");
        values.put("email", "1111111");
        db.insert("user", null, values);
    }
}
