package com.watheq.laywer.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.watheq.laywer.api.ClientServices;

public class LoginModelResponse extends BaseModel {

    public LoginModelResponse(Throwable throwable) {
        this.throwable = throwable;
    }

    private Throwable throwable;

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @SerializedName("data")
    private Response response;

    @NonNull
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    public class Response {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("created_at")
        @Expose
        private Integer createdAt;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("active")
        @Expose
        private int active;
        @SerializedName("lawyerType")
        @Expose
        private String lawyerType;
        @SerializedName("IDCardFile")
        @Expose
        private String IDCardFile;
        @SerializedName("licenseFile")
        @Expose
        private String licenseFile;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("isCompleteProfile")
        @Expose
        private int isCompleteProfile;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("isCompleteFiles")
        private int isCompleteFiles;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getIsCompleteFiles() {
            return isCompleteFiles;
        }

        public void setIsCompleteFiles(int isCompleteFiles) {
            this.isCompleteFiles = isCompleteFiles;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImage() {
            return ClientServices.baseUrl + image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Integer createdAt) {
            this.createdAt = createdAt;
        }

        public int getIsCompleteProfile() {
            return isCompleteProfile;
        }

        public void setIsCompleteProfile(Integer isCompleteProfile) {
            this.isCompleteProfile = isCompleteProfile;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}