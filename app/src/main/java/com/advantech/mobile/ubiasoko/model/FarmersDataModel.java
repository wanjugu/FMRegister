package com.advantech.mobile.ubiasoko.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ADVANTECH CONSULTING on 4/30/2018.
 */

public class FarmersDataModel {

    public FarmersDataModel() {
    }

    /**
     *
     * @param region
     * @param association
     * @param businesstype
     * @param surname
     * @param firstname
     * @param nationalid
     * @param product
     * @param myAssocId
     * @param ward
     * @param village
     * @param dob
     * @param gender
     * @param district
     * @param othernames
     * @param phoneno
     */
    public FarmersDataModel(int nationalid, String firstname, String surname, String othernames, String dob, String phoneno,
                            String gender, int region, Object district, Object ward, Object village, String myAssocId,
                            String association, int businesstype, String product, boolean isDeleted) {
        super();
        this.nationalid = nationalid;
        this.firstname = firstname;
        this.surname = surname;
        this.othernames = othernames;
        this.dob = dob;
        this.phoneno = phoneno;
        this.gender = gender;
        this.region = region;
        this.district = district;
        this.ward = ward;
        this.village = village;
        this.myAssocId = myAssocId;
        this.association = association;
        this.businesstype = businesstype;
        this.product = product;
        this.isDeleted = isDeleted;
    }
    @SerializedName("nationalid")
    @Expose
    private int nationalid;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("othernames")
    @Expose
    private String othernames;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("phoneno")
    @Expose
    private String phoneno;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("region")
    @Expose
    private int region;
    @SerializedName("district")
    @Expose
    private Object district;
    @SerializedName("ward")
    @Expose
    private Object ward;
    @SerializedName("village")
    @Expose
    private Object village;
    @SerializedName("my_assoc_id")
    @Expose
    private String myAssocId;
    @SerializedName("association")
    @Expose
    private String association;
    @SerializedName("businesstype")
    @Expose
    private int businesstype;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;

    public int getNationalid() {
        return nationalid;
    }

    public void setNationalid(int nationalid) {
        this.nationalid = nationalid;
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

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public Object getDistrict() {
        return district;
    }

    public void setDistrict(Object district) {
        this.district = district;
    }

    public Object getWard() {
        return ward;
    }

    public void setWard(Object ward) {
        this.ward = ward;
    }

    public Object getVillage() {
        return village;
    }

    public void setVillage(Object village) {
        this.village = village;
    }

    public String getMyAssocId() {
        return myAssocId;
    }

    public void setMyAssocId(String myAssocId) {
        this.myAssocId = myAssocId;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public int getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(int businesstype) {
        this.businesstype = businesstype;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}

