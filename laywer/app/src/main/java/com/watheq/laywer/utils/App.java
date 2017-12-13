package com.watheq.laywer.utils;

import android.app.Application;

import com.watheq.laywer.R;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by mahmoud.diab on 11/14/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PrefsManager.init(this);
        Paper.init(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DinNextRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
