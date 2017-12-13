package com.watheq.laywer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mahmoud.diab on 12/13/2017.
 */

public class CompleteFilesBody {
    @SerializedName("lawyerType")
    @Expose
    private String lawyerType;
    @SerializedName("IDCardFile")
    @Expose
    private String IDCardFile;
    @SerializedName("licenseFile")
    @Expose
    private String licenseFile;

    public String getLawyerType() {
        return lawyerType;
    }

    public void setLawyerType(String lawyerType) {
        this.lawyerType = lawyerType;
    }

    public String getIDCardFile() {
        return IDCardFile;
    }

    public void setIDCardFile(String IDCardFile) {
        this.IDCardFile = IDCardFile;
    }

    public String getLicenseFile() {
        return licenseFile;
    }

    public void setLicenseFile(String licenseFile) {
        this.licenseFile = licenseFile;
    }
}
