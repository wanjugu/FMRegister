package com.advantech.mobile.fmregister.model.AssocView;

/**
 * Created by ADVANTECH CONSULTING on 5/25/2018.
 */

public class ViewAssociationsModel{
    int assoc_ID;
    String assoc_name,assoc_regno,assoc_mobile;

    String commodities;
    String region,district,ward,createdAt;
    int regionID,districtID,wardID,userID,syncStatuc,isDeleted;

    public ViewAssociationsModel() {
    }

    public int getAssoc_ID() {
        return assoc_ID;
    }

    public void setAssoc_ID(int assoc_ID) {
        this.assoc_ID = assoc_ID;
    }

    public String getAssoc_name() {
        return assoc_name;
    }

    public void setAssoc_name(String assoc_name) {
        this.assoc_name = assoc_name;
    }

    public String getAssoc_regno() {
        return assoc_regno;
    }

    public void setAssoc_regno(String assoc_regno) {
        this.assoc_regno = assoc_regno;
    }

    public String getAssoc_mobile() {
        return assoc_mobile;
    }

    public void setAssoc_mobile(String assoc_mobile) {
        this.assoc_mobile = assoc_mobile;
    }




    public String getCommodities() {
        return commodities;
    }

    public void setCommodities(String commodities) {
        this.commodities = commodities;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public int getWardID() {
        return wardID;
    }

    public void setWardID(int wardID) {
        this.wardID = wardID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSyncStatuc() {
        return syncStatuc;
    }

    public void setSyncStatuc(int syncStatuc) {
        this.syncStatuc = syncStatuc;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
