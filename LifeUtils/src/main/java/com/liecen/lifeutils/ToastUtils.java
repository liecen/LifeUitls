package com.liecen.lifeutils;

import android.view.Gravity;

import com.vastweb.mainappmvp.BuildConfig;

public class ToastUtils {
    public static void showShortTop20(final CharSequence charSequence) {
        com.blankj.utilcode.util.ToastUtils.setGravity(Gravity.TOP, 0, 400);
        com.blankj.utilcode.util.ToastUtils.setMsgTextSize(20);
        com.blankj.utilcode.util.ToastUtils.showShort(charSequence);
    }

    public static void showShort(final CharSequence charSequence) {
        com.blankj.utilcode.util.ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        com.blankj.utilcode.util.ToastUtils.showShort(charSequence);
    }

    public static void showLong(final CharSequence charSequence) {
        com.blankj.utilcode.util.ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        com.blankj.utilcode.util.ToastUtils.showLong(charSequence);
    }
    
    public static void debugShowToast(final CharSequence charSequence) {
        if (BuildConfig.DEBUG) {
            com.blankj.utilcode.util.ToastUtils.setGravity(Gravity.CENTER, 0, 0);
            com.blankj.utilcode.util.ToastUtils.showLong("调试显示："+charSequence);
        }
    }
    
    

}
