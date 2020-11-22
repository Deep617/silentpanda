package com.silentpanda.fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.silentpanda.R;
import com.silentpanda.helper.Common;
import com.silentpanda.utils.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EndorsementFragment extends BaseFragment {
    ViewPager brandListviewPager;
    View view;
    SlidingTabLayout slidingTabLayout;
    private Common common;
    int[] itemList = {R.drawable.endorse1, R.drawable.endorse1, R.drawable.endorse1};
    int pos = -1;

    public EndorsementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_endorsement, container, false);
        View footerLayout = getActivity().findViewById(R.id.footer);
        footerLayout.setVisibility(View.VISIBLE);
        brandListviewPager = (ViewPager) view.findViewById(R.id.brandListviewPager);
        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        common = Common.getNewInstance(getActivity());
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Semibold.otf");
        TextView header=(TextView)view.findViewById(R.id.header);
        header.setTypeface(face);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        slidingTabLayout.setTextColor(getResources().getColor(R.color.secondaryText));
        slidingTabLayout.setSelectedTextColor(getResources().getColor(R.color.red));
        slidingTabLayout.setTextSizeAndStyle(14, null);
        slidingTabLayout.setTextPadding(0, 17, 0, 17);

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.red);
            }

            @Override
            public int getDividerColor(int position) {
                return 0;
            }
        });
        try {
            List<String> tabTitleList = new ArrayList<>();
            tabTitleList.add("HOSPITALITY");
            tabTitleList.add("GYM");
            tabTitleList.add("DINING");
            tabTitleList.add("ENTERTAINMENT");

            brandListviewPager.setOffscreenPageLimit(tabTitleList.size() - 1);
            brandListviewPager.setAdapter(new BrandListViewPagerAdapter(getActivity(), tabTitleList));
            slidingTabLayout.setTextWidth(displaymetrics.widthPixels / tabTitleList.size());
            slidingTabLayout.setViewPager(brandListviewPager, Typeface.createFromAsset(getActivity().getAssets(), "fonts/SanFranciscoText-Bold.otf"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        pos = common.getIntValue("position");
        brandListviewPager.setCurrentItem(pos);
        return view;
    }

    private class BrandListViewPagerAdapter extends PagerAdapter {
        private int tabsCount;
        private List<String> tabTitleList = new ArrayList<String>();
        private Context context;

        public BrandListViewPagerAdapter(FragmentActivity endorsementFragment, List<String> tabTitleList) {
            this.context = endorsementFragment;
            this.tabTitleList = tabTitleList;
            tabsCount = tabTitleList.size();
        }

        @Override
        public int getCount() {
            return tabsCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitleList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = (View) getActivity().getLayoutInflater().inflate(R.layout.official_public_grid, container, false);
            GridView gridView = (GridView) view.findViewById(R.id.gridview);
            TextView noimage_tv = (TextView) view.findViewById(R.id.noimage_tv);

            if (position == 0 | position == 1 || position == 2 || position == 3) {
                if (itemList.length != 0) {
                    noimage_tv.setVisibility(View.GONE);
                    GridItemAdapter gridItemAdapter = new GridItemAdapter(itemList);
                    gridView.setAdapter(gridItemAdapter);
                   /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), ImageSliderActivity.class);
                            intent.putExtra("pos", position);
                            intent.putParcelableArrayListExtra("imageList", publicSelectModelArrayList);
                            startActivity(intent);
                        }
                    });*/
                } else {
                    noimage_tv.setVisibility(View.VISIBLE);
                }
            }

            container.addView(view);
            return view;
        }
    }

    private class GridItemAdapter extends BaseAdapter {
        private int[] categoryImages;

        public GridItemAdapter(int[] categoryList) {
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
                grid = inflater.inflate(R.layout.row_endorse, null);
                ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
                imageView.setBackgroundResource(categoryImages[position]);
            } else {
                grid = (View) convertView;
            }

            return grid;
        }
    }
}
