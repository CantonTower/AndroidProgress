package cn.longrise.www.androidprogress.activity.widgetActivity;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import cn.longrise.www.androidprogress.R;
import cn.longrise.www.androidprogress.utils.FromAndOutAnimation;

/**
 *  xml代码中屏蔽的部分是关于ViewFlipper的简单使用
 *  关于ViewFlipper的使用方法，详见下面网址：
 *  https://blog.csdn.net/u013378580/article/details/52038255
 */

/**
 *  在使用此类时，应当特别注意关于Theme的使用。
 *  Theme之间的继承和Activity与AppCompatActivity的区别
 *  application中的android:theme="@style/AppTheme"继承哪一个，则相应的各activity则需要继承哪一个
 *  Theme.AppCompat.Light.DarkActionBar(此类是AppCompatActivity，不可设置全屏)
 *  Theme.AppCompat.Light.NoActionBar
 *  需要自定义继承NoActionBar的Theme,或者修改application中的theme,或者继承activity
 */

/**
 *  由于在操作图片时难度较高，此类暂时屏蔽手势控制
 */

public class ViewFlipperActivity extends AppCompatActivity {
    private ViewFlipper mViewFlipper;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        initView();
        initFlipperTouch();
    }

    private void initFlipperTouch() {
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mDetector.onTouchEvent(event);
                //当有触屏事件作用于ViewFlipper时，把这个触屏事件返回给 GestureDetector手势识别器处理
                //如果想要把整个屏幕上的触屏事件都返回给 GestureDetector手势识别器处理，
                //而不单单是在ViewFlipper上发生触屏事件时就给 GestureDetector处理，
                //那么我们就不必为flipper单独设置onTouchListener（）触摸监听器了，就只需要重写Activity中的onTouchEvent()方法，
                //在该方法中讲整个屏幕的触摸事件返回给手势识别器处理就行
            }
        });

        mDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            /*
             * 用户按下触摸屏、快速移动后松开即触发这个事件
             * e1：第1个ACTION_DOWN MotionEvent
             * e2：最后一个ACTION_MOVE MotionEvent
             * velocityX：X轴上的移动速度，像素/秒
             * velocityY：Y轴上的移动速度，像素/秒
             * 触发条件 ：
             * X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
             */
            @Override
            public boolean onDown(MotionEvent e) {
                //手指按下屏幕时触发
//                mViewFlipper.stopFlipping();//当触摸到ViewFlipper时，就让它停止自动滑动，如果要触摸到整个屏幕的任意一处就让它停止滑动，就按上面那个方法，
//                // 不用设置ViewFlipper的触摸事件监听了
//                new Handler().postDelayed(new Runnable() {//在当前线程（也即主线程中）开启一个消息处理器，并在3秒后发送flipper.startFlipping();
//                    // 这个消息给主线程，再让它自动滑动，从而来更新UI
//                    @Override
//                    public void run() {
//                        mViewFlipper.startFlipping();//3秒后执行，让它又开始滑动
//                    }
//                }, 3000);
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                final float FLING_MIN_DISTANCE = 100;//最小滑动像素
//                final float FLING_MIN_VELOCITY = 150;//最小滑动速度
//                if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE &&
//                        Math.abs(velocityX) > FLING_MIN_VELOCITY){//X轴上的移动速度去绝对值进行比较
//                    //判断x轴坐标如果第一次按下时的坐标减去第二次离开屏幕时的坐标大于我们设置的位移，因为一个控件的原点是在左上角，就说明此时是向左滑动的
//                    //设置View进入屏幕时候使用的动画
//                    mViewFlipper.setInAnimation(FromAndOutAnimation.inFromRightAnimation());
//                    //设置View退出屏幕时候使用的动画
//                    mViewFlipper.setOutAnimation(FromAndOutAnimation.outToLeftAnimation());
//                    mViewFlipper.showNext();//显示下一个视图
//
//                }else if(e2.getX() - e1.getX() > FLING_MIN_DISTANCE &&
//                        Math.abs(velocityX) > FLING_MIN_VELOCITY){
//                    //判断x轴坐标如果第二次离开屏幕时的坐标减去第一次按下时的坐标大于我们设置的位移，因为一个控件的原点是在左上角，就说明此时是向右滑动的
//                    mViewFlipper.setInAnimation(FromAndOutAnimation.inFromLeftAnimation());
//                    mViewFlipper.setOutAnimation(FromAndOutAnimation.outToRightAnimation());
//                    mViewFlipper.showPrevious();//显示上一个视图
//                }
                return true;
            }
        });
    }

    private void initView() {
        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        addImage();
        mViewFlipper.setFlipInterval(2000);
        mViewFlipper.setInAnimation(FromAndOutAnimation.inFromRightAnimation());
        mViewFlipper.setOutAnimation(FromAndOutAnimation.outToLeftAnimation());
        mViewFlipper.startFlipping();
    }

    private void addImage() {
        View inflate1 = getLayoutInflater().inflate(R.layout.view_flipper_image, null);
        ImageView ivShow1 = (ImageView) inflate1.findViewById(R.id.iv_view_flipper_image);
        ivShow1.setImageResource(R.mipmap.game_feizi);
        mViewFlipper.addView(inflate1);

        View inflate2 = getLayoutInflater().inflate(R.layout.view_flipper_image, null);
        ImageView ivShow2 = (ImageView) inflate2.findViewById(R.id.iv_view_flipper_image);
        ivShow2.setImageResource(R.mipmap.game_jiujie);
        mViewFlipper.addView(inflate2);

        View inflate3 = getLayoutInflater().inflate(R.layout.view_flipper_image, null);
        ImageView ivShow3 = (ImageView) inflate3.findViewById(R.id.iv_view_flipper_image);
        ivShow3.setImageResource(R.mipmap.game_langtaosha);
        mViewFlipper.addView(inflate3);
    }
}
