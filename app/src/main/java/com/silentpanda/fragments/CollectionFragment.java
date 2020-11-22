package com.silentpanda.fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silentpanda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends BaseFragment {
    View view;
    ListView lvList;
    int[] collectionList = {R.drawable.apparels, R.drawable.footwear, R.drawable.footwear};

    public CollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_collection, container, false);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.VISIBLE);
        lvList = (ListView) view.findViewById(R.id.lvList);
        TextView header = (TextView) view.findViewById(R.id.header);
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        header.setTypeface(bold);
        CollectionsAdapter collectionAdapter = new CollectionsAdapter(collectionList);
        lvList.setAdapter(collectionAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fr = new ProductListingFragment();
                activityCallback.onButtonClick(fr, false);
            }
        });

        return view;
    }

    private class CollectionsAdapter extends BaseAdapter {
        private int[] imageId;

        public CollectionsAdapter(int[] collectionList) {
            this.imageId = collectionList;
        }

        @Override
        public int getCount() {
            return imageId.length;
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
            View view1;
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                view1 = new View(getActivity());
                view1 = inflater.inflate(R.layout.row_collection, null);
                ImageView imageView = (ImageView) view1.findViewById(R.id.ivImage);
                imageView.setBackgroundResource(imageId[position]);

            } else {
                view1 = (View) convertView;
            }
            return view1;
        }
    }
}
