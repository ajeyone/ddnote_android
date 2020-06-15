package com.ajeyone.framework.log;

import android.util.Log;

public class ALog {
    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    public static void w(String tag, String message) {
        Log.w(tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    public static void e(String tag, String message, Throwable e) {
        Log.e(tag, message, e);
    }
}
