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
 * 白 圆 背景
 */
public class WhiteCircle extends View {

    public WhiteCircle(Context context) {
        super(context);
    }

    public WhiteCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WhiteCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WhiteCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //定义画笔
        Paint paint = new Paint();
        //设置颜色
        paint.setColor(Color.WHITE);
        //设置画笔类型
        paint.setStyle(Paint.Style.FILL);
        //使用画笔在画布上画圆形
        float x = DensityUtil.dp2px(getContext(), 30);
        canvas.drawCircle(x, x, x, paint);
    }
}
