package com.silentpanda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.silentpanda.R;
import com.silentpanda.fragments.BaseFragment;
import com.silentpanda.fragments.CartFragment;
import com.silentpanda.fragments.HomeFragment;
import com.silentpanda.fragments.MyProfileFragment;
import com.silentpanda.fragments.MyWishListFragment;
import com.silentpanda.interfaces.ToolbarListner;

public class HomeActivity extends AppCompatActivity implements ToolbarListner {
    FragmentManager fragmentManager;
    FrameLayout fragment_container;
    FragmentTransaction ft;
    private BaseFragment fragment;
    ImageView scanBtn, homeBtn, cartBtn, wishListBtn, profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragment_container = (FrameLayout) findViewById(R.id.fragment_container);
        scanBtn = (ImageView) findViewById(R.id.scanBtn);
        homeBtn = (ImageView) findViewById(R.id.homeBtn);
        cartBtn = (ImageView) findViewById(R.id.cartBtn);
        wishListBtn = (ImageView) findViewById(R.id.wishListBtn);
        profileBtn = (ImageView) findViewById(R.id.profileBtn);

        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment());
        ft.commit();

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.home_un_selected);
                wishListBtn.setImageResource(R.drawable.wishlist_un_selected);
                profileBtn.setImageResource(R.drawable.profile_un_selected);
                cartBtn.setImageResource(R.drawable.shopping_cart_not_sel);
                Intent intent = new Intent(HomeActivity.this, ScanningActivity.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.home_selected);
                wishListBtn.setImageResource(R.drawable.wishlist_un_selected);
                profileBtn.setImageResource(R.drawable.profile_un_selected);
                cartBtn.setImageResource(R.drawable.shopping_cart_not_sel);
                fragmentManager = getSupportFragmentManager();
                ft = fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment());
                ft.commit();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.home_un_selected);
                wishListBtn.setImageResource(R.drawable.wishlist_un_selected);
                profileBtn.setImageResource(R.drawable.profile_selected);
                cartBtn.setImageResource(R.drawable.shopping_cart_not_sel);
                fragmentManager = getSupportFragmentManager();
                ft = fragmentManager.beginTransaction().replace(R.id.fragment_container, new MyProfileFragment());
                ft.commit();
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.home_un_selected);
                wishListBtn.setImageResource(R.drawable.wishlist_un_selected);
                profileBtn.setImageResource(R.drawable.profile_un_selected);
                cartBtn.setImageResource(R.drawable.shopping_cart_selected);
                fragmentManager = getSupportFragmentManager();
                ft = fragmentManager.beginTransaction().replace(R.id.fragment_container, new CartFragment());
                ft.commit();
            }
        });
        wishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.home_un_selected);
                wishListBtn.setImageResource(R.drawable.wishlist_selected);
                profileBtn.setImageResource(R.drawable.profile_un_selected);
                cartBtn.setImageResource(R.drawable.shopping_cart_not_sel);
                fragmentManager = getSupportFragmentManager();
                ft = fragmentManager.beginTransaction().replace(R.id.fragment_container, new MyWishListFragment());
                ft.commit();
            }
        });
    }

    @Override
    public void onButtonClick(Fragment newfragment, Boolean isCommingBack) {
        fragment = (BaseFragment) newfragment;
        FragmentManager frgManager = this.getSupportFragmentManager();
        fragment = (BaseFragment) newfragment;

        if (isCommingBack) {
            onBackPressed();
        } else {
            FragmentTransaction ft = frgManager.beginTransaction();
            ft.setCustomAnimations(R.anim.enter, R.anim.exit,
                    R.anim.slide_in_left, R.anim.slide_in_right);
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(fragment.getClass().getName());
            ft.commit();
        }
    }

    @Override
    public void onButtonClickNoBack(Fragment newfragment) {
        fragment = (BaseFragment) newfragment;
        FragmentManager frgManager = this.getSupportFragmentManager();
        fragment = (BaseFragment) newfragment;
        FragmentTransaction ft = frgManager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit,
                R.anim.slide_in_left, R.anim.slide_in_right);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        homeBtn.setImageResource(R.drawable.home_selected);

    }
}
