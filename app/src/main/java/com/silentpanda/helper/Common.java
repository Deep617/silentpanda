package com.silentpanda.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.silentpanda.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rajesh on 9/11/17.
 */

public class Common {

    private Context mcontext;
    private static Common mInstance = null;
    public static boolean debug = true;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    protected Dialog loadDialog = null;


    public Common(Context ctx) {
        mcontext = ctx;
        pref = mcontext.getSharedPreferences(Constants.preference, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static synchronized Common getNewInstance(Context ctx) {

        mInstance = new Common(ctx);

        return mInstance;
    }

    public static void showKeyboard(Activity activity) {


        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }

    public static float getDistance(double startLati, double startLongi,
                                    double goalLati, double goalLongi) {
        Location locationA = new Location("point A");
        locationA.setLatitude(startLati);
        locationA.setLongitude(startLongi);
        Location locationB = new Location("point B");
        locationB.setLatitude(goalLati);
        locationB.setLongitude(goalLongi);
        float distance = (float) (locationA.distanceTo(locationB) * 0.000621371);
        return distance;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mcontext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();

        return activeNetworkInfo != null;
    }

    public void showShortToast(String output) {
        Toast.makeText(mcontext, output, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String output) {
        Toast.makeText(mcontext, output, Toast.LENGTH_LONG).show();
    }

    public boolean setBooleanValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
        return value;
    }

    public boolean getBooleanValue(String key) {
        return pref.getBoolean(key, false);
    }

    public void removeKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    public String getStringValue(String key) {
        return pref.getString(key, "");
    }

    public void setStringValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }


    public void setIntValue(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getIntValue(String key) {
        return pref.getInt(key, -1);
    }

    public void showSpinner(Context context) {
        Log.e("BaseActivity", "showSpinner");
        if (loadDialog != null) {
            if (loadDialog.isShowing())
                loadDialog.dismiss();
        }
        loadDialog = new Dialog(context, R.style.TransparentDialogTheme);
        loadDialog.setContentView(R.layout.spinner_rotate);
        loadDialog.setCanceledOnTouchOutside(false);

        ImageView ivLoader = (ImageView) loadDialog.findViewById(R.id.ivSpinner);

        Animation animRotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
        ivLoader.startAnimation(animRotate);
        loadDialog.show();
    }

}
