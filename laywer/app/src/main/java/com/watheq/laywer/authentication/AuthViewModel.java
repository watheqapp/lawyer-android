package com.watheq.laywer.authentication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import com.watheq.laywer.model.LoginBody;
import com.watheq.laywer.model.LoginModelResponse;
import com.watheq.laywer.repository.LoginRepo;
import com.watheq.laywer.repository.LoginRepoImpl;

/**
 * Created by mahmoud.diab on 11/22/2017.
 */

public class AuthViewModel extends ViewModel {

    private MutableLiveData<Long> intervalTime = new MutableLiveData<>();
    private MediatorLiveData<LoginModelResponse> loginLiveModel;
    private LoginRepo loginRepo;

    public AuthViewModel() {
        loginRepo = new LoginRepoImpl();
        loginLiveModel = new MediatorLiveData<>();
    }

    void setIntervalTime(long timeToStart) {
        CountDownTimer cdt = new CountDownTimer(timeToStart, 1000) {// def 2 mins
            @Override
            public void onTick(long millisUntilFinished) {
                intervalTime.setValue(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                intervalTime.setValue(0L);
            }
        };

        cdt.start();
    }

    LiveData<Long> getIntervalTime() {
        return intervalTime;
    }

    LiveData<LoginModelResponse> getApiResponse() {
        return loginLiveModel;
    }

    LiveData<LoginModelResponse> loginUser(LoginBody loginBody) {
        loginLiveModel.addSource(loginRepo.loginUser(loginBody), new Observer<LoginModelResponse>() {
            @Override
            public void onChanged(@Nullable LoginModelResponse loginModel) {
                loginLiveModel.setValue(loginModel);
            }
        });
        return loginLiveModel;
    }
}
