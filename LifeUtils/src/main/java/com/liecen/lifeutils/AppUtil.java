package com.liecen.lifeutils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * desc:
 * Author: haungrui
 * Email:  506745564@qq.com
 * Date:  2019/8/15
 */
public class AppUtil {
    static Application application;
    public static int WIRTE_TYPE_ADDRESS_LNG_LAT = 0;
    public static int WIRTE_TYPE_NEW_VERSION_LOG = 1;
    public static int WIRTE_TYPE_BOOLEAN_READ_EXPAIN_ACCOUNT = 2;
    public static int TEXT_FONT_SCALE_ENLARGE = 0;
    public static int TEXT_FONT_SCALE_NARROW = 1;

    // 保留两位小数
    public static String getFormat00(String price) {
        double priceFormat = 0;
        if (TextUtils.isEmpty(price) || "null".equals(price)) {
            priceFormat = 0;
        } else {
            priceFormat = Double.parseDouble(price);
        }
        DecimalFormat df1 = new DecimalFormat("#0.00");
        String mPrice = df1.format(priceFormat);
        return mPrice;
    } // 保留两位小数

    public static String getFormat00(double price) {
        double priceFormat = 0;
        if (null != null || "null".equals(price)) {
            priceFormat = 0.0d;
        } else {
            priceFormat = price;
        }
        DecimalFormat df1 = new DecimalFormat("#0.00");
        String mPrice = df1.format(priceFormat);
        return mPrice;
    }

    public static String getFormat00(float price) {
        float priceFormat = 0;
        if (null != null || "null".equals(price)) {
            priceFormat = 0.0f;
        } else {
            priceFormat = price;
        }
        DecimalFormat df1 = new DecimalFormat("#0.00");
        String mPrice = df1.format(priceFormat);
        return mPrice;
    }

    public static float getFloatFormat00(float price) {
        float priceFormat = 0;
        if (null != null || "null".equals(price)) {
            priceFormat = 0.0f;
        } else {
            priceFormat = price;
        }
        DecimalFormat df1 = new DecimalFormat("#0.00");
        float mPrice = Float.valueOf(df1.format(priceFormat));
        return mPrice;
    }

    // 保留两位小数，并且四舍五入
    public static double formatDouble2(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        double bg = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return bg;
    }

