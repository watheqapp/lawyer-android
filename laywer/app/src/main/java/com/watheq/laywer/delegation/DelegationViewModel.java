package com.watheq.laywer.delegation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.watheq.laywer.base.BaseHandlingErrors;
import com.watheq.laywer.model.MainCategoriesResponse;
import com.watheq.laywer.repository.DelegationRepo;
import com.watheq.laywer.repository.DelegationRepoImpl;

/**
 * Created by mahmoud.diab on 12/2/2017.
 */

public class DelegationViewModel extends ViewModel {
    private MediatorLiveData<MainCategoriesResponse> deMediatorLiveData;
    private DelegationRepo delegationRepo;
    private BaseHandlingErrors errorHandling;

    public DelegationViewModel() {
        delegationRepo = new DelegationRepoImpl();
        deMediatorLiveData = new MediatorLiveData<>();
    }

    void initListener(BaseHandlingErrors error) {
        errorHandling = error;
    }

    LiveData<MainCategoriesResponse> getCategories(String auth) {
        if (deMediatorLiveData.getValue() != null)
            deMediatorLiveData.postValue(deMediatorLiveData.getValue());
        else
            deMediatorLiveData.addSource(delegationRepo.getCategoriesResponse(auth, errorHandling), new Observer<MainCategoriesResponse>() {
                @Override
                public void onChanged(@Nullable MainCategoriesResponse mainCategoriesResponse) {
                    deMediatorLiveData.setValue(mainCategoriesResponse);
                }
            });
        return deMediatorLiveData;
    }
}
