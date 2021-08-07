package com.liecen.lifeutils;
/*
 *  @author ： Life
 *  onCreate DateTime 2021/1/23  : 9:35
 *  in order to : EditView拦截器
 */

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EditViewFilterUtils {
    public static String chinaTextFilter(String str) throws PatternSyntaxException {
        //只允许汉字
        String regEx = "[^\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static String mobileNumberFilter(String str) throws PatternSyntaxException {
        //输入时，时时校验手机号码 11 位数，第一位必须为1，第二位不能为2 ;PHONE = "^1[3456789]\\d{9}$";
        String regEx;
        if (TextUtils.isEmpty(str) || str == null || str.length() == 0) {
            return "";
        } else if (str.length() == 1) {
            regEx = "[^1]";
        } else if (str.length() == 2) {
            regEx = "[1][^3456789]";
        } else {
            return str;
        }
        LogUtil.info("正则"+regEx);
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
