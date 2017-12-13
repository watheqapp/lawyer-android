package com.watheq.laywer.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by mahmoud.diab on 11/13/2017.
 */

public abstract class BaseFragment extends Fragment {

    private FragmentNavigation mFragmentNavigation;
    private BaseActivity parentActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation) {
            mFragmentNavigation = (FragmentNavigation) context;
        }
        if (context instanceof BaseActivity) {
            parentActivity = (BaseActivity) context;
        }
    }

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, root);
        inOnCreateView(root, container, savedInstanceState);
        return root;
    }

    @LayoutRes
    public abstract int getLayoutResource();

    public abstract void inOnCreateView(View root, ViewGroup container, @Nullable Bundle savedInstanceState);

    public void showNotification(@StringRes int msgRes) {
        if (parentActivity == null) return;
        parentActivity.showSnackBar(msgRes);
    }

    public void showNotification(String msg) {
        if (parentActivity == null) return;
        parentActivity.showSnackBar(msg);
    }

    public void errorHandlerMsg() {

    }
}
