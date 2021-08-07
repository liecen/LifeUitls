package com.liecen.lifeutils;

import android.app.Activity;
import android.location.Address;
import android.location.LocationManager;
import android.webkit.JavascriptInterface;


import java.lang.ref.WeakReference;

public class AndroidInterfacesToWeb {
    private WeakReference<Activity> mActivity;
    private String mBaseAppId;
    private String mAppId;

    public AndroidInterfacesToWeb(Activity context) {
        mActivity = new WeakReference<>(context);
    }

    @JavascriptInterface
    public void backFromJS() {
        ((Activity) mActivity.get()).finish();
    }

    @JavascriptInterface
    public String getToken() {

      /*  if (LoginBeanUntil.getUser() == null || LoginBeanUntil.getUser().getToken() == null
                || "".equalsIgnoreCase(LoginBeanUntil.getUser().getToken())) {
            AppUtil.reLogin();
            return "token 过期";
        } else {
            String token = LoginBeanUntil.getUser().getToken();
            return *//* "bearer "+ *//*token;
        }*/
        return "获取token";

    }

    @JavascriptInterface
    public String getVisitAppId() {
        return mAppId;
    }

    @JavascriptInterface
    public void getCountDetail(String url) {

    }

    @JavascriptInterface
    public String getStatusBarHeight() {
        return "0";
    }

    @JavascriptInterface
    public String returnPhotoBlob() {
        return "d";
        // return Base64Util.imageToBase64("/storage/emulated/0/photo.jpeg");
    }

    /*
     * @SuppressWarnings("unchecked") public String getBlob(String base64, String type) throws IOException, SQLException
     * { String s = base64.substring(base64.indexOf(',') + 1); byte[] bstr = Base64.decode( s, Base64.NO_WRAP); int n =
     * bstr.length; byte[] u8arr = new byte[][n]; while (n>0) { u8arr[n] = bstr[n] ;
     * 
     * } return new Blob([u8arr], { type, })
     * 
     * }
     */

    @JavascriptInterface
    public String getHfId() {
        return mAppId;

    }

    /*
     * { latitude: 0, longitude: 0, adress: "详细地址" }
     */

    @JavascriptInterface
    public String adressToWeb() {
        return "返回地址";
    }

    @JavascriptInterface
    public String getBaseAppId() {
        return mBaseAppId;
    }

    public void setBaseAppId(String baseAppId) {
        this.mBaseAppId = baseAppId;
    }

    public void setAppId(String appId) {
        this.mAppId = appId;
    }
}
