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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.silentpanda.R;
import com.silentpanda.helper.Common;
import com.silentpanda.utils.ListUtility;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    View view;
    GridView categoryView, trendingView;
    SliderLayout slider;
    int[] trendingList = {R.drawable.trending1, R.drawable.trending2, R.drawable.trending3};
    int[] categoryList = {R.drawable.hospitality, R.drawable.gym, R.drawable.dining};
    Common common;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        common = Common.getNewInstance(getActivity());
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.VISIBLE);
        categoryView = (GridView) view.findViewById(R.id.categoryView);
        trendingView = (GridView) view.findViewById(R.id.trendingView);
        slider = (SliderLayout) view.findViewById(R.id.slider);
        Typeface medium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Medium.otf");
        Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Bold.otf");
        TextView trending = (TextView) view.findViewById(R.id.trending);
        TextView more = (TextView) view.findViewById(R.id.more);
        trending.setTypeface(medium);
        more.setTypeface(bold);
        TrendingAdapter trendingAdapter = new TrendingAdapter(trendingList);
        trendingView.setAdapter(trendingAdapter);

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        categoryView.setAdapter(categoryAdapter);
        ListUtility.setGridViewHeightBasedOnItems(categoryView);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("home1", R.drawable.home1);
        file_maps.put("home2", R.drawable.home2);
        file_maps.put("Home3", R.drawable.home3);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Fragment fr = new CollectionFragment();
                    activityCallback.onButtonClick(fr, false);
                }
            });
            //add your extra information
         /*   textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);*/

            slider.addSlider(textSliderView);
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);
        slider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        categoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                common.setIntValue("position", position);
                Fragment fr = new EndorsementFragment();
                activityCallback.onButtonClick(fr, false);
            }
        });
        return view;
    }

    private class TrendingAdapter extends BaseAdapter {
        private int[] imageId;

        public TrendingAdapter(int[] trendingList) {
            this.imageId = trendingList;
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
                grid = inflater.inflate(R.layout.row_trending_item, null);
                ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
                imageView.setBackgroundResource(imageId[position]);

            } else {
                grid = (View) convertView;
            }

            return grid;
        }
    }

    private class CategoryAdapter extends BaseAdapter {
        private int[] categoryImages;

        public CategoryAdapter(int[] categoryList) {
            this.categoryImages = categoryList;
        }

        @Override
        public int getCount() {
            return categoryImages.length;
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
                grid = inflater.inflate(R.layout.row_category, null);
                ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
                imageView.setBackgroundResource(categoryImages[position]);
            } else {
                grid = (View) convertView;
            }

            return grid;
        }
    }
}
