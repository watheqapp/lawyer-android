package com.watheq.laywer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sub {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cost")
    @Expose
    private int cost;
    @SerializedName("hasSubs")
    @Expose
    private int hasSubs;
    @SerializedName("subs")
    @Expose
    private List<Sub> subs = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHasSubs() {
        return hasSubs;
    }

    public void setHasSubs(int hasSubs) {
        this.hasSubs = hasSubs;
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }

}