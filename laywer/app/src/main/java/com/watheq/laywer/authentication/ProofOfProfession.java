package com.watheq.laywer.authentication;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.watheq.laywer.MainActivity;
import com.watheq.laywer.R;
import com.watheq.laywer.base.BaseActivity;
import com.watheq.laywer.model.CompleteFilesBody;
import com.watheq.laywer.model.LoginModelResponse;
import com.watheq.laywer.utils.FileUtils;
import com.watheq.laywer.utils.UserManager;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ProofOfProfession extends BaseActivity {
    public static final int FIRST_IMAGE = 10545;
    public static final int SECOND_IMAGE = 10546;
    @BindView(R.id.lawyer)
    TextView lawyer;
    @BindView(R.id.marriage_official)
    TextView marriageOfficial;
    @BindView(R.id.complete_files)
    CircularProgressButton confirmBtn;
    @BindView(R.id.first_image)
    TextView firstImageTv;
    @BindView(R.id.second_image)
    TextView secondImageTv;
    private String firstImage, secondImage;
    private CompleteFilesViewModel completeFilesViewModel;
    private String lawyerType = "authorized";

    private final Observer<LoginModelResponse> getCompleteFilesResponse = new Observer<LoginModelResponse>() {
        @Override
        public void onChanged(@Nullable LoginModelResponse completeProfileResponse) {
            if (completeProfileResponse.getThrowable() == null
                    && completeProfileResponse.getCode() == 200) {
                UserManager.getInstance().addUser(completeProfileResponse);
                MainActivity.start(ProofOfProfession.this);
                confirmBtn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1500);
            } else if (completeProfileResponse.getThrowable() == null) {
                showSnackBarNotification(completeProfileResponse.getMessage());
            } else {
                showSnackBarNotification(getString(R.string.all_no_internet));
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        completeFilesViewModel = ViewModelProviders.of(this).get(CompleteFilesViewModel.class);
    }

    @Override
    public void clean() {

    }

    @Override
    public int myView() {
        return R.layout.activity_proof_of_profession;
    }

    @OnClick(R.id.lawyer)
    void onLawyerClicked() {
        lawyer.setBackgroundResource(R.drawable.rounded_red);
        marriageOfficial.setBackgroundResource(R.drawable.rounded_corner);
        lawyer.setTextColor(getColor(android.R.color.white));
        marriageOfficial.setTextColor(getColor(R.color.taupe_gray));
        lawyerType = "authorized";
    }


    @OnClick(R.id.marriage_official)
    void onMarriageClicked() {
        marriageOfficial.setBackgroundResource(R.drawable.rounded_red);
        lawyer.setBackgroundResource(R.drawable.rounded_corner);
        marriageOfficial.setTextColor(getColor(android.R.color.white));
        lawyer.setTextColor(getColor(R.color.taupe_gray));
        lawyerType = "clerk";
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ProofOfProfession.class);
        context.startActivity(starter);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    protected void openGallery(int requestCode) {
        Intent takeGalleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takeGalleryIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeGalleryIntent, requestCode);
        }
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showRationaleForLocation(PermissionRequest request) {
        showRationaleDialog(R.string.permission_external_storage_rationale, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == FIRST_IMAGE) {
            firstImage = FileUtils.onResult(data, this);
            firstImageTv.setText(getString(R.string.added));
        } else if (resultCode == Activity.RESULT_OK && requestCode == SECOND_IMAGE) {
            secondImage = FileUtils.onResult(data, this);
            secondImageTv.setText(getString(R.string.added));
        }
    }

    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    @OnClick(R.id.first_image)
    void onFirstImageClicked() {
        openGallery(FIRST_IMAGE);
    }

    @OnClick(R.id.second_image)
    void onSecondImageClicked() {
        openGallery(SECOND_IMAGE);
    }

    @OnClick(R.id.complete_files)
    void onCompleteFilesClicked() {
        if (firstImage == null || secondImage == null) {
            showSnackBar(getString(R.string.validation_error_required));
            return;
        }
        confirmBtn.startAnimation();
        CompleteFilesBody completeFilesBody = new CompleteFilesBody();
        completeFilesBody.setLawyerType(lawyerType);
        completeFilesBody.setIDCardFile(firstImage);
        completeFilesBody.setLicenseFile(secondImage);
        completeFilesViewModel.completeFiles(UserManager.getInstance().getUserToken(), completeFilesBody)
                .observe(this, getCompleteFilesResponse);

    }
}
