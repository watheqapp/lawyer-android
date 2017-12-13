package com.watheq.laywer.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.AndroidException;

import com.watheq.laywer.base.BaseHandlingErrors;
import com.watheq.laywer.model.MainCategoriesResponse;
import com.watheq.laywer.network.ApiFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mahmoud.diab on 12/2/2017.
 */

public class DelegationRepoImpl implements DelegationRepo {
    @Override
    public LiveData<MainCategoriesResponse> getCategoriesResponse(String auth, final BaseHandlingErrors baseHandlingErrors) {
        final MutableLiveData<MainCategoriesResponse> mutableLiveData = new MutableLiveData<>();
        ApiFactory.createInstance().getCategories(auth).enqueue(new Callback<MainCategoriesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MainCategoriesResponse> call, @NonNull Response<MainCategoriesResponse> response) {
                if (response.body() != null && response.body().getCode() == 200)
                    mutableLiveData.setValue(response.body());
                else {
                    if (baseHandlingErrors != null) {
                        if (response.body() != null)
                            baseHandlingErrors.onResponseFail(response.body().getMessage());
                        else
                            baseHandlingErrors.onResponseFail(response.message());
                    } else
                        throw new RuntimeException("you have to intiListener before calling the api");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MainCategoriesResponse> call, @NonNull Throwable t) {
                if (baseHandlingErrors != null)
                    baseHandlingErrors.onResponseFail(t.getMessage());
                else
                    throw new RuntimeException("you have to intiListener before calling the api");
            }
        });
        return mutableLiveData;
    }
}
