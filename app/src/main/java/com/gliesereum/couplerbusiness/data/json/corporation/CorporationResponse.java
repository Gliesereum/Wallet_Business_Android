package com.gliesereum.couplerbusiness.data.json.corporation;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CorporationResponse {

    @SerializedName("businessActivity")
    private String businessActivity;

    @SerializedName("country")
    private String country;

    @SerializedName("placeIncorporation")
    private String placeIncorporation;

    @SerializedName("objectState")
    private String objectState;

    @SerializedName("city")
    private String city;

    @SerializedName("companyType")
    private String companyType;

    @SerializedName("corporationEmployees")
    private List<String> corporationEmployees;

    @SerializedName("officeNumber")
    private String officeNumber;

    @SerializedName("corporationSharedOwnerships")
    private List<CorporationSharedOwnershipsItem> corporationSharedOwnerships;

    @SerializedName("description")
    private String description;

    @SerializedName("index")
    private String index;

    @SerializedName("logoUrl")
    private String logoUrl;

    @SerializedName("kycApproved")
    private boolean kycApproved;

    @SerializedName("coverUrl")
    private String coverUrl;

    @SerializedName("phone")
    private String phone;

    @SerializedName("street")
    private String street;

    @SerializedName("rcNumber")
    private String rcNumber;

    @SerializedName("name")
    private String name;

    @SerializedName("buildingNumber")
    private String buildingNumber;

    @SerializedName("id")
    private String id;

    @SerializedName("dateIncorporation")
    private String dateIncorporation;

    @SerializedName("region")
    private String region;

    public String getBusinessActivity() {
        return businessActivity;
    }

    public void setBusinessActivity(String businessActivity) {
        this.businessActivity = businessActivity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlaceIncorporation() {
        return placeIncorporation;
    }

    public void setPlaceIncorporation(String placeIncorporation) {
        this.placeIncorporation = placeIncorporation;
    }

    public String getObjectState() {
        return objectState;
    }

    public void setObjectState(String objectState) {
        this.objectState = objectState;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public List<String> getCorporationEmployees() {
        return corporationEmployees;
    }

    public void setCorporationEmployees(List<String> corporationEmployees) {
        this.corporationEmployees = corporationEmployees;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public List<CorporationSharedOwnershipsItem> getCorporationSharedOwnerships() {
        return corporationSharedOwnerships;
    }

    public void setCorporationSharedOwnerships(List<CorporationSharedOwnershipsItem> corporationSharedOwnerships) {
        this.corporationSharedOwnerships = corporationSharedOwnerships;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public boolean isKycApproved() {
        return kycApproved;
    }

    public void setKycApproved(boolean kycApproved) {
        this.kycApproved = kycApproved;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRcNumber() {
        return rcNumber;
    }

    public void setRcNumber(String rcNumber) {
        this.rcNumber = rcNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateIncorporation() {
        return dateIncorporation;
    }

    public void setDateIncorporation(String dateIncorporation) {
        this.dateIncorporation = dateIncorporation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorporationResponse that = (CorporationResponse) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public CorporationResponse(String id) {
        this.id = id;
    }
}