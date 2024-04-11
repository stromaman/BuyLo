package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.FinalCategoryy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FinalCategory {

    @SerializedName("data")
    @Expose
    private List<FinalCategoryy> data;
    @SerializedName("Type")
    @Expose
    private String type;

    public List<FinalCategoryy> getData() {
        return data;
    }

    public void setData(List<FinalCategoryy> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
