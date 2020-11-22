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
public class OrderReviewFragment extends BaseFragment {
    View view;
    TextView totalAmount, subTotalAmnt, deliveryCharge, totalSum, cardNumber, cardHolderName, expiryDate, deliveryAddress, addressCity, pinCode, contactNo;
    ListView lvList;
    int[] cartItemImageList = {R.drawable.prolisting1, R.drawable.prolisting1};

    public OrderReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_review, container, false);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.GONE);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Medium.otf");
        TextView header = (TextView) view.findViewById(R.id.header);
        lvList = (ListView) view.findViewById(R.id.lvList);
        TextView delvryDetailsText = (TextView) view.findViewById(R.id.delvryDetailsText);
        TextView paymentMethodText = (TextView) view.findViewById(R.id.paymentMethodText);
        TextView deliveryAddressText = (TextView) view.findViewById(R.id.deliveryAddressText);
        totalAmount = (TextView) view.findViewById(R.id.totalAmount);
        subTotalAmnt = (TextView) view.findViewById(R.id.subTotalAmnt);
        deliveryCharge = (TextView) view.findViewById(R.id.deliveryCharge);
        totalSum = (TextView) view.findViewById(R.id.totalSum);
        cardNumber = (TextView) view.findViewById(R.id.cardNumber);
        cardHolderName = (TextView) view.findViewById(R.id.cardHolderName);
        expiryDate = (TextView) view.findViewById(R.id.expiryDate);
        deliveryAddress = (TextView) view.findViewById(R.id.deliveryAddress);
        addressCity = (TextView) view.findViewById(R.id.addressCity);
        pinCode = (TextView) view.findViewById(R.id.pinCode);
        contactNo = (TextView) view.findViewById(R.id.contactNo);
        header.setTypeface(face);
        delvryDetailsText.setTypeface(medium);
        paymentMethodText.setTypeface(medium);
        deliveryAddressText.setTypeface(medium);
        totalAmount.setTypeface(medium);
        subTotalAmnt.setTypeface(medium);
        deliveryCharge.setTypeface(medium);
        totalSum.setTypeface(medium);
        cardNumber.setTypeface(medium);
        cardHolderName.setTypeface(medium);
        expiryDate.setTypeface(medium);
        deliveryAddress.setTypeface(medium);
        addressCity.setTypeface(medium);
        pinCode.setTypeface(medium);
        contactNo.setTypeface(medium);

        ReviewCartAdapter reviewCartAdapter = new ReviewCartAdapter(cartItemImageList, medium);
        lvList.setAdapter(reviewCartAdapter);

        return view;
    }

    private class ReviewCartAdapter extends BaseAdapter {
        int[] cartimageList;
        Typeface sanMedium;

        public ReviewCartAdapter(int[] cartItemImageList, Typeface medium) {
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
                listview = inflater.inflate(R.layout.row_review_cart, null);
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
