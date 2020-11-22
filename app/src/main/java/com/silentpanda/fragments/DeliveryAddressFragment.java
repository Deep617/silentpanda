package com.silentpanda.fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silentpanda.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryAddressFragment extends BaseFragment {
    View view;
    ListView lvList;
    ImageView addAddressBtn, nextBtn;
    String[] addressList = {"J-1501, Skytech mattroit, Sector76", "k-2, Skytech tower, NewYork"};

    public DeliveryAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_delivery_address, container, false);
        lvList = (ListView) view.findViewById(R.id.lvList);
        addAddressBtn = (ImageView) view.findViewById(R.id.addAddressBtn);
        nextBtn = (ImageView) view.findViewById(R.id.nextBtn);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.GONE);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Regular.otf");
        TextView header = (TextView) view.findViewById(R.id.header);
        header.setTypeface(face);

        DeliveryAddressAdapter deliveryAddressAdapter = new DeliveryAddressAdapter(addressList, regular);
        lvList.setAdapter(deliveryAddressAdapter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new PaymentFragment();
                activityCallback.onButtonClick(fr, false);
            }
        });
        return view;
    }

    private class DeliveryAddressAdapter extends BaseAdapter {
        String[] deliveryAddressLst;
        Typeface sanRegular;

        public DeliveryAddressAdapter(String[] addressList, Typeface regular) {
            this.deliveryAddressLst = addressList;
            this.sanRegular = regular;
        }

        @Override
        public int getCount() {
            return deliveryAddressLst.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listview;
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                listview = new View(getActivity());
                listview = inflater.inflate(R.layout.row_delivery_address, null);
                ImageView editAddressBtn = (ImageView) listview.findViewById(R.id.editAddressBtn);
                ImageView deleteAddressBtn = (ImageView) listview.findViewById(R.id.deleteAddressBtn);
                TextView deliveryAddress = (TextView) listview.findViewById(R.id.deliveryAddress);
                TextView addressCity = (TextView) listview.findViewById(R.id.addressCity);
                TextView pinCode = (TextView) listview.findViewById(R.id.pinCode);
                TextView contactNo = (TextView) listview.findViewById(R.id.contactNo);

                deliveryAddress.setText(deliveryAddressLst[position]);
                deliveryAddress.setTypeface(sanRegular);
                addressCity.setTypeface(sanRegular);
                pinCode.setTypeface(sanRegular);
                contactNo.setTypeface(sanRegular);
            } else {
                listview = (View) convertView;
            }

            return listview;
        }
    }
}
