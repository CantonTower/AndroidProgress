package cn.longrise.www.androidprogress.activity.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.Permission;

import cn.longrise.www.androidprogress.R;

/**
 * Build.VERSION.SDK_INT:当前手机系统版本
 * Build.VERSION_CODES.M:Android6.0(代号23)(M:棉花糖(Android6.0的另一种叫法))
 * PERMISSION_GRANTED:具有权限
 * PERMISSION_DENIED:不具有权限
 */

/**
 * https://www.jianshu.com/p/a37f4827079a
 */

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnUserCamera;

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initView();
        initClick();
    }

    private void initClick() {
        mbtnUserCamera.setOnClickListener(this);
    }

    private void initView() {
        mbtnUserCamera = (Button) findViewById(R.id.btn_user_camera);
    }

    private void getCameraPermission() {
        // 判断当前系统是否高于或等于6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 此时当前系统大于6.0，判断是否拥有打电话权限
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                // 具有权限，开始调取打电话
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 123456));
                startActivity(intent);
            } else {
                // 不具有权限，进行权限申请
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
            }
        } else {
            // 小于6.0系统，直接打电话
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 123456));
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnUserCamera) {
            getCameraPermission();
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 123456));
//            startActivity(intent);
//            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 123456));
//            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 已获取打电话权限，开始打电话
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 123456));
                startActivity(intent);
            } else {
                Toast.makeText(this, "未获取拨打电话权限，不能拨打电话", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
