package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 4/27/2018.
 */

public class FarmersRegistrationModel {
    //private int nationalid;
    private String firstname,surname,othernames,phoneno,gender;
    private int region,district,ward,village;
    private int assoc_name;
    private String commodity;
    private String mTimestamp;
    private int user_id,isdeleted;








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

    public int getAssoc_name() {
        return assoc_name;
    }

    public void setAssoc_name(int assoc_name) {
        this.assoc_name = assoc_name;
    }






    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(String timestamp) {
        this.mTimestamp = timestamp;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUpdatestatus() {
        return updatestatus;
    }

    public void setUpdatestatus(int updatestatus) {
        this.updatestatus = updatestatus;
    }

    public int getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        this.isdeleted = isdeleted;
    }

    public FarmersRegistrationModel(String firstname, String surname, String othernames, String phoneno, String gender, int region, int district, int ward,
                                    int village, int assoc_name,String commodity, String timestamp,
                                    int user_id, int updatestatus, int isdeleted) {
      //  this.nationalid = nationalid;
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
        this.assoc_name = assoc_name;
       // this.position = position;
       // this.businesstype = businesstype;
       // this.myassoc_id = myassoc_id;
       // this.myassoc_id = myassoc_id;
        this.commodity = commodity;
        this.mTimestamp = timestamp;
        this.user_id = user_id;
        this.updatestatus = updatestatus;
        this.isdeleted = isdeleted;
       // this.bsn_type_id = businesstypeID;

    }

    private int updatestatus;

    public FarmersRegistrationModel() {
    }


}
