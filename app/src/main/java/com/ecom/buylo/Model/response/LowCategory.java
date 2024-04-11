package com.ecom.buylo.Model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LowCategory {
    @SerializedName("SScid")
    @Expose
    private Integer sScid;
    @SerializedName("SScImage")
    @Expose
    private String sScImage;
    @SerializedName("SScName")
    @Expose
    private String sScName;

    public Integer getSScid() {
        return sScid;
    }

    public void setSScid(Integer sScid) {
        this.sScid = sScid;
    }

    public String getSScImage() {
        return sScImage;
    }

    public void setSScImage(String sScImage) {
        this.sScImage = sScImage;
    }

    public String getSScName() {
        return sScName;
    }

    public void setSScName(String sScName) {
        this.sScName = sScName;
    }
}
