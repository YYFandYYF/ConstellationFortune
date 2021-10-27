package com.yuefei.constellationfortune;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yuefei.constellationfortune.utils.DensityUtil;

/**
 * 竖直矩形-展示指数
 */
public class VerticalBar extends View {

    private static final String TAG = "VerticalBar";

    private int paint_color = Color.RED;
    private int height = 100;

    public VerticalBar(Context context) {
        super(context);
    }

    public VerticalBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VerticalBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //定义画笔
        Paint paint = new Paint();
        //设置颜色
        paint.setColor(paint_color);
        //设置画笔类型
        paint.setStyle(Paint.Style.FILL);
        //使用画笔在画布上画矩形
        int bottom = DensityUtil.dp2px(getContext(), 80);
        canvas.drawRect(0, bottom - bottom * height / 100, DensityUtil.dp2px(getContext(), 10), bottom, paint);

//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(2);
//        canvas.drawRect(0, getBottom(), getRight(), 0, paint);

    }

    public void setPaint_color(int paint_color) {
        this.paint_color = paint_color;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
