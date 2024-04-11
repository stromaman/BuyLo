package com.ecom.buylo.Model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinalCategoryy {
    @SerializedName("ConcatenatedName")
    @Expose
    private String concatenatedName;
    @SerializedName("SSSid")
    @Expose
    private Integer sSSid;
    @SerializedName("SSSImage")
    @Expose
    private String sSSImage;
    @SerializedName("SSSname")
    @Expose
    private String sSSname;

    public String getConcatenatedName() {
        return concatenatedName;
    }

    public void setConcatenatedName(String concatenatedName) {
        this.concatenatedName = concatenatedName;
    }

    public Integer getSSSid() {
        return sSSid;
    }

    public void setSSSid(Integer sSSid) {
        this.sSSid = sSSid;
    }

    public String getSSSImage() {
        return sSSImage;
    }

    public void setSSSImage(String sSSImage) {
        this.sSSImage = sSSImage;
    }

    public String getSSSname() {
        return sSSname;
    }

    public void setSSSname(String sSSname) {
        this.sSSname = sSSname;
    }

}
