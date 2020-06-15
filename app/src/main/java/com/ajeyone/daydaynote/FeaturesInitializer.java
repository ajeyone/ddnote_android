package com.ajeyone.daydaynote;

import com.ajeyone.framework.log.ALog;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class FeaturesInitializer {
    private static final String TAG = "FeaturesInitializer";

    public static void initialize() {
        autoLogin();
    }

    private static void autoLogin() {
        ParseUser.enableAutomaticUser();
        final ParseUser user = ParseUser.getCurrentUser();
        if (user == null) {
            ALog.e(TAG, "autoLogin: current user is null");
            return;
        }
        if (user.getObjectId() == null) {
            ALog.i(TAG, "autoLogin: user is new, not saved");
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        ALog.i(TAG, "autoLogin: save succeeded");
                        onUserSaved(user);
                    } else {
                        ALog.e(TAG, "autoLogin: save failed", e);
                    }
                }
            });
        } else {
            ALog.i(TAG, "autoLogin: user had been already saved before");
            onUserSaved(user);
        }
    }

    private static void onUserSaved(ParseUser user) {
    }
}
