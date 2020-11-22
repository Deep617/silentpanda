package com.silentpanda.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silentpanda.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends BaseFragment {
    View view;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.VISIBLE);
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Medium.otf");
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        TextView myProfile = (TextView) view.findViewById(R.id.myProfile);
        TextView myOrder = (TextView) view.findViewById(R.id.myOrder);
        TextView scannedCodes = (TextView) view.findViewById(R.id.scannedCodes);
        TextView setting = (TextView) view.findViewById(R.id.setting);
        TextView other = (TextView) view.findViewById(R.id.other);
        TextView header = (TextView) view.findViewById(R.id.header);
        myProfile.setTypeface(medium);
        myOrder.setTypeface(medium);
        scannedCodes.setTypeface(medium);
        setting.setTypeface(medium);
        other.setTypeface(medium);
        header.setTypeface(bold);
        return view;
    }

}
