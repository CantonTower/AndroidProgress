package cn.longrise.www.androidprogress.activity.ui;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.longrise.www.androidprogress.adapter.AppAdapter;
import cn.longrise.www.androidprogress.bean.AppBean;
import cn.longrise.www.androidprogress.R;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener{
    private ListView mListView;
    private ArrayList<AppBean> mAppList;
    private AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initData();
        initView();
    }

    private void initData() {
        int[] thumbs = {
                R.mipmap.tencent_safe, R.mipmap.baidu_safe, R.mipmap.kingsoft_safe,
                R.mipmap.an_doctor, R.mipmap.ruixing_safe, R.mipmap.wangqin_safe,
                R.mipmap.lost_safe, R.mipmap.bigspider_safe, R.mipmap.avg_safe,
                R.mipmap.lbe_safe, R.mipmap.mobile_an_safe};
        Resources res = getResources();
        String[] nameArr = res.getStringArray(R.array.names);
        String[] versionArr = res.getStringArray(R.array.version);
        int[] fileSizeArr = res.getIntArray(R.array.file_size);
        mAppList = new ArrayList<>();
        for(int i = 0;i < nameArr.length;i ++ ) {
            mAppList.add(new AppBean(nameArr[i], thumbs[i], versionArr[i], fileSizeArr[i]));
        }
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new AppAdapter(this, mAppList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        AppBean bean = (AppBean) adapterView.getItemAtPosition(position);
        Toast.makeText(this, bean.getName() + "已点击", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        // 长按删除某一项
        mAppList.remove(position);
        mAdapter.refreshAdapter();
        // 返回true表示将点击事件拦截，此时将不会产生点击事件
        return true;
    }
}
