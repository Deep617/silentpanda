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
public class PaymentFragment extends BaseFragment {
    View view;
    ListView lvList;
    ImageView addNewCardBtn, nextBtn;
    String[] cardList = {"Visa *****234", "Visa *******456"};

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        lvList = (ListView) view.findViewById(R.id.lvList);
        addNewCardBtn = (ImageView) view.findViewById(R.id.addNewCardBtn);
        nextBtn = (ImageView) view.findViewById(R.id.nextBtn);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.GONE);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Regular.otf");
        TextView header = (TextView) view.findViewById(R.id.header);
        header.setTypeface(face);

        PaymentAdapter paymentAdapter = new PaymentAdapter(cardList, regular);
        lvList.setAdapter(paymentAdapter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new OrderReviewFragment();
                activityCallback.onButtonClick(fr, false);
            }
        });
        return view;
    }

    private class PaymentAdapter extends BaseAdapter {
        String[] savedCardList;
        Typeface sanRegular;

        public PaymentAdapter(String[] cardList, Typeface regular) {
            this.savedCardList = cardList;
            this.sanRegular = regular;

        }

        @Override
        public int getCount() {
            return savedCardList.length;
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
                listview = inflater.inflate(R.layout.row_payment_card, null);
                ImageView editCardBtn = (ImageView) listview.findViewById(R.id.editCardBtn);
                ImageView deleteCardBtn = (ImageView) listview.findViewById(R.id.deleteCardBtn);
                TextView cardNumber = (TextView) listview.findViewById(R.id.cardNumber);
                TextView cardHolderName = (TextView) listview.findViewById(R.id.cardHolderName);
                TextView expiryDate = (TextView) listview.findViewById(R.id.expiryDate);

                cardNumber.setText(savedCardList[position]);
                cardNumber.setTypeface(sanRegular);
                cardHolderName.setTypeface(sanRegular);
                expiryDate.setTypeface(sanRegular);
            } else {
                listview = (View) convertView;
            }

            return listview;
        }
    }
}
