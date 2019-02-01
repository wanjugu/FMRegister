package com.advantech.mobile.ubiasoko.model.TradersView;

/**
 * Created by ADVANTECH CONSULTING on 5/24/2018.
 */

public class ViewTradersModel {
    String traderId;

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    String firstname;
    String surname;
    String othername;
    String mobile;
    String gender;
    String region,district,ward,village;
    int regionID,districtID,wardID,villageID;
    String businessname;
    int createdAt,syncstatus,isDeleted,userId;


    public ViewTradersModel() {
    }

    public ViewTradersModel(String firstname, String surname,
                            String othername,String mobile, String gender,
                            String region, String district, String ward, String village,
                            int regionID, int districtID, int wardID,
                            int villageID,  String businessname,
                            int createdAt,int syncstatus, int isDeleted, int userId) {
        //this.nationalID = nationalID;
        this.firstname = firstname;
        this.surname = surname;
        this.othername = othername;
        //this.dob = dob;
        this.mobile = mobile;
        this.gender = gender;
        this.region = region;
        this.district = district;
        this.ward = ward;
        this.village = village;
        this.regionID = regionID;
        this.districtID = districtID;
        this.wardID = wardID;
        this.villageID = villageID;
       // this.additionalInfo = additionalInfo;
        this.businessname = businessname;
//        this.phoneno2 = phoneno2;
//        this.businessTypename = businessTypename;
//        this.businessType_ID = businessType_ID;
//        this.commodity = commodity;
        this.createdAt = createdAt;
        this.syncstatus = syncstatus;
        this.isDeleted = isDeleted;
        this.userId = userId;
    }

    @Override
    public String toString() {
        String name =  firstname + " " +surname;
        return name;
    }

//    public String getNationalID() {
//        return nationalID;
//    }
//
//    public void setNationalID(String nationalID) {
//        this.nationalID = nationalID;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

//    public String getDob() {
//        return dob;
//    }
//
//    public void setDob(String dob) {
//        this.dob = dob;
//    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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

    public int getVillageID() {
        return villageID;
    }

    public void setVillageID(int villageID) {
        this.villageID = villageID;
    }

//    public String getAdditionalInfo() {
//        return additionalInfo;
//    }
//
//    public void setAdditionalInfo(String additionalInfo) {
//        this.additionalInfo = additionalInfo;
//    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

//    public String getPhoneno2() {
//        return phoneno2;
//    }
//
//    public void setPhoneno2(String phoneno2) {
//        this.phoneno2 = phoneno2;
//    }
//
//    public String getBusinessTypename() {
//        return businessTypename;
//    }

//    public void setBusinessTypename(String businessTypename) {
//        this.businessTypename = businessTypename;
//    }
//
//    public int getBusinessType_ID() {
//        return businessType_ID;
//    }
//
//    public void setBusinessType_ID(int businessType_ID) {
//        this.businessType_ID = businessType_ID;
//    }
//
//    public String getCommodity() {
//        return commodity;
//    }
//
//    public void setCommodity(String commodity) {
//        this.commodity = commodity;
//    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public int getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(int syncstatus) {
        this.syncstatus = syncstatus;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
