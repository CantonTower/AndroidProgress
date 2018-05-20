package cn.longrise.www.androidprogress.activity.H5AndWebPage;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.longrise.www.androidprogress.R;

public class H5AndWebPageActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnSimpleWebPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_and_web_page);
        initView();
        initClick();
    }

    private void initView() {
        mbtnSimpleWebPage = (Button) findViewById(R.id.btn_simple_webPage);
    }

    private void initClick() {
        mbtnSimpleWebPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnSimpleWebPage) {
            openSimpleWebPage();
        }
    }

    private void openSimpleWebPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }
}
