package com.watheq.laywer.repository;

import android.arch.lifecycle.LiveData;

import com.watheq.laywer.model.CompleteProfileBody;
import com.watheq.laywer.model.LoginModelResponse;


/**
 * Created by mahmoud.diab on 12/1/2017.
 */

public interface CompleteProfileRepo {
    LiveData<LoginModelResponse> completeProfile(String auth, CompleteProfileBody completeProfileBody);
}
