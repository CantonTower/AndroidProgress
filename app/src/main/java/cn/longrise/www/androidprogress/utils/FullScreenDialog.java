package cn.longrise.www.androidprogress.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import cn.longrise.www.androidprogress.R;

/**
 * Created by Administrator on 2018-3-23.
 */

public class FullScreenDialog extends Dialog {
    private Context mContext;

    public FullScreenDialog(@NonNull Context context, int res) {
        // 此处修改了super的参数，因此此类使用的是dialog的第二个构造方法
        super(context, R.style.full_screen);
        mContext = context;
        initDialog(res);
    }

    private void initDialog(int res) {
        View inflate = ((Activity) mContext).getLayoutInflater().inflate(res, null);
        this.setContentView(inflate);
    }
}
