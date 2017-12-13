package com.watheq.laywer.repository;

import android.arch.lifecycle.LiveData;

import com.watheq.laywer.base.BaseHandlingErrors;
import com.watheq.laywer.model.MainCategoriesResponse;


/**
 * Created by mahmoud.diab on 12/2/2017.
 */

public interface DelegationRepo {
    LiveData<MainCategoriesResponse> getCategoriesResponse(String auth, BaseHandlingErrors errors);
}
