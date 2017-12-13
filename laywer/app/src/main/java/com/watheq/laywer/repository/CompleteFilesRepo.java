package com.watheq.laywer.repository;

import android.arch.lifecycle.LiveData;

import com.watheq.laywer.model.CompleteFilesBody;
import com.watheq.laywer.model.LoginModelResponse;

/**
 * Created by mahmoud.diab on 12/13/2017.
 */

public interface CompleteFilesRepo {
    LiveData<LoginModelResponse> completeProfile(String auth, CompleteFilesBody completeFilesBody);
}
