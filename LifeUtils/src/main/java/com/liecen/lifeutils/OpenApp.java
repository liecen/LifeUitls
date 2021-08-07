package com.liecen.lifeutils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import static com.vastweb.mainappmvp.app.Constant.SECURITY_PATROL_PACKAGE_NAME;

/**
 * @author ： Life
 * onCreate DateTime 2021/3/7  : 15:43
 * in order to :
 **/

public class OpenApp {

    public  void openApp(Context context) {
        if (hasApplication(context,SECURITY_PATROL_PACKAGE_NAME)) {
            Intent intent = new Intent();
            //参数1：要跳转到的应用的包名    参数二：该包中的某个Activity
            ComponentName cn = new ComponentName(SECURITY_PATROL_PACKAGE_NAME, SECURITY_PATROL_PACKAGE_NAME+".mvp.main.MainActivity");
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "不存在该应用", Toast.LENGTH_LONG).show();
        }
    }

    private boolean hasApplication(Context context,String packageName) {
        PackageManager packageManager = context.getPackageManager();

        //获取系统中安装的应用包的信息
        java.util.List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < listPackageInfo.size(); i++) {
            if (listPackageInfo.get(i).packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;

    }
}
