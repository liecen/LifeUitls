package com.liecen.lifeutils;

import android.content.Context;

import com.vastweb.mainappmvp.R;
import com.vastweb.mainappmvp.widget.ToastLoadingDialog;

import java.util.HashMap;


public class PayManager {

    private static volatile PayManager instance;
    private ToastLoadingDialog mToastLoadingDialog;
    private Context mApplicationContext;

    private PayManager() {
    }

    public static PayManager getInstance() {
        if (instance == null) {
            synchronized (PayManager.class) {
                if (instance == null) {
                    instance = new PayManager();
                }
            }
        }
        return instance;
    }


    public void init(Context context) {
        mApplicationContext = context;
//        api = WXAPIFactory.createWXAPI(context, Constant.WX_APPID);

    }


    public void showLoading(Context context) {
        if (mToastLoadingDialog == null) {
            mToastLoadingDialog = new ToastLoadingDialog(context);
        }
        mToastLoadingDialog.setMsg(R.string.is_loading);
        mToastLoadingDialog.show();
    }


    public void hideLoading(Context context) {
        if (mToastLoadingDialog != null) {
            mToastLoadingDialog.dismiss();
            mToastLoadingDialog = null;
        }
    }


    //微信生成支付请求参数：线下扫码支付-收银
    public void API_GetWxpayTradeInfo_CashierQRPayment(Context mContext, String mOrderNo, int isOffset) {
        showLoading(mContext);
        HashMap map = new HashMap();
        map.put("OrderId", mOrderNo);
        map.put("IsOffset", isOffset);
       /* ApiManagerPay.getInstence().getApiService().API_GetWxpayTradeInfo_CashierQRPayment_V2(map)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new BaseObserver<GetWxpayTradeInfo>() {
                    @Override
                    protected void onSuccees(GetWxpayTradeInfo result) {
                        hideLoading(mContext);
                         if (ApiCode.SUCCESS.equals(result.getMsg())) {
                            LogUtil.d("获取微信支付参数成功:" + result.getRequestStr());
                            try {
                                JSONObject mJson = new JSONObject(result.getRequestStr());
                                PayReq req = new PayReq();
                                req.appId = mJson.getString("appid");
                                req.partnerId = mJson.getString("partnerid");
                                req.prepayId = mJson.getString("prepayid");
                                req.nonceStr = mJson.getString("noncestr");
                                req.timeStamp = mJson.getString("timestamp");
                                req.packageValue = "Sign=WXPay";
                                req.sign = mJson.getString("sign");
                                api.sendReq(req);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                ToastUtil.showToast(mContext, "出现异常");
                            }
                        } else {
                            ToastUtil.showToast(mContext, result.getMsg());
                        }

                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        hideLoading(mContext);
                        ToastUtil.showToast(mContext, "出现异常");
                    }
                });
*/
    }


    //微信生成支付请求参数：商品订单-在线支付
    public void API_GetWxpayTradeInfo_BusinessProductRecord(Context mContext, String mOrderNo, int isOffset) {
        showLoading(mContext);
        HashMap map = new HashMap();
        map.put("OrderId", mOrderNo);
        map.put("IsOffset", isOffset);
      /*  ApiManagerPay.getInstence().getApiService().API_GetWxpayTradeInfo_BusinessProductRecord_V2(map)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new BaseObserver<GetWxpayTradeInfo>() {
                    @Override
                    protected void onSuccees(GetWxpayTradeInfo result) {
                        hideLoading(mContext);
                        if (ApiCode.SUCCESS.equals(result.getMsg())) {
                            LogUtil.d("获取微信支付参数成功:" + result.getRequestStr());
                            try {
                                JSONObject mJson = new JSONObject(result.getRequestStr());
                                PayReq req = new PayReq();
                                req.appId = mJson.getString("appid");
                                req.partnerId = mJson.getString("partnerid");
                                req.prepayId = mJson.getString("prepayid");
                                req.nonceStr = mJson.getString("noncestr");
                                req.timeStamp = mJson.getString("timestamp");
                                req.packageValue = "Sign=WXPay";
                                req.sign = mJson.getString("sign");
                                api.sendReq(req);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                ToastUtil.showToast(mContext, "数据解析异常");
                            }
                        } else {
                            ToastUtil.showToast(mContext, result.getMsg());
                        }

                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        hideLoading(mContext);
                        ToastUtil.showToast(mContext, "出现异常");
                    }
                });
*/
    }


