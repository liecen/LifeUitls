package com.liecen.lifeutils;




public class LogUtil {

    private static final String TAG = "vastweb";

    // 这个方法都打印log;便于观察
    public static void vastWeb(Object message) {
         Timber.tag(TAG).i(message.toString());
    }

    public static void i(Object message) {
//        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(TAG).i(message.toString());
//        }
    }

    public static void info(Object message) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag("vastweb").i(message.toString());
        }
    }

    public static void e(Object message) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(TAG).e(message.toString());
        }
    }

    public static void d(Object message) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(TAG).d(message.toString());
        }
    }

    public static void w(Object message) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(TAG).w(message.toString());
        }
    }

    public static void v(Object message) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(TAG).v(message.toString());
        }
    }


    // 下面是传入自定义tag的函数
    public static void i(String tag, Object msg) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(tag).i(msg.toString());
        }
    }

    public static void d(String tag, Object msg) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(tag).d(msg.toString());
        }
    }

    public static void e(String tag, Object msg) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(tag).e(msg.toString());
        }
    }

    public static void w(String tag, Object msg) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(tag).w(msg.toString());
        }
    }

    public static void v(String tag, Object msg) {
        if (BuildConfig.LOG_DEBUG) {
            Timber.tag(tag).v(msg.toString());
        }
    }
}
