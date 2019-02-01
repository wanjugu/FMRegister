package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 5/23/2018.
 */

public class AssociationRegistrationModel {
    private String assoc_name,assoc_regno,assoc_mobile,assoc_telephone;
    int business_type;
    String assoc_commodities;
    int region,district,ward;
    String street,postaladdress,zipcode;

    private String mTimestamp;
    private int user_id,syncStatus,isdeleted;
    private String assoc_firstname,assoc_surname,assoc_other_name;

    public AssociationRegistrationModel(String assoc_name, String assoc_regno, String assoc_mobile,
                                        String assoc_telephone, int business_type, String assoc_commodities,
                                        int region,int district, int ward, String street, String postaladdress,
                                        String zipcode, String mTimestamp, int user_id, int syncStatus,
                                        int isdeleted) {
        this.assoc_name = assoc_name;
        this.assoc_regno = assoc_regno;
        this.assoc_mobile = assoc_mobile;
        this.assoc_telephone = assoc_telephone;
        this.business_type = business_type;
        this.assoc_commodities = assoc_commodities;
        this.region = region;
        this.district = district;
        this.ward = ward;
        this.street = street;
        this.postaladdress = postaladdress;
        this.zipcode = zipcode;
        this.mTimestamp = mTimestamp;
        this.user_id = user_id;
        this.syncStatus = syncStatus;
        this.isdeleted = isdeleted;
    }

    public AssociationRegistrationModel() {
    }

    public String getAssoc_firstname() {
        return assoc_firstname;
    }

    public void setAssoc_firstname(String assoc_firstname) {
        this.assoc_firstname = assoc_firstname;
    }

    public String getAssoc_surname() {
        return assoc_surname;
    }

    public void setAssoc_surname(String assoc_surname) {
        this.assoc_surname = assoc_surname;
    }

    public String getAssoc_other_name() {
        return assoc_other_name;
    }

    public void setAssoc_other_name(String assoc_other_name) {
        this.assoc_other_name = assoc_other_name;
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

    public String getAssoc_telephone() {
        return assoc_telephone;
    }

    public void setAssoc_telephone(String assoc_telephone) {
        this.assoc_telephone = assoc_telephone;
    }

    public int getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(int business_type) {
        this.business_type = business_type;
    }

    public String getAssoc_commodities() {
        return assoc_commodities;
    }

    public void setAssoc_commodities(String assoc_commodities) {
        this.assoc_commodities = assoc_commodities;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostaladdress() {
        return postaladdress;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public int getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        this.isdeleted = isdeleted;
    }
}
