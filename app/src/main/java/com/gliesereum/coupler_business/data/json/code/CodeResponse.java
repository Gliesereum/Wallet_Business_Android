package com.gliesereum.coupler_business.data.json.code;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CodeResponse {

    @SerializedName("sent")
    private String sent;

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getSent() {
        return sent;
    }

}