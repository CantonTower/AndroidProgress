package cn.longrise.www.androidprogress.activity.customDrawingActivity.codeCustomDrawing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.longrise.www.androidprogress.R;

/**
 * Created by Administrator on 2018-4-24.
 */

/**
 *  自定义TextView的高度见https://blog.csdn.net/a2241076850/article/details/70226988
 */

/**
 *  paint绘制文本时，坐标是从基准线最左边为圆点，向下为正，向上为负，向左为负，向右为正。
 *  同时，canvas.drawText()方法，第二三个参数的意思是，相对于canvas的左上角，paint的起点坐标，也是基准线的最左边。
 *  paint抗锯齿和防抖动的使用：https://blog.csdn.net/lovexieyuan520/article/details/50732023
 */

/**
 *  MeasureSpec.EXACTLY:表示精确值。当layout_width="50dp"或match_parent都是精确值
 *
 *  MeasureSpec.AT_MOST:表示父容器给予了最大值。当wrap_content时，出现这种情况。
 *  此时，MeasureSpec.getSize(widthMeasureSpec)得到的数值是父容器给予的最大值。比如当整个屏幕只有这一个TextView时，使用wrap_context
 *  那么MeasureSpec.getSize(widthMeasureSpec)就会得到680(宽)1024(高)，则刚好是屏幕分辨率。
 *
 *  MeasureSpec.UNSPECIFIED:表示未指定尺寸。这种情况不多。
 */

public class MyTextView extends View{
    private Paint mPaint;
    private String mText;
    private int mTextColor;
    private int mTextSize;
    private int mHeight;

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mText = array.getString(R.styleable.MyTextView_text);
        mTextColor = array.getColor(R.styleable.MyTextView_textColor, 0x000);
        mTextSize = array.getDimensionPixelSize(R.styleable.MyTextView_textSize, 50);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasure(widthMeasureSpec),heightMeasure(heightMeasureSpec));
    }

    private int heightMeasure(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int result = size;
        if (mode == MeasureSpec.AT_MOST) {
            result = (int) (mPaint.descent() - mPaint.ascent());
        }
        mHeight = result;
        return result;
    }

    private int widthMeasure(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = size;
        if (mode == MeasureSpec.AT_MOST) {
            result = (int) (mPaint.measureText(mText));
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        canvas.drawText(mText, 0, mHeight - mPaint.descent(), mPaint);
    }
}
