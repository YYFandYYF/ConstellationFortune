package com.yuefei.constellationfortune.utils;

import android.content.Context;

/**
 * dp与像素px换算
 */
public class DensityUtil {
    /**
     * PX = density * DP
     * px像素     dpi 像素/英寸     dp 设备无关像素
     * density = dpi/160
     * 1 dp = density px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
