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
public class CartFragment extends BaseFragment {
    View view;
    ListView lvList;
    TextView subTotal, tvTax;
    ImageView checkoutBtn;
    int[] cartItemImageList = {R.drawable.prolisting1, R.drawable.prolisting1};
    Typeface medium;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        subTotal = (TextView) view.findViewById(R.id.subTotal);
        tvTax = (TextView) view.findViewById(R.id.tvTax);
        lvList = (ListView) view.findViewById(R.id.lvList);
        checkoutBtn = (ImageView) view.findViewById(R.id.checkoutBtn);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.VISIBLE);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        Typeface italic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-RegularItalic.otf");
        medium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Medium.otf");
        TextView header = (TextView) view.findViewById(R.id.header);

        header.setTypeface(face);
        subTotal.setTypeface(face);
        tvTax.setTypeface(italic);

        CartListAdapter cartListAdapter = new CartListAdapter(cartItemImageList, medium);
        lvList.setAdapter(cartListAdapter);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new DeliveryAddressFragment();
                activityCallback.onButtonClick(fr, false);
            }
        });
        return view;
    }

    private class CartListAdapter extends BaseAdapter {
        int[] cartimageList;
        Typeface sanMedium;

        public CartListAdapter(int[] cartItemImageList, Typeface medium) {
            this.cartimageList = cartItemImageList;
            this.sanMedium = medium;
        }

        @Override
        public int getCount() {
            return cartimageList.length;
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
                listview = inflater.inflate(R.layout.row_cart, null);
                ImageView imageView = (ImageView) listview.findViewById(R.id.cartItemImage);
                TextView itemName = (TextView) listview.findViewById(R.id.itemName);
                TextView itemColor = (TextView) listview.findViewById(R.id.itemColor);
                TextView itemSize = (TextView) listview.findViewById(R.id.itemSize);
                TextView quantityText = (TextView) listview.findViewById(R.id.quantityText);
                TextView itemQuantity = (TextView) listview.findViewById(R.id.itemQuantity);
                TextView itemPrize = (TextView) listview.findViewById(R.id.itemPrize);

                itemName.setTypeface(sanMedium);
                itemColor.setTypeface(sanMedium);
                itemSize.setTypeface(sanMedium);
                quantityText.setTypeface(sanMedium);
                itemQuantity.setTypeface(sanMedium);
                itemPrize.setTypeface(sanMedium);
                imageView.setBackgroundResource(cartimageList[position]);
            } else {
                listview = (View) convertView;
            }

            return listview;
        }
    }
}
