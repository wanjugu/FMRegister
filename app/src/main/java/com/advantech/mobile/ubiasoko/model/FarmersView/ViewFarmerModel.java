package com.advantech.mobile.ubiasoko.model.FarmersView;

/**
 * Created by ADVANTECH CONSULTING on 5/23/2018.
 */

public class ViewFarmerModel {
    //nationalId was replaced with an autoincrementing ID
    String nationalId,firstname,surname,othernames,dob,phone,gender;
    int regionId,districtId,villageId,wardId;
    String regionname,districtname,wardname,villagename;
    String myAssoc_id,businessType_name,association_name,positionName,products;
    int businessType_id,association_id,position_id,syncStatus;

    public ViewFarmerModel() {
    }

    @Override
    public String toString() {

        String name = firstname + " " + surname;

        return name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

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

    public String getOthernames() {
        return othernames;
    }

    public void setOthernames(String othernames) {
        this.othernames = othernames;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getWardname() {
        return wardname;
    }

    public void setWardname(String wardname) {
        this.wardname = wardname;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

//    public String getMyAssoc_id() {
//        return myAssoc_id;
//    }
//
//    public void setMyAssoc_id(String myAssoc_id) {
//        this.myAssoc_id = myAssoc_id;
//    }

//    public String getBusinessType_name() {
//        return businessType_name;
//    }
//
//    public void setBusinessType_name(String businessType_name) {
//        this.businessType_name = businessType_name;
//    }

    public String getAssociation_name() {
        return association_name;
    }

    public void setAssociation_name(String association_name) {
        this.association_name = association_name;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getBusinessType_id() {
        return businessType_id;
    }

    public void setBusinessType_id(int businessType_id) {
        this.businessType_id = businessType_id;
    }

    public int getAssociation_id() {
        return association_id;
    }

    public void setAssociation_id(int association_id) {
        this.association_id = association_id;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
