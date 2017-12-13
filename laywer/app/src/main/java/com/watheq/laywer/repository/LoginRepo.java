package com.watheq.laywer.repository;

import android.arch.lifecycle.LiveData;

import com.watheq.laywer.model.LoginBody;
import com.watheq.laywer.model.LoginModelResponse;


/**
 * Created by mahmoud.diab on 11/29/2017.
 */

public interface LoginRepo {
    LiveData<LoginModelResponse> loginUser(LoginBody loginBody);
}
