package cn.longrise.www.androidprogress.utils;

import android.content.Context;

/**
 * Created by Administrator on 2018-4-25.
 */

public class PublicUtils {

    public static int dp2px(Context context,float dipValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
