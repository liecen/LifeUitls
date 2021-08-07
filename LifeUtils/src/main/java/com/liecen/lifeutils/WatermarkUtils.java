package com.liecen.lifeutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.liecen.lifeutils.AppUtil.dp2px;
import static com.liecen.lifeutils.LubanUtils.savePicturePathNew;

/**
 * @Author: life
 * @CreateDate: 2021/5/8 11:26
 * @Description: 给图片添加水印
 */
public class WatermarkUtils {

    public static String createWater(Context context, String path, String address, int layoutView, int tv_createtime, int tv_address) {
        File originSrc = new File(path);
        if (!originSrc.exists()) return "";
        Bitmap srcBit = BitmapFactory.decodeFile(path);
        Bitmap waterPic = createWaterMaskRightBottom(context,srcBit, convertViewToBitmap(context, address, layoutView, tv_createtime, tv_address), 10, 10);
        return saveBitmap(waterPic);
    }

    /**
     * 设置水印图片在左上角
     *
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingTop
     * @return
     */
    private static Bitmap createWaterMaskLeftTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                dp2px(context, paddingLeft), paddingTop);
    }

    /**
     * 设置水印图片在右下角
     *
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingBottom
     * @return
     */
    private static Bitmap createWaterMaskRightBottom(Context context,
                                                     Bitmap src, Bitmap watermark,
                                                     int paddingRight, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片到右上角
     *
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingTop
     * @return
     */
    private static Bitmap createWaterMaskRightTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingRight, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                dp2px(context, paddingTop));
    }

    /**
     * 设置水印图片到左下角
     *
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingBottom
     * @return
     */
    private static Bitmap createWaterMaskLeftBottom(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark, dp2px(context, paddingLeft),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制水印图片
     *
     * @param src         原图
     * @param watermark   水印
     * @param paddingLeft
     * @param paddingTop
     * @return
     */
    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save();
        // 存储
        canvas.restore();
        return newb;
    }

    /**
     * 保存方法
     */
    public static String saveBitmap(Bitmap waterPic) {
        String waterPicPath = savePicturePathNew + DateUtils.convertToStringNoSplit(System.currentTimeMillis());
        File f = new File(waterPicPath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            waterPic.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return waterPicPath;
    }

    /**
     * 将一个view转换为Bitmap,为水印
     *
     * @return
     */
    private static Bitmap convertViewToBitmap(Context context, String address, int layoutView, int tv_createtime, int tv_address) {
        View view = View.inflate(context, layoutView, null);
        TextView tvCreateText = view.findViewById(tv_createtime);
        tvCreateText.setText("app走访于：" + DateUtils.convertToString(System.currentTimeMillis()));
        TextView tvAddress = view.findViewById(tv_address);
        tvAddress.setText(address);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
}