    //微信生成支付请求参数：购买商家分红权（项目认购）
    public void API_GetWxpayTradeInfo_BusinessBonusRecord(Context mContext, String mOrderNo) {
        showLoading(mContext);
        HashMap map = new HashMap();
        map.put("OrderId", mOrderNo);
        /*ApiManagerPay.getInstence().getApiService().API_GetWxpayTradeInfo_BusinessBonusRecord(map)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new BaseObserver<GetWxpayTradeInfo>() {
                    @Override
                    protected void onSuccees(GetWxpayTradeInfo result) {
                        hideLoading(mContext);
                        if (ApiCode.SUCCESS.equals(result.getMsg())) {
                            LogUtil.d("获取微信支付参数成功:" + result.getRequestStr());
                            try {
                                JSONObject mJson = new JSONObject(result.getRequestStr());
                                PayReq req = new PayReq();
                                req.appId = mJson.getString("appid");
                                req.partnerId = mJson.getString("partnerid");
                                req.prepayId = mJson.getString("prepayid");
                                req.nonceStr = mJson.getString("noncestr");
                                req.timeStamp = mJson.getString("timestamp");
                                req.packageValue = "Sign=WXPay";
                                req.sign = mJson.getString("sign");
                                api.sendReq(req);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                ToastUtil.showToast(mContext, "出现异常");
                            }
                        } else {
                            ToastUtil.showToast(mContext, result.getMsg());
                        }

                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        hideLoading(mContext);
                        ToastUtil.showToast(mContext, "出现异常");
                    }
                });
*/
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    /*private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    *//**
     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
     *//*
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtil.d("支付状态码:" + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        EventBus.getDefault().post("0","alipayresult");
                        ToastUtil.showToast(mApplicationContext, "支付成功!");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtil.showToast(mApplicationContext, "支付失败!");
                    }
                    break;
                }

                default:
                    break;
            }
        }

    }; */
/*

    //API_GetAlipayTradeInfo_CashierQRPayment
    public void API_GetAlipayTradeInfo_CashierQRPayment(Activity mContext, String mOrderNo,int isOffset){
        HashMap map = new HashMap();
        map.put("OrderId", mOrderNo);
        map.put("IsOffset", isOffset);
        ApiManagerPay.getInstence().getApiService().API_GetAlipayTradeInfo_CashierQRPayment_V2(map)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new BaseObserver<GetAlipayTradeInfo>() {
                    @Override
                    protected void onSuccees(GetAlipayTradeInfo result) {
                        hideLoading(mContext);
                        if (ApiCode.SUCCESS.equals(result.getMsg())) {
                            LogUtil.d("获取支付宝支付参数成功:" + result.getRequestStr());
                            Runnable payRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(mContext);
                                    Map<String, String> mALi = alipay.payV2(result.getRequestStr(), true);
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = mALi;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else {
                            ToastUtil.showToast(mContext, result.getMsg());
                        }

                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        hideLoading(mContext);
                        ToastUtil.showToast(mContext, "出现异常");
                    }
                });
    }


    public void API_GetAlipayTradeInfo_BusinessProductRecord(Activity mContext, String mOrderNo,int isOffset){
        HashMap map = new HashMap();
        map.put("OrderId", mOrderNo);
        map.put("IsOffset", isOffset);
        ApiManagerPay.getInstence().getApiService().API_GetAlipayTradeInfo_BusinessProductRecord_V2(map)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new BaseObserver<GetAlipayTradeInfo>() {
                    @Override
                    protected void onSuccees(GetAlipayTradeInfo result) {
                        hideLoading(mContext);
                        if (ApiCode.SUCCESS.equals(result.getMsg())) {
                            LogUtil.d("获取支付宝支付参数成功:" + result.getRequestStr());
                            Runnable payRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(mContext);
                                    Map<String, String> mALi = alipay.payV2(result.getRequestStr(), true);
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = mALi;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else {
                            ToastUtil.showToast(mContext, result.getMsg());
                        }

                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        hideLoading(mContext);
                        ToastUtil.showToast(mContext, "出现异常");
                    }
                });
    }


    //购买商家分红权（项目认购）
    public void API_GetAlipayTradeInfo_BusinessBonusRecord(Activity mContext, String mOrderNo){
        HashMap map = new HashMap();
        map.put("OrderId", mOrderNo);
        ApiManagerPay.getInstence().getApiService().API_GetAlipayTradeInfo_BusinessBonusRecord(map)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new BaseObserver<GetAlipayTradeInfo>() {
                    @Override
                    protected void onSuccees(GetAlipayTradeInfo result) {
                        hideLoading(mContext);
                        if (ApiCode.SUCCESS.equals(result.getMsg())) {
                            LogUtil.d("获取支付宝支付参数成功:" + result.getRequestStr());
                            Runnable payRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(mContext);
                                    Map<String, String> mALi = alipay.payV2(result.getRequestStr(), true);
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = mALi;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else {
                            ToastUtil.showToast(mContext, result.getMsg());
                        }

                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        hideLoading(mContext);
                        ToastUtil.showToast(mContext, "出现异常");
                    }
                });
    }
*/


}
