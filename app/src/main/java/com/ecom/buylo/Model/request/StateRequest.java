package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.State;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateRequest {
    @SerializedName("data")
    @Expose
    private List<State> data;

    public List<State> getData() {
        return data;
    }

    public void setData(List<State> data) {
        this.data = data;
    }
}
