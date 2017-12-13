package com.watheq.laywer.delegation;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.watheq.laywer.R;
import com.watheq.laywer.base.BaseFragment;
import com.watheq.laywer.base.BaseHandlingErrors;
import com.watheq.laywer.model.MainCategoriesResponse;
import com.watheq.laywer.utils.UserManager;
import com.watheq.laywer.views.EmptyView;
import com.watheq.laywer.views.RecyclerViewEmptySupport;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DelegationFragment extends BaseFragment implements BaseHandlingErrors {

    private static final String TAG = "DelegationFragment";
    private DelegationViewModel delegationViewModel;
    private MainCategoriesListAdapter categoriesListAdapter;
    private MainCategoriesResponse mainCategoriesResponse;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerFrameLayout;
    @BindView(R.id.recycler_view)
    RecyclerViewEmptySupport recyclerView;
    @BindView(R.id.empty_view)
    EmptyView emptyView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    public static DelegationFragment getInstance() {
        return new DelegationFragment();
    }

    private final Observer<MainCategoriesResponse> getCategories = new Observer<MainCategoriesResponse>() {
        @Override
        public void onChanged(@Nullable MainCategoriesResponse mainCategoriesResponse) {
            Log.d(TAG, "onChanged: " + mainCategoriesResponse);
            DelegationFragment.this.mainCategoriesResponse = mainCategoriesResponse;
            shimmerFrameLayout.stopShimmerAnimation();
            shimmerFrameLayout.setVisibility(View.GONE);
            if (categoriesListAdapter == null) {
                categoriesListAdapter = new MainCategoriesListAdapter(getContext());
                categoriesListAdapter.setProductList(mainCategoriesResponse.getData().getCategories());
                recyclerView.setAdapter(categoriesListAdapter);
            }

        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_delegation;
    }

    @Override
    public void inOnCreateView(View root, ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        toolbarTitle.setText(getString(R.string.wathaq));
        delegationViewModel = ViewModelProviders.of(this).get(DelegationViewModel.class);
        delegationViewModel.initListener(this);
        delegationViewModel.getCategories(UserManager.getInstance().getUserToken()).observe(this, getCategories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        if (mainCategoriesResponse != null)
            recyclerView.setAdapter(categoriesListAdapter);

    }

    @Override
    public void onResponseFail(String msg) {
        recyclerView.setAdapter(new MainCategoriesListAdapter(getContext()));
        recyclerView.setEmptyView(emptyView);
        shimmerFrameLayout.stopShimmerAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
        showNotification(msg);
    }
}
