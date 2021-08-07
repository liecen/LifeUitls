package com.liecen.lifeutils;

import android.content.Context;
import android.view.ViewGroup;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.vastweb.mainappmvp.R;

import java.util.Calendar;


/**
 * @desc:
 * @author: zhang
 * @date: 2019/5/23
 */
public class PickerUtil {
    private volatile static PickerUtil mSingleton = null;

   /* private PickerUtil(){

    }
    public static PickerUtil getInstance() {
        if (mSingleton == null) {
            synchronized (PickerUtil.class) {
                if (mSingleton == null) {
                    mSingleton = new PickerUtil();
                }
            }
        }
        return mSingleton;

    }*/
     public static  void showTimePicker(Context mContext, ViewGroup viewGroup,OnTimeSelectListener  listener) {

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);
         //正确设置方式 原因：注意事项有说明
        startDate.set(1990,0,1);
        endDate.set(2099,11,31);

        TimePickerView pvTime = new TimePickerBuilder(mContext,listener)
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(13)
                .setSubCalSize(13)
                .setLineSpacingMultiplier(3)
                //    .setContentSize(18)//滚轮文字大小
                .setTitleSize(13)//标题文字大小
                 .setTitleText("选择日期和时间")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(android.graphics.Color.BLACK)//标题文字颜色
                .setSubmitColor(android.graphics.Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(mContext.getResources().getColor(R.color.color_3D7EFF ))//取消按钮文字颜色
                .setTitleBgColor(android.graphics.Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(android.graphics.Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .setDecorView(viewGroup)
                .build();
        pvTime.show();

     }


     // 这个app主要用该方法，开始时间选当月第一天，结束时间选当天
    public static void showTimePicker(Context mContext, ViewGroup viewGroup, OnTimeSelectListener listener, String title) {
        Calendar selectedDate = Calendar.getInstance();
        if (title.contains("结束")) {
        } else {
            selectedDate.set(Calendar.DAY_OF_MONTH, 1);
            selectedDate.set(Calendar.HOUR_OF_DAY, 0);
            selectedDate.set(Calendar.MINUTE, 0);
            selectedDate.set(Calendar.SECOND, 0);
        }
        Calendar startDate = Calendar.getInstance();
        // startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        // endDate.set(2020,1,1);
        // 正确设置方式 原因：注意事项有说明
        startDate.set(1990, 0, 1);
        endDate.set(2099, 11, 31);
        TimePickerView pvTime = new TimePickerBuilder(mContext, listener)
                .setType(new boolean[] { true, true, true, false, false, false })// 默认全部显示
                .setCancelText("取消")// 取消按钮文字
                .setSubmitText("确定")// 确认按钮文字
                .setContentTextSize(13).setSubCalSize(13).setLineSpacingMultiplier(3f)
                // .setContentSize(18)//滚轮文字大小
                .setTitleSize(13)// 标题文字大小
                .setTitleText(title)// 标题文字
                .setOutSideCancelable(true)// 点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)// 是否循环滚动
                .setTitleColor(android.graphics.Color.BLACK)// 标题文字颜色
                .setSubmitColor(android.graphics.Color.BLUE)// 确定按钮文字颜色
                // .setCancelColor(mContext.getResources().getColor(R.color.color_3D7EFF ))//取消按钮文字颜色
                .setTitleBgColor(android.graphics.Color.WHITE)// 标题背景颜色 Night mode
                .setBgColor(android.graphics.Color.WHITE)// 滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)// 起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")// 默认设置为年月日时分秒
                .isCenterLabel(false) // 是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)// 是否显示为对话框样式
                .setDecorView(viewGroup).build();
        pvTime.show();

    }


    //显示小时和分钟，可传入标题
     public static  void showTimePickerOnlyHourMinute(String timePickerTitle,Context mContext, ViewGroup viewGroup,OnTimeSelectListener  listener) {

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);
         //正确设置方式 原因：注意事项有说明
        startDate.set(1990,0,1);
        endDate.set(2099,11,31);

        TimePickerView pvTime = new TimePickerBuilder(mContext,listener)
                .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)
                .setSubCalSize(13)
                .setLineSpacingMultiplier(3)
                .setTitleSize(13)//标题文字大小
                 .setTitleText(timePickerTitle)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(android.graphics.Color.BLACK)//标题文字颜色
                .setSubmitColor(android.graphics.Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(mContext.getResources().getColor(R.color.color_3D7EFF ))//取消按钮文字颜色
                .setTitleBgColor(android.graphics.Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(android.graphics.Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .setDecorView(viewGroup)
                .build();
        pvTime.show();

     }



    public static  void showPickerArea(Context mContext, java.util.List<String> mList, OnOptionsSelectListener  listener) {

        OptionsPickerView serviceOptions = new OptionsPickerBuilder(mContext, listener)
                .setTitleText("所属区域")
                .setContentTextSize(12)//设置滚轮文字大小
                .setTitleSize(13) // 标题文字大小
                .setSubCalSize(12) // 取消确定按钮文字大小
                .setDividerColor(android.graphics.Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setLineSpacingMultiplier(3.0F) // 行间距
                .setBgColor(android.graphics.Color.WHITE)
                .setTitleBgColor(android.graphics.Color.WHITE)
//                .setTitleColor(mContext.getResources().getColor(R.color.color_353535))
//                .setCancelColor(mContext.getResources().getColor(R.color.color_999))
//                .setSubmitColor(mContext.getResources().getColor(R.color.color_333333))
//                .setTextColorCenter(mContext.getResources().getColor(R.color.color_333333))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setBackgroundId(mContext.getResources().getColor(R.color.common_translucent_bg)) //设置外部遮罩颜色
                .setOptionsSelectChangeListener((options1, options2, options3) -> {

                })
                .build();
        serviceOptions.setPicker(mList);//一选择器
        serviceOptions.show();

    }



    public static  void showPickerHead(Context mContext, java.util.List<String> mList, OnOptionsSelectListener  listener) {

        OptionsPickerView serviceOptions = new OptionsPickerBuilder(mContext, listener)
                .setTitleText("总部所在地")
                .setContentTextSize(12)//设置滚轮文字大小
                .setTitleSize(13) // 标题文字大小
                .setSubCalSize(12) // 取消确定按钮文字大小
                .setDividerColor(android.graphics.Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setLineSpacingMultiplier(3.0F) // 行间距
                .setBgColor(android.graphics.Color.WHITE)
                .setTitleBgColor(android.graphics.Color.WHITE)
//                .setTitleColor(mContext.getResources().getColor(R.color.color_353535))
                .setCancelColor(mContext.getResources().getColor(R.color.color_999))
//                .setSubmitColor(mContext.getResources().getColor(R.color.color_333333))
//                .setTextColorCenter(mContext.getResources().getColor(R.color.color_333333))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setBackgroundId(mContext.getResources().getColor(R.color.common_translucent_bg)) //设置外部遮罩颜色
                .setOptionsSelectChangeListener((options1, options2, options3) -> {

                })
                .build();
        serviceOptions.setPicker(mList);//一选择器
        serviceOptions.show();

    }
}
