package cn.longrise.www.androidprogress;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by Administrator on 2018-4-23.
 */

public class AndroidProgressApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void StrictMode() {
        // 此方法是当Android7.0时，防止出现FileUriExposedException，Android7.0不允许一项包含文件URI的intent离开应用
        // 可解决Android7.0时，调用照相机崩溃的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
