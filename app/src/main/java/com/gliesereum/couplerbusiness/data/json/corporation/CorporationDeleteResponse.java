package com.gliesereum.couplerbusiness.data.json.corporation;

import com.google.gson.annotations.SerializedName;

public class CorporationDeleteResponse {

    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
