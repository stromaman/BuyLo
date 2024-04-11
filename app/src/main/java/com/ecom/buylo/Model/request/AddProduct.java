package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.ProductVendor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddProduct {
    @SerializedName("data")
    @Expose
    private List<ProductVendor> data;

    public List<ProductVendor> getData() {
        return data;
    }

    public void setData(List<ProductVendor> data) {
        this.data = data;
    }
}
