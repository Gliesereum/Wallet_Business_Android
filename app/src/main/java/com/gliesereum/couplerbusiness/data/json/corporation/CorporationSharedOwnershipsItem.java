package com.gliesereum.couplerbusiness.data.json.corporation;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CorporationSharedOwnershipsItem {

    @SerializedName("corporationId")
    private String corporationId;

    @SerializedName("corporationOwnerId")
    private Object corporationOwnerId;

    @SerializedName("share")
    private int share;

    @SerializedName("id")
    private String id;

    @SerializedName("ownerId")
    private String ownerId;

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    public String getCorporationId() {
        return corporationId;
    }

    public void setCorporationOwnerId(Object corporationOwnerId) {
        this.corporationOwnerId = corporationOwnerId;
    }

    public Object getCorporationOwnerId() {
        return corporationOwnerId;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getShare() {
        return share;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }
}