package com.watheq.laywer.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.watheq.laywer.model.CompleteFilesBody;
import com.watheq.laywer.model.LoginModelResponse;
import com.watheq.laywer.network.ApiFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mahmoud.diab on 12/13/2017.
 */

public class CompleteFilesRepoImpl implements CompleteFilesRepo {
    @Override
    public LiveData<LoginModelResponse> completeProfile(String auth, CompleteFilesBody completeFilesBody) {
        final MutableLiveData<LoginModelResponse> completeProfileData = new MutableLiveData<>();
        ApiFactory.createInstance().completeFiles(auth, completeFilesBody).enqueue(new Callback<LoginModelResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginModelResponse> call, @NonNull Response<LoginModelResponse> response) {
                if (response.body() != null)
                    completeProfileData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<LoginModelResponse> call, @NonNull Throwable t) {
                completeProfileData.setValue(new LoginModelResponse(t));
            }
        });
        return completeProfileData;
    }
}
