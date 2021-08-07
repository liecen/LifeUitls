package com.liecen.lifeutils;

import android.text.TextUtils;

/**
 * @Author: life
 * @CreateDate: 2021/5/18 10:43
 * @Description: 数字解析工具
 */
public class ParseUtils {
    public static int parseInt(String str) {
        int result = -1;
        if (!TextUtils.isEmpty(str)) {
            try {
                result = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static long parseLong(String str) {
        long result = -1;
        if (!TextUtils.isEmpty(str)) {
            try {
                result = Long.parseLong(str);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