    public static long getDaysOfTwo(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // 跨年的情况会出现问题哦
        // 如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
        try {
            Date fDate = sdf.parse(date);
            Date oDate = sdf.parse(sdf.format(new Date()));

            long betweenDate = (fDate.getTime() - oDate.getTime()) / (60 * 60 * 24 * 1000);

            return betweenDate;
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * @param string 要写文件的文件内容
     */
    public static void writeLocalData(int type, String string) {
        File file = null;
        if (type == WIRTE_TYPE_ADDRESS_LNG_LAT) {
            file = new File(
                    Environment.getExternalStorageDirectory() + "/data/data/com.custom.xingfucun/xingfucun.txt");
        } else if (type == WIRTE_TYPE_NEW_VERSION_LOG) {
            file = new File(
                    Environment.getExternalStorageDirectory() + "/data/data/com.custom.xingfucun/updatelog.txt");
        } else if (type == WIRTE_TYPE_BOOLEAN_READ_EXPAIN_ACCOUNT) {
            file = new File(
                    Environment.getExternalStorageDirectory() + "/data/data/com.custom.xingfucun/read_expain.txt");
        }
        // 判断文件是否存在
        if (!file.exists()) {
            File path = new File(file.getParent());
            if (!path.exists() && !path.mkdirs()) { // 判断文件夹是否存在，不存在则创建文件夹
                return;
            }
            try {
                if (!file.createNewFile()) { // 创建文件
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 实例化对象：文件输出流
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            // 写入文件
            fileOutputStream.write(string.getBytes());
            // 清空输出流缓存
            fileOutputStream.flush();
            // 关闭输出流
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLocalData(int type) {
        String reads = "";
        try {
            FileInputStream fileInputStream = null;
            if (type == WIRTE_TYPE_ADDRESS_LNG_LAT) {
                // /storage/emulated/0/data/data/com.custom.xingfucun/
                fileInputStream = new FileInputStream(Environment.getExternalStorageDirectory()
                        + "/data/data/com.custom" + ".xingfucun" + "/xingfucun.txt");
            } else if (type == WIRTE_TYPE_NEW_VERSION_LOG) {
                fileInputStream = new FileInputStream(Environment.getExternalStorageDirectory()
                        + "/data/data/com.custom" + ".xingfucun" + "/updatelog.txt");
            } else if (type == WIRTE_TYPE_BOOLEAN_READ_EXPAIN_ACCOUNT) {
                fileInputStream = new FileInputStream(Environment.getExternalStorageDirectory()
                        + "/data/data/com.custom" + ".xingfucun" + "/read_expain.txt");
                ;
            }
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (fileInputStream.read(bytes) != -1) {
                baos.write(bytes, 0, 19); // 限制经纬度总共19个字符，防止空字符乱码
            }
            reads = baos.toString();
            baos.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reads;
    }

    public static SharedPreferences mSharedPreferencesApp;

    public static void saveName(String name, String pwd) {
        String loginNameNew = null;
        String loginPswNew = null;
        try {
            loginNameNew = DES.getDES(name);
            loginPswNew = DES.getDES(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = mSharedPreferencesApp.edit();
        editor.putString("name", loginNameNew);
        editor.putString("pwd", loginPswNew);
        editor.apply();
        editor.commit();
    }

    public static String[] getName() {
        if (null != mSharedPreferencesApp) {
            return new String[]{mSharedPreferencesApp.getString("name", null),
                    mSharedPreferencesApp.getString("pwd", null)};
        }
        return null;
    }

    public static void setTextFontScaleAnimation(android.widget.TextView view, int scaleType) {
        if (scaleType == TEXT_FONT_SCALE_ENLARGE) { // 放大
            AnimatorSet animationSetBig = new AnimatorSet();
            // ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationY", 0, -20, -20);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleX", 1.35f);
            ObjectAnimator animator1_2 = ObjectAnimator.ofFloat(view, "scaleY", 1.35f);
            // ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "translationY", -20, 0, 0);
            animator1.setDuration(500);
            animationSetBig.playTogether(animator1, animator1_2);
            animationSetBig.setDuration(500);
            animationSetBig.start();

        } else if (scaleType == TEXT_FONT_SCALE_NARROW) {// 缩小
            AnimatorSet animationSetSmall = new AnimatorSet();
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f);
            ObjectAnimator animator2_2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f);
            animator2.setDuration(500);
            animationSetSmall.playTogether(animator2, animator2_2);
            animationSetSmall.start();

        }
    }

    public static void reLogin(Context context,Class startFirstAct) {
        Intent intentLogin = new Intent(context, startFirstAct);
        intentLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentLogin);
    }
    /*
     * public static void exitToLogin() { List<Activity> activityList = MyApplication.getContext().getActivityList();
     * for (int i = activityList.size() - 1; i >= 0; --i) {// remove from top Activity activity = activityList.get(i);
     * // sActivityList remove the index activity at onActivityDestroyed activity.finish(); } System.exit(0); }
     */

    private void getSomeActivity(Context context,Activity elseActivity) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getApplicationContext().getPackageName(), PackageManager.GET_ACTIVITIES);
            for (ActivityInfo activityInfo : packageInfo.activities) {
                Class<?> aClass = Class.forName(activityInfo.name);
            }

        } catch (PackageManager.NameNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty(String... strings) {
        for (String s : strings) {
            if (TextUtils.isEmpty(s))
                return false;
        }
        return true;
    }

  /*  public static void imagViewAutoHeight(Context context, ImageView imageView) {
        int screenWidth = getScreenWidth(context);
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(lp);
        imageView.setMaxWidth(screenWidth);
        imageView.setMaxHeight(screenWidth * 5); // 这里其实可以根据需求而定，我这里测试为最大宽度的5倍
    }

    public static void imageViewAutoHeightType2(Context context, ImageView imageView) {
        int screenWidth = getScreenWidth(context);
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        // lp.width = (int) (screenWidth*0.47);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(lp);
        imageView.setMaxWidth(screenWidth);
        imageView.setMaxHeight(screenWidth * 5); // 这里其实可以根据需求而定，我这里测试为最大宽度的5倍
    }*/

    public static boolean isNoNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true表示是外网版本
     */
   /* public static boolean isPoliceExternalAppVersion() {
        LogUtil.info("包名：" + AppUtils.getAppPackageName() + AppUtils.getAppPackageName().contains("debug"));
        boolean isPoliceExternalAppVersion = AppUtils.getAppPackageName().contains("external")
                || AppUtils.getAppPackageName().contains("test") || AppUtils.getAppPackageName().contains("debug");
        return isPoliceExternalAppVersion;

    }*/

    /**
     * 清除多余无用的照片，避免使用过程，导致app应用数据持续增加，浪费手机内存
     * 做法：遍历savePicturePathNew文件下所有文件，查询是否存在数据库中，没有则删除；
     */
    @Deprecated
    public static void ClearUselessPhotos(final String savePicturePath) {
        LogUtil.d("开始清除多余照片--start");
        new Thread() {
            @Override
            public void run() {
                File file = new File(savePicturePath);
                if (file.exists()) {
                    File[] photos = file.listFiles();
                    for (File item : photos) {
                        LogUtil.d("遍历照片路径：" + item.getPath() + "，文件名：" + item.getName());
                       /* if (HomeVisitLogUntil.queryPhotoStatusOfDatabase(item.getName())) {
                            LogUtil.d("多余照片路径：" + item.getPath());
                            item.delete();
                        } else {
                            LogUtil.d("有用照片路径：" + item.getPath());
                        }*/
                    }
                } else {
                    file.mkdirs();
                }
            }
        }.start();
    }

    private static String mDefaultDynamicPictureSeparator = ",";
    public static String appendToString(String operationSymbol, java.util.List<String> list) {
        if (TextUtils.isEmpty(operationSymbol)) {

            operationSymbol = mDefaultDynamicPictureSeparator;
        }
        StringBuffer sb = new StringBuffer();
        if (list == null) {

        } else {
            int i, size = list.size();
            for (i = 0; i < size; i++) {
                if (i < size - 1) {
                    sb.append(list.get(i)).append(operationSymbol);
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    // 拼接字符，最后一个仍然加拼接符号
    public static String appendToString(String operationSymbol, java.util.List<String> list, String beforeTheCharacter) {
        if (TextUtils.isEmpty(operationSymbol)) {
            operationSymbol = mDefaultDynamicPictureSeparator;
        }
        StringBuffer sb = new StringBuffer();
        if (list == null) {

        } else {
            int i, size = list.size();
            for (i = 0; i < size; i++) {
                    sb.append(beforeTheCharacter).append(list.get(i)).append(operationSymbol);
            }
        }
        return sb.toString();
    }

    public static boolean hasApplication(Context context,String packageName) {
        PackageManager packageManager = context.getPackageManager();

        // 获取系统中安装的应用包的信息
        java.util.List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < listPackageInfo.size(); i++) {
            if (listPackageInfo.get(i).packageName.equalsIgnoreCase(packageName)) {
     /*           if (listPackageInfo.get(i).versionCode == 101) {
                    return true;
                } else {
                    ToastUtils.showShort("版本过低，请升级！");
                }*/
                return true;
            }
        }
        return false;

    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
