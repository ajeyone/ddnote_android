package com.ajeyone.daydaynote;

import android.app.Application;

import com.ajeyone.framework.log.ALog;
import com.parse.Parse;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        ALog.i(TAG, "onCreate: ");
        initParse();
        FeaturesInitializer.initialize();
    }

    private void initParse() {
        ALog.i(TAG, "initParse: ");
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.PARSE_APPLICATION_ID)
                .server(BuildConfig.PARSE_SERVER)
                .build()
        );
    }
}
