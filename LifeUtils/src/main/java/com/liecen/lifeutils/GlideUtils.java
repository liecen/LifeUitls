package com.liecen.lifeutils;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.vastweb.mainappmvp.R;
import com.vastweb.mainappmvp.app.MyApplication;


public class GlideUtils {

    public static void loadImage(android.graphics.drawable.Drawable imgUrl, ImageView imageView) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }

    public static void loadImageCornor30(android.graphics.drawable.Drawable imgUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transforms(new RoundedCorners(50));
        Glide.with(imageView.getContext()).load(imgUrl).apply(requestOptions).into(imageView);
    }


    public static void loadImage(String imgUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.icon_image_default);
        requestOptions.error(R.mipmap.icon_image_error);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestOptions.skipMemoryCache(false);
        Glide.with(imageView.getContext()).load(imgUrl).apply(requestOptions)/*.transition(GenericTransitionOptions.with(R.anim.loading))*/.into(imageView);

        //Glide.with(imageView.getContext()).load(imgUrl).preload().onLoadStarted(imageView.getContext().getResources().getDrawable(R.mipmap.xingfucun_loading_img)).into(imageView);

    }

    public static void loadImageCornor30(String imgUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.icon_image_default);
        requestOptions.error(R.mipmap.icon_image_error);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestOptions.skipMemoryCache(false);
        requestOptions.transforms(new RoundedCorners(30));
        Glide.with(imageView.getContext()).load(imgUrl).apply(requestOptions)/*.transition(GenericTransitionOptions.with(R.anim.loading))*/.into(imageView);

        //Glide.with(imageView.getContext()).load(imgUrl).preload().onLoadStarted(imageView.getContext().getResources().getDrawable(R.mipmap.xingfucun_loading_img)).into(imageView);
    }

    public static void loadImageNoCache(String imgUrl, ImageView imageView) {
        RequestOptions requestOptions = RequestOptions
                .centerInsideTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
        Glide.with(imageView.getContext()).load(imgUrl).apply(requestOptions).into(imageView);
    }

    public static void loadImageIcon(String imgUrl, ImageView imageView) {
        String  updateTime= String.valueOf(System.currentTimeMillis());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.signature(new ObjectKey(updateTime));
        Glide.with(imageView.getContext()).load(imgUrl).apply(requestOptions).into(imageView);
    }

    public static void loadImageIconCornor30(String imgUrl, ImageView imageView) {
        String  updateTime= String.valueOf(System.currentTimeMillis());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.signature(new ObjectKey(updateTime));
        requestOptions.transforms(new RoundedCorners(30));
        Glide.with(imageView.getContext()).load(imgUrl).apply(requestOptions).into(imageView);
    }

    public static void clearMemoryCache() {
        //清理缓存
        Glide.get(MyApplication.getContext()).clearMemory();
    }

}
