package com.yuefei.constellationfortune;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yuefei.constellationfortune.utils.DensityUtil;


/**
 * 波浪View  +   动画
 */
public class Wave extends View {

    private float shift = 1;
    private float speed = (float) (4 * Math.PI / 308);    //w(x+speed)=wx
    Path path = new Path();

    int height = DensityUtil.dp2px(getContext(), 100);//起始高度
    int A = DensityUtil.dp2px(getContext(), 20);//振幅

    public Wave(Context context) {
        super(context);
    }

    public Wave(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        postDelayed(new WaveAnimation(), 16);
    }

    public Wave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Wave(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int width = getWidth();//154.0

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#FFFFFF"));
        paint.setAlpha(100);
        drawWavePath(canvas, width / 3, paint);
        paint.setAlpha(150);
        drawWavePath(canvas, width / 5, paint);
        paint.setAlpha(200);
        drawWavePath(canvas, width / 7, paint);
    }

    private void drawWavePath(Canvas canvas, float width, Paint paint) {
        path.moveTo(0, height);
        for (int i = 1; i <= getWidth(); i++) {
            float y = (float) (height - Math.abs(A * Math.sin(Math.PI * i / width / 2 + shift)));
            path.lineTo(i, y);
        }
        path.lineTo(getWidth(), height);//将曲线闭合
        canvas.drawPath(path, paint);
    }


    class WaveAnimation implements Runnable {
        @Override
        public void run() {
            path.reset();
            shift += speed;
            invalidate();
            Wave.this.postDelayed(this, 16);
        }
    }
}
