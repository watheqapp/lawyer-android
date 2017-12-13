package com.watheq.laywer;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.watheq.laywer.authentication.AuthenticationActivity;
import com.watheq.laywer.authentication.CompleteProfileActivity;
import com.watheq.laywer.authentication.ProofOfProfession;
import com.watheq.laywer.utils.UserManager;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserManager.getInstance().getUser() == null)
                    AuthenticationActivity.start(SplashScreen.this);
                else if (UserManager.getInstance().getUserData().getIsCompleteProfile() == 1 &&
                        UserManager.getInstance().getUserData().getIsCompleteFiles() == 1)
                    MainActivity.start(SplashScreen.this);
                else if (UserManager.getInstance().getUserData().getIsCompleteProfile() == 0)
                    CompleteProfileActivity.start(SplashScreen.this);
                else if (UserManager.getInstance().getUserData().getIsCompleteFiles() == 0)
                    ProofOfProfession.start(SplashScreen.this);
                finish();
            }
        }, 3000);
    }
}
