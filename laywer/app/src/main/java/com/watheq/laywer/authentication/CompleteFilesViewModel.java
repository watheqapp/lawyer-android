package com.watheq.laywer.authentication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.watheq.laywer.model.CompleteFilesBody;
import com.watheq.laywer.model.LoginModelResponse;
import com.watheq.laywer.repository.CompleteFilesRepo;
import com.watheq.laywer.repository.CompleteFilesRepoImpl;

/**
 * Created by mahmoud.diab on 12/13/2017.
 */

public class CompleteFilesViewModel extends ViewModel {
    private MediatorLiveData<LoginModelResponse> loginLiveModel;
    private CompleteFilesRepo completeFilesRepo;

    public CompleteFilesViewModel() {
        loginLiveModel = new MediatorLiveData<>();
        completeFilesRepo = new CompleteFilesRepoImpl();
    }

    LiveData<LoginModelResponse> completeFiles(String auth, CompleteFilesBody completeFilesBody) {
        loginLiveModel.addSource(completeFilesRepo.completeProfile(auth, completeFilesBody), new Observer<LoginModelResponse>() {
            @Override
            public void onChanged(@Nullable LoginModelResponse loginModel) {
                loginLiveModel.setValue(loginModel);
            }
        });
        return loginLiveModel;
    }
}
