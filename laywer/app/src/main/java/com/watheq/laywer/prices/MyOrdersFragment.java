package com.watheq.laywer.prices;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.watheq.laywer.R;
import com.watheq.laywer.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends BaseFragment {


    public static MyOrdersFragment getInstance(){
        return new MyOrdersFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_prices;
    }

    @Override
    public void inOnCreateView(View root, ViewGroup container, @Nullable Bundle savedInstanceState) {
//on create view
    }

}
