package com.liecen.lifeutils;
/*
 *  @author ： Life
 *  onCreate DateTime 2020/12/24  : 11:08
 *  in order to : 处理拍照照片，uri转 path ，在判断是否为小米倾倒照片
 */

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraPhotoLubanUtils {
    /**
     * 根据Uri获取path;
     * 全平台处理方法
     */
    public String getPath(final Context context, final Uri uri) throws Exception {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        final boolean isN = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;

        if (isN) {
            return getFilePathForN(context, uri);
        }

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.parseLong(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] { split[1] };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * 获取此Uri的数据列的值。这对于MediaStore uri和其他基于文件的内容提供程序非常有用。
     */
    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        android.database.Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (IllegalArgumentException e) {
            // do nothing
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * 将bitmap作为一张图片存入到指定路径
     *
     * @param bitmap
     * @param file
     */
    public void storeImage(Bitmap bitmap, File file) {
        FileOutputStream os;
        try {
            os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Uri getImageStreamFromExternal(String path) {
        File externalPubPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File picPath = new File(path);
        Uri uri = null;
        if (picPath.exists()) {
            uri = Uri.fromFile(picPath);
        }

        return uri;
    }

    private Uri getMediaUriFromPath(Context context, String path) {
        Uri mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        android.database.Cursor cursor = context.getContentResolver().query(mediaUri, null, MediaStore.Images.Media.DISPLAY_NAME + "= ?",
                new String[] { path.substring(path.lastIndexOf("/") + 1) }, null);

        Uri uri = null;
        if (cursor.moveToFirst()) {
            uri = ContentUris.withAppendedId(mediaUri,
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
        }
        cursor.close();
        return uri;
    }
    /**
     * 把uri转为绝对路径
     * @param context
     * @param uri
     * @return
     */

    private String getFilePathForN(Context context, Uri uri) {
        try {
            android.database.Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();
            String name = (returnCursor.getString(nameIndex));
            File file = new File(context.getFilesDir(), name);
            java.io.InputStream inputStream = context.getContentResolver().openInputStream(uri);
            FileOutputStream outputStream = new FileOutputStream(file);
            int read = 0;
            int maxBufferSize = 1 * 1024 * 1024;
            int bytesAvailable = inputStream.available();

            int bufferSize = Math.min(bytesAvailable, maxBufferSize);

            final byte[] buffers = new byte[bufferSize];
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }
            returnCursor.close();
            inputStream.close();
            outputStream.close();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //以下方法是处理小米照片旋转问题

    public String judgmentXiaoMi4PathSting(String photoPath) {
        if (!"".equals(photoPath) && "Xiaomi".equals(Build.MANUFACTURER)) { // 小米手机横竖颠倒问题
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            bitmap = rotaingImageView(readPictureDegree(photoPath), bitmap);
            int lastIndex = photoPath.lastIndexOf(".");
            String startString = photoPath.substring(0, lastIndex);
            String endString = photoPath.substring(lastIndex);
            String newPath = startString /* + "backup" */ + endString;
            storeImage(bitmap, new File(newPath));
            return newPath;
        } else {
            return photoPath;
        }
    }
    public Uri judgmentXiaoMi4Uri(String photoPath) {
        if (!"".equals(photoPath) && "Xiaomi".equals(Build.MANUFACTURER)) { // 小米手机横竖颠倒问题
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            bitmap = rotaingImageView(readPictureDegree(photoPath), bitmap);
            int lastIndex = photoPath.lastIndexOf(".");
            String startString = photoPath.substring(0, lastIndex);
            String endString = photoPath.substring(lastIndex);
            String newPath = startString /* + "backup" */ + endString;
            storeImage(bitmap, new File(newPath));
            return getImageStreamFromExternal(newPath);
        } else {
            return getImageStreamFromExternal(photoPath);
        }
    }

    /**
     * 读取照片旋转角度
     * @param path 照片路径
     * @return 角度
     */
    private int readPictureDegree(String path) {
        int degree = 0;
        try {

            ExifInterface exifInterface = new ExifInterface(path);
            int orientation =
                    exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param angle 被旋转角度
     * @param bitmap 图片对象
     * @return 旋转后的图片
     */
    private Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        matrix.postRotate(angle);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }
}
