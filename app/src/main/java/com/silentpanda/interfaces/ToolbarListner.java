package com.silentpanda.interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by sujata on 8/11/17.
 */

public interface ToolbarListner {
    void onButtonClick(Fragment fragment, Boolean isCommingBack);
    public void onButtonClickNoBack(Fragment fragment);
}
