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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.silentpanda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListingFragment extends BaseFragment {
    View view;
    GridView productListGridView;
    ImageView filterBtn;
    int[] productList = {R.drawable.prolisting1, R.drawable.prolisting1, R.drawable.prolisting1};

    public ProductListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_listing, container, false);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.VISIBLE);
        productListGridView = (GridView) view.findViewById(R.id.productListGridView);
        filterBtn = (ImageView) view.findViewById(R.id.filterBtn);
        TextView header = (TextView) view.findViewById(R.id.header);
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Regular.otf");
        header.setTypeface(bold);
        ProductListAdapter productListAdapter = new ProductListAdapter(productList, regular);
        productListGridView.setAdapter(productListAdapter);

        productListGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fr = new ProductsDetailFragment();
                activityCallback.onButtonClick(fr, false);
            }
        });
        return view;
    }

    private class ProductListAdapter extends BaseAdapter {
        private int[] imageId;
        Typeface regular;

        public ProductListAdapter(int[] productList, Typeface regular) {
            this.imageId = productList;
            this.regular = regular;

        }

        @Override
        public int getCount() {
            return imageId.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View grid;
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                grid = new View(getActivity());
                grid = inflater.inflate(R.layout.row_product_listing, null);
                ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
                ImageView favBtn = (ImageView) grid.findViewById(R.id.favBtn);
                TextView productName = (TextView) grid.findViewById(R.id.productName);
                TextView productPrice = (TextView) grid.findViewById(R.id.productPrice);
                favBtn.setVisibility(View.GONE);
                productName.setTypeface(regular);
                productPrice.setTypeface(regular);
                imageView.setBackgroundResource(imageId[position]);

            } else {
                grid = (View) convertView;
            }

            return grid;
        }
    }
}
