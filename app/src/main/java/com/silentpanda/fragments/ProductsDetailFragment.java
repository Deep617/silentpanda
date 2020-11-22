package com.silentpanda.fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silentpanda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsDetailFragment extends BaseFragment {
    View view;
    ImageView addToCartBtn, buyNowBtn;
    TextView itemDesc, itemIdealFor, itemForOcassion, moreText;
    RecyclerView sizeRecyclerView, colorRecyclerView;
    ViewPager viewPager;
    int[] slideImagesList = {R.drawable.prolisting1, R.drawable.prolisting1, R.drawable.prolisting1};
    private TextView[] dots;
    int page_position = 0;
    LinearLayout ll_dots;
    String[] sizeList = {"5", "6", "7", "8", "9", "10"};
    int[] colorList = {R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark};
    Typeface regular;

    public ProductsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_details, container, false);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.GONE);
        ll_dots = (LinearLayout) view.findViewById(R.id.ll_dots);
        sizeRecyclerView = (RecyclerView) view.findViewById(R.id.sizeRecyclerView);
        colorRecyclerView = (RecyclerView) view.findViewById(R.id.colorRecyclerView);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        addToCartBtn = (ImageView) view.findViewById(R.id.addToCartBtn);
        buyNowBtn = (ImageView) view.findViewById(R.id.buyNowBtn);
        TextView sizeText = (TextView) view.findViewById(R.id.sizeText);
        TextView colorText = (TextView) view.findViewById(R.id.colorText);
        TextView aboutItemText = (TextView) view.findViewById(R.id.aboutItemText);
        itemDesc = (TextView) view.findViewById(R.id.itemDesc);
        itemIdealFor = (TextView) view.findViewById(R.id.itemIdealFor);
        itemForOcassion = (TextView) view.findViewById(R.id.itemForOcassion);
        moreText = (TextView) view.findViewById(R.id.moreText);
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        regular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Regular.otf");
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Medium.otf");
        sizeText.setTypeface(medium);
        colorText.setTypeface(medium);
        aboutItemText.setTypeface(medium);
        itemDesc.setTypeface(regular);
        itemIdealFor.setTypeface(regular);
        itemForOcassion.setTypeface(regular);
        moreText.setTypeface(regular);

        SlideImagesAdapter slideImageAdapter = new SlideImagesAdapter(getActivity(), slideImagesList);
        viewPager.setAdapter(slideImageAdapter);
        addBottomDots(0);


        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        sizeRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        sizeRecyclerView.setHasFixedSize(true);

        SizeAdapter sizeAdapter = new SizeAdapter(sizeList, getActivity(), regular);
        sizeRecyclerView.setAdapter(sizeAdapter);

        LinearLayoutManager horizontalLayoutManagaerForColor = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        colorRecyclerView.setLayoutManager(horizontalLayoutManagaerForColor);
        colorRecyclerView.setHasFixedSize(true);
        ColorAdapter colorAdapter = new ColorAdapter(colorList, getActivity());
        colorRecyclerView.setAdapter(colorAdapter);

        final Handler handler = new Handler();

        /*final Runnable update = new Runnable() {
            public void run() {
                if (page_position == slideImagesList.length) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager.setCurrentItem(page_position, true);
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 5000);*/

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[slideImagesList.length];
        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(Color.parseColor("#727272"));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#000000"));
    }

    private class SlideImagesAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;
        int[] imageList;

        public SlideImagesAdapter(FragmentActivity activity, int[] slideImagesList) {
            mContext = activity;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.imageList = slideImagesList;
        }

        @Override
        public int getCount() {
            return imageList.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(imageList[position]);
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    private class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.MyViewHolder> {
        String[] sizeList;
        Context context;
        Typeface regular;

        public SizeAdapter(String[] sizeList, FragmentActivity activity, Typeface regular) {
            this.sizeList = sizeList;
            this.context = activity;
            this.regular = regular;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView tvBorder;
            TextView sizeTv;


            public MyViewHolder(View view) {
                super(view);
                tvBorder = (ImageView) view.findViewById(R.id.tvBorder);
                sizeTv = (TextView) view.findViewById(R.id.sizeTv);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_size, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.sizeTv.setText(sizeList[position]);
            holder.sizeTv.setTypeface(regular);
            if (position == 0) {
                holder.tvBorder.setBackgroundResource(R.drawable.size_bg_selected);
                holder.sizeTv.setTextColor(Color.parseColor("#ff0000"));
            } else {
                holder.tvBorder.setBackgroundResource(R.drawable.size_bg_not_selected);
                holder.sizeTv.setTextColor(Color.parseColor("#727272"));
            }

        }


        @Override
        public int getItemCount() {
            return sizeList.length;
        }
    }

    private class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyColorHolder> {
        int[] colorList;
        Context context;

        public ColorAdapter(int[] colorList, FragmentActivity activity) {
            this.colorList = colorList;
            this.context = activity;
        }

        @Override
        public MyColorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_size, parent, false);

            return new MyColorHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyColorHolder holder, int position) {
            holder.sizeTv.setVisibility(View.GONE);
            holder.tvBorder.setBackgroundResource(colorList[position]);
        }

        @Override
        public int getItemCount() {
            return colorList.length;
        }

        public class MyColorHolder extends RecyclerView.ViewHolder {
            ImageView tvBorder;
            TextView sizeTv;


            public MyColorHolder(View view) {
                super(view);
                tvBorder = (ImageView) view.findViewById(R.id.tvBorder);
                sizeTv = (TextView) view.findViewById(R.id.sizeTv);
            }
        }
    }
}


