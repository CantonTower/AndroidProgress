package cn.longrise.www.androidprogress.activity.callSystemFunction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import cn.longrise.www.androidprogress.R;

/**
 * ******************
 *  电话部分
 * ******************
 *
 *  在开发中应尽量去使用启动拨号键盘，避免使用直接拨打电话
 */

/**
 * ****************************
 * 照相机部分
 * ****************************
 *
 * 存储问题：
 * 分为三种：
 * 1.App的私有路径部分。比如：SharedPreference和数据库就存储在这里，即/data/data/com.example.story/files
 * 2.sd卡的App私有部分。比如：App存储图片,视频等，放在此处较好，即/mnt/sdcard/Android/data/com.example.story/files
 * 3.sd卡公共文件部分。比如：公有的文件，图片，视频，即/mnt/sdcard/Pictures
 * 其中2和3需要获取读写权限，1不需要。
 * 其中1和2在卸载App后，会自动删除，3不会自动删除。
 * 其中1需要手机获取root权限后，才可以在手机文件管理器中访问。2和3不需要获取root权限，即可访问。
 * getFilesDir();--获取1的方法。/data/data/com.example.story/files
 * getExternalFilesDir(null);--获取2的方法。/mnt/sdcard/Android/data/com.example.story/files
 * getExternalFilesDir("Caches");--获取2的方法。/mnt/sdcard/Android/data/com.example.story/files/Caches
 * Environment.getExternalStorageDirectory();--获取3的方法。/mnt/sdcard
 * Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);--获取3的方法。/mnt/sdcard/Pictures
 * 注意：sd卡指的是手机自带的存储，我们通常说的sd卡内存卡指的是可扩展sd卡和便携移动sd卡
 * 在开发中应该将SharedPreference和数据库放在1中。图片视频等文件放在2和3中。
 *
 * 下面两行代码，dir表示的是一个引用，并不是一个对象。file则表示一个对象。
 * File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
 * File file = new File(dir, "text.xml");
 */

public class CallSystemFunctionActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mbtnCall;
    private Button mbtnDial;
    private Button mbtnCallCamera;
    private ImageView mivCameraPhoto;
    private Uri mUri;

    private static final int CALL_PHONE_PERMISSION = 1;
    private static final int DIAL_PERMISSION = 2;
    private static final int CALL_CAMERA_FOR_RESULT = 3;
    private static final int CAMERA_PERMISSION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_system_function);
        initView();
        initClick();
    }

    private void initView() {
        mbtnCall = (Button) findViewById(R.id.btn_call);
        mbtnDial = (Button) findViewById(R.id.btn_dial);
        mbtnCallCamera = (Button) findViewById(R.id.btn_call_camera);
        mivCameraPhoto = (ImageView) findViewById(R.id.iv_camera_photo);
    }

    private void initClick() {
        mbtnCall.setOnClickListener(this);
        mbtnDial.setOnClickListener(this);
        mbtnCallCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mbtnCall) {
            callPhone();
        } else if (v == mbtnDial) {
            dial();
        } else if (v == mbtnCallCamera) {
            callCamera();
        }
    }

    private void callCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startTakePhoto();
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
            }
        } else {
            startTakePhoto();
        }
    }

    private void startTakePhoto(){
        // 判断sd卡是否处于可读写状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 以时间戳的方式命名照片，此处是获取时间戳
            String format = SimpleDateFormat.getDateTimeInstance().format(System.currentTimeMillis());
            File path = getExternalFilesDir("photos");
            File file = new File(path, format + ".jpg");
            mUri = Uri.fromFile(file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // 下面一行代码，如果添加，则需要再拍照后从uri中获取图片。
            // 如果删除此行代码，则从onActivityResult中的data中获取到一个缩略图
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
            startActivityForResult(intent, CALL_CAMERA_FOR_RESULT);
        } else {
            Toast.makeText(this, "sd卡不能使用", Toast.LENGTH_SHORT).show();
        }
    }

    private void dial() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 123456));
                startActivity(intent);
            } else {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, DIAL_PERMISSION);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 123456));
            startActivity(intent);
        }
    }

    private void callPhone() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 123456));
                startActivity(intent);
            } else {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 123456));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_PHONE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 123456));
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("main", "CallSystemFunctionActivity.onRequestPermissionsResult.Exception=" + e.toString());
                    }
                } else {
                    Toast.makeText(this, "未获取拨打电话权限，不能拨打电话", Toast.LENGTH_SHORT).show();
                }
                break;
            case DIAL_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 123456));
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "未获取拨打电话权限，不能拨打电话", Toast.LENGTH_SHORT).show();
                } 
                break;
            case CAMERA_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startTakePhoto();
                } else {
                    Toast.makeText(this, "未获取照相机拍照权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CALL_CAMERA_FOR_RESULT) {
            // 下面两行屏蔽的代码，当intent不指定uri时(删除此代码：intent.putExtra(MediaStore.EXTRA_OUTPUT, uri))，
            // 使用下面方式将返回一个缩略图，当使用指定uri(图片地址),使用下面try—catch，则会得到图片的原图
//            Bitmap bitmap = data.getParcelableExtra("data");
//            mivCameraPhoto.setImageBitmap(bitmap);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
                mivCameraPhoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e("main", "CallSystemFunctionActivity.onActivityResult.Exception=" + e.toString());
            }
        }
    }
}
