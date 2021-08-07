package com.liecen.lifeutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * @Author: life
 * @CreateDate: 2021/5/27 10:57
 * @Description: 查看大图工具
 */
public class BigImageUtils {
}
/*{

    public static void showBigImageType2(String imagePath, Context context) {
        ImagePreview.getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                .setContext((Activity) context)

                // 设置从第几张开始看（索引从0开始）
                .setIndex(0)

                //=================================================================================================
                // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                // 1：第一步生成的imageInfo List
//               .setImageInfoList(imageInfoList)

                // 2：直接传url List
//               .setImageList(List<String> imageList)

                // 3：只有一张图片的情况，可以直接传入这张图片的url
                .setImage(imagePath)
                .setEnableDragClose(true)
                //=================================================================================================

                // 开启预览
                .start();

        // 默认配置为：
        //      显示顶部进度指示器、
        //      显示右侧下载按钮、
        //      隐藏左侧关闭按钮、
        //      开启点击图片关闭、
        //      关闭下拉图片关闭、
        //      加载方式为手动模式
        //      加载原图的百分比在底部
    }

    *//**
 * 查看大图
 *
 * @param imagePathList 所有图片集合
 * @param index         默认查看图片的下标
 * @param context
 *//*
    public static void showBigImageType2(java.util.List<String> imagePathList, int index, Context context) {
        ImagePreview.getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                .setContext((Activity) context)
                // 设置从第几张开始看（索引从0开始）
                .setIndex(index)
                //=================================================================================================
                // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                // 1：第一步生成的imageInfo List
//               .setImageInfoList(imageInfoList)
                // 2：直接传url List
                .setImageList(imagePathList)
                // 3：只有一张图片的情况，可以直接传入这张图片的url
//                .setImage(imagePath)
                .setEnableDragClose(true)
                //=================================================================================================
                // 开启预览
                .start();
        // 默认配置为：
        //      显示顶部进度指示器、
        //      显示右侧下载按钮、
        //      隐藏左侧关闭按钮、
        //      开启点击图片关闭、
        //      关闭下拉图片关闭、
        //      加载方式为手动模式
        //      加载原图的百分比在底部
    }
}*/
