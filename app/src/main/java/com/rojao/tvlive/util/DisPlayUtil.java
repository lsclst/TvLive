package com.rojao.tvlive.util;

import android.content.Context;

/**
 * Created by lsc on 2017/4/14 0014.
 *
 * @author lsc
 * @desc ${TODO}
 */
public class DisPlayUtil {

    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    private static int pxToDp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }
}
