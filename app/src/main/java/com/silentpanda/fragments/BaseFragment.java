package com.silentpanda.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.silentpanda.interfaces.ToolbarListner;

/**
 * Created by rajesh on 8/11/17.
 */

public class BaseFragment extends Fragment {
    ToolbarListner activityCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (ToolbarListner) activity;
        }   catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ToolbarListener");
        }
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            // mfragmentButtonClickListner = (FragmentButtonClickListner)
            // getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentButtonClickListner");
        }
    }
}
