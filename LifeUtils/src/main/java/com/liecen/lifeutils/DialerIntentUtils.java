package com.liecen.lifeutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/*
 *   create by Life_lai
 *   in order to 跳转拨打电话界面
 *   Date on 2020/9/28/11:32
 */
public class DialerIntentUtils {

    public DialerIntentUtils(Context context, String phoneNumber) {
        dialerIntent(context, phoneNumber);
    }
    /**
     * @param context 上下文
    @param phoneNumber input phone number
     */
    private void dialerIntent(Context context, String phoneNumber) {
        if (context != null || phoneNumber == null || TextUtils.isEmpty(phoneNumber)){
            return;
        }
        Intent intentDialer = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intentDialer.setData(data);
        if (intentDialer.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intentDialer);
        }
    }
}
