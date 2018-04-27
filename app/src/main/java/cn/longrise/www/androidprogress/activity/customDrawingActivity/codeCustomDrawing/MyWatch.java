package cn.longrise.www.androidprogress.activity.customDrawingActivity.codeCustomDrawing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

import cn.longrise.www.androidprogress.R;
import cn.longrise.www.androidprogress.utils.PublicUtils;

/**
 * Created by Administrator on 2018-4-25.
 * <p>
 * onSizeChanged()是在onMeasure()方法运行完成后(即测量完成后)执行，从这个方法里面可以获得最终控件获取的宽高
 * 自定义View的onDraw()方法有时运行一次，有时运行两次。这是合理的，因此，对于初始化Paint的位置一定要注意，即使onDraw()
 * ~~重复绘制，也应该不要影响UI
 * <p>
 * canvas.save()方法是保存当前canvas的状态，canvas.restore()方法是还原刚才save()保存的状态。
 * 当canvas要进行平移，旋转等操作时，为了使这样的操作不会对后续绘制产生影响，因此使用save和restore方法，
 * 这两个方法的使用是成双成对的
 * <p>
 * canvas.Translate()方法的参数是针对当前位置的坐标，而不是空间左上角的坐标
 *
 * postInvalidateDelayed(1000);方法是延迟刷新UI,这个方法会导致onDraw方法每隔1秒运行一次
 */


/**
 *  **************
 *  绘制圆形
 *  **************
 *
 *  第一种方法：先将画笔的位置从控件的左上角转移至中心点，再进行画圆
 *  第二种方法：当画笔还处于控件的左上角时，设置圆的中心点，进行画圆
 */

public class MyWatch extends View {
    private int mRadios; // 外圆半径
    private int mPadding; // 边距(表与布局边框的距离)
    private int mTextSize; // 文字大小
    private int mHourPointWidth; // 时针宽度
    private int mMinutePointWidth; // 分针宽度
    private int mSecondPointWidth; // 秒针宽度
    private int mPointRadius; // 指针圆角
    private int mPointEndLength; // 指针末尾的长度
    private int mColorLong; // 长线的颜色
    private int mColorShort; // 短线的颜色
    private int mHourPointColor; // 时针的颜色
    private int mMinutePointColor; // 分针的颜色
    private int mSecondPointColor; // 分针的颜色

    private Paint mPaint; // 画笔
    private Context mContext;

    private int mHour;
    private int mMinute;
    private int mSecond;
    private int mAngleHour;
    private int mAngleMinute;
    private int mAngleSecond;

    public MyWatch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyWatch);
        mRadios = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchRadios, 0);
        mPadding = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchPadding, 0);
        mTextSize = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchTextSize, 0);
        mHourPointWidth = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchHourPointWidth, 0);
        mMinutePointWidth = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchMinutePointWidth, 0);
        mSecondPointWidth = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchSecondPointWidth, 0);
        mPointRadius = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchPointRadios, 0);
        mPointEndLength = array.getDimensionPixelSize(R.styleable.MyWatch_myWatchPointEndLength, 0);
        mColorLong = array.getColor(R.styleable.MyWatch_myWatchColorLong, 0);
        mColorShort = array.getColor(R.styleable.MyWatch_myWatchColorShort, 0);
        mHourPointColor = array.getColor(R.styleable.MyWatch_myWatchHourPointColor, 0);
        mMinutePointColor = array.getColor(R.styleable.MyWatch_myWatchMinutePointColor, 0);
        mSecondPointColor = array.getColor(R.styleable.MyWatch_myWatchSecondPointColor, 0);
        initPaint();
    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mSecond = calendar.get(Calendar.SECOND);
        mAngleHour = (mHour % 12) * (360 / 12) + (int) (mMinute * 0.5);
        mAngleMinute = mMinute * (360 / 60);
        mAngleSecond = mSecond * (360 / 60);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 1000;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadios = (Math.min(w, h) / 2) - mPadding;
        // 尾部指针默认为半径的六分之一
        mPointEndLength = mRadios / 6;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initTime();
        paintCircle(canvas);
        paintScale(canvas);
        paintPointer(canvas);
        canvas.restore();
        postInvalidateDelayed(1000);
    }

    private void paintPointer(Canvas canvas) {
        canvas.save();
        canvas.rotate(mAngleHour);
        RectF rectHour = new RectF(-(mHourPointWidth / 2), -(mRadios * 2 / 5), mHourPointWidth / 2, mPointEndLength);
        mPaint.setColor(mHourPointColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRoundRect(rectHour, 10, 10, mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(mAngleMinute);
        RectF rectMinute = new RectF(-(mMinutePointWidth / 2), -(mRadios * 3 / 5), mMinutePointWidth / 2, mPointEndLength);
        mPaint.setColor(mMinutePointColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(PublicUtils.dp2px(mContext, 3));
        canvas.drawRoundRect(rectMinute, 3, 3, mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(mAngleSecond);
        RectF rectSecond = new RectF(-(mSecondPointWidth / 2), -(mRadios * 4 / 5), mSecondPointWidth / 2, mPointEndLength);
        mPaint.setColor(mSecondPointColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRoundRect(rectSecond, 3, 3, mPaint);
        canvas.restore();

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(mSecondPointColor);
        canvas.drawCircle(0, 0, mSecondPointWidth * 4, mPaint);
    }

    private void paintScale(Canvas canvas) {
        int lineWidth;
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                mPaint.setStrokeWidth(PublicUtils.dp2px(mContext, 1));
                mPaint.setColor(mColorLong);
                lineWidth = 40;
                mPaint.setTextSize(mTextSize);
                String text = String.valueOf((i / 5) == 0 ? 12 : (i / 5));
                // Paint.measureText()和Paint.getTextBounds均可获得字体宽度，getTextBounds稍微小一些
                mPaint.setColor(Color.BLACK);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.save();
                canvas.translate(0, -mRadios + PublicUtils.dp2px(mContext, 10) + lineWidth + PublicUtils.dp2px(mContext, 3) + (mPaint.descent() - mPaint.ascent()) / 2);
                canvas.rotate(-(6 * i));
                canvas.drawText(text, -(mPaint.measureText(text) / 2), -(mPaint.ascent() / 2), mPaint);
                canvas.restore();
                mPaint.setStrokeWidth(PublicUtils.dp2px(mContext, 2));
            } else {
                mPaint.setStrokeWidth(PublicUtils.dp2px(mContext, 1));
                mPaint.setColor(mColorShort);
                lineWidth = 30;
            }
            canvas.drawLine(0, -mRadios + PublicUtils.dp2px(mContext, 10), 0, -mRadios + PublicUtils.dp2px(mContext, 10) + lineWidth, mPaint);
            canvas.rotate(6);
        }
    }

    private void paintCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 下面屏蔽的两行代码，是绘制圆形的第一种方法。
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        canvas.drawCircle(0, 0, mRadios, mPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadios, mPaint);
    }
}
