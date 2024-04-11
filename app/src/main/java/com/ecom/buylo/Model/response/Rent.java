package com.ecom.buylo.Model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rent {
    @SerializedName("Cid")
    @Expose
    private Integer cid;
    @SerializedName("Cname")
    @Expose
    private String cname;
    @SerializedName("Cimage")
    @Expose
    private String cimage;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage;
    }
}
