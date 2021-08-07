package com.liecen.lifeutils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import com.vastweb.mainappmvp.R;

/**
 * Toast工具类
 *
 * @author stephen.shen
 * @email shenzhaoxiang@gmail.com
 * @date 2018-07-15 22:28
 */
public class ToastUtil {

	/**
	 * 显示带ICON的Toast
	 *
	 * @param context
	 * @param message
	 * @param iconId
	 */
	public static void showToast(Context context, String message, int iconId) {
		//LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout_shopdetail_top1/下的xml布局文件，并且实例化
		LayoutInflater inflater = LayoutInflater.from(context);//调用LayoutInflater.from(context);
		android.view.View view = inflater.inflate(R.layout.common_toast, null); //加載layout下的布局
		if (iconId != 0) {
			ImageView ivIcon = view.findViewById(R.id.common_toast_icon);
			ivIcon.setImageResource(iconId);//显示的图片
			ivIcon.setVisibility(android.view.View.VISIBLE);
		}
		android.widget.TextView tvMsg = view.findViewById(R.id.common_toast_msg);
		tvMsg.setText(message); //toast内容
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
		toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
		toast.setView(view); //添加视图文件
		toast.show();
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				if (toast!=null){
					toast.cancel();

				}
			}
		}, 1000);


	}

	/**
	 * 显示不带ICON的Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showToast(Context context, String message) {
		showToast(context, message, 0);
	}

 }
