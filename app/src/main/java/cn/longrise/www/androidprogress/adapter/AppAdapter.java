package cn.longrise.www.androidprogress.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.longrise.www.androidprogress.R;
import cn.longrise.www.androidprogress.bean.AppBean;

/**
 * Created by Administrator on 2018/5/6.
 */

public class AppAdapter extends BaseAdapter{
    private Context mContext;
    private List<AppBean> mList;

    public AppAdapter(Context mContext, List<AppBean> list) {
        this.mContext = mContext;
        this.mList = list;

//        mList = new ArrayList<>();
//        this.mContext = mContext;
//        for(int i = 0;i < list.size();i ++ ) {
//            mList.add(list.get(i));
//        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public AppBean getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshAdapter() {
        // 这一步直接等于即可，因为此处的mList和Activity中的list是指向同一地址的，
        // 因此这种操作既方便也更符合逻辑,由于操作的是同一对象，在Activity中的list
        // 改变后，直接在此类中notifyDataSetChanged()即可(由于Activity中没有
        // notifyDataSetChanged()方法，因此必须在Adapter中调用)，也就是不用写
        // mList = list语句。notifyDataSetChanged()会重新调用getCount,getItem和
        // getView等方法。
//        mList = list;
        notifyDataSetChanged();

        // 如果需要使用下面这种方式，则应该使Adapter中的list和Activity的list不指向同一对象，
        // 此类中的构造方法屏蔽的部分，就是使两个list得到相同的数据，但不指向同一对象
//        mList.clear();
//        mList.addAll(list);
//        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_list_view, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_app_name);
            holder.tvVersion = (TextView) convertView.findViewById(R.id.tv_app_version);
            holder.tvSize = (TextView) convertView.findViewById(R.id.tv_app_size);
            holder.ivThumbId = (ImageView) convertView.findViewById(R.id.iv_app_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AppBean appBean = getItem(position);
        holder.tvName.setText(appBean.getName());
        holder.tvVersion.setText(appBean.getVersion());
        // 下面appBean.getFileSize()获取的是int型，setText()括号中需要转化为String型
        holder.tvSize.setText(appBean.getFileSize() + "KB");
        holder.ivThumbId.setImageResource(appBean.getThumId());
        return convertView;
    }

    private class ViewHolder{
        TextView tvName;
        TextView tvVersion;
        TextView tvSize;
        ImageView ivThumbId;
    }
}
