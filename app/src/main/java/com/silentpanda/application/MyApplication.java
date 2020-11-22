package com.silentpanda.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.silentpanda.helper.Constants;

/**
 * Created by sujata on 7/11/17.
 */

public class MyApplication extends Application {
    public static String USER;
    public static String NAME;
    public static String MOBILE;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String getUser(Context context) {
        if (USER == null || USER.length() == 0) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            USER = preferences.getString(Constants.USER, "");
        }
        return USER;
    }

    public static String getName(Context context) {
        if (NAME == null || NAME.length() == 0) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            NAME = preferences.getString(Constants.NAME, "");
        }
        return NAME;
    }

    public static String getMobile(Context context) {
        if (MOBILE == null || MOBILE.length() == 0) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            MOBILE = preferences.getString(Constants.MOBILE, "");
        }
        return MOBILE;
    }
}
