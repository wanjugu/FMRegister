package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 5/22/2018.
 */

public class TraderRegistrationModel {
   // private int nationalid;
    private String firstname,surname,othernames,phoneno,gender;
    private int region,district,ward,village;
   // String more_loc_info,businessname,telephoneNo;
  //  private int businessType;
  //  private String commodity;
    private String mTimestamp,businessname,associationname;
    private int user_id;
    private int syncStatus;
    private int isdeleted;

    public int getAssociation_id() {
        return association_id;
    }

    public void setAssociation_id(int association_id) {
        this.association_id = association_id;
    }

    private int association_id;

    public TraderRegistrationModel(String firstname, String surname, String othernames,
                                    String phoneno, String gender, int region, int district,
                                   int ward, int village,String businessname,
                                  String mTimestamp,String associationname,
                                   int user_id,int syncStatus, int isdeleted) {
       // this.nationalid = nationalid;
        this.firstname = firstname;
        this.surname = surname;
        this.othernames = othernames;
       // this.dob = dob;
        this.phoneno = phoneno;
        this.gender = gender;
        this.region = region;
        this.district = district;
        this.ward = ward;
        this.village = village;
//        this.more_loc_info = more_loc_info;
        this.businessname = businessname;
        this.associationname = associationname;
//        this.telephoneNo = telephoneNo;
//        this.businessType = businessType;
//        this.commodity = commodity;
        this.mTimestamp = mTimestamp;
        this.user_id = user_id;
        this.syncStatus = syncStatus;
        this.isdeleted = isdeleted;
    }

    public TraderRegistrationModel() {
    }

    public String getAssociationname() {
        return associationname;
    }

    public void setAssociationname(String associationname) {
        this.associationname = associationname;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

//    public int getNationalid() {
//        return nationalid;
//    }

//    public void setNationalid(int nationalid) {
//        this.nationalid = nationalid;
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

    public String getOthernames() {
        return othernames;
    }

    public void setOthernames(String othernames) {
        this.othernames = othernames;
    }

//    public String getDob() {
//        return dob;
//    }

//    public void setDob(String dob) {
//        this.dob = dob;
//    }

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

    public int getVillage() {
        return village;
    }

    public void setVillage(int village) {
        this.village = village;
    }

//    public String getMore_loc_info() {
//        return more_loc_info;
//    }
//
//    public void setMore_loc_info(String more_loc_info) {
//        this.more_loc_info = more_loc_info;
//    }
//
    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }
//
//    public String getTelephoneNo() {
//        return telephoneNo;
//    }
//
//    public void setTelephoneNo(String telephoneNo) {
//        this.telephoneNo = telephoneNo;
//    }
//
//    public int getBusinessType() {
//        return businessType;
//    }
//
//    public void setBusinessType(int businessType) {
//        this.businessType = businessType;
//    }
//
//    public String getCommodity() {
//        return commodity;
//    }
//
//    public void setCommodity(String commodity) {
//        this.commodity = commodity;
//    }

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

    public int getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        this.isdeleted = isdeleted;
    }
}
