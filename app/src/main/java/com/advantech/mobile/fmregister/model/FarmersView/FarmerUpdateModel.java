package com.advantech.mobile.fmregister.model.FarmersView;

/**
 * Created by ADVANTECH CONSULTING on 5/29/2018.
 */

public class FarmerUpdateModel {
    String eNationalId,eAssociationId;
    String eFirstname,eSurname, eOthernames;
    String eDob,ePhoneno,eGender;
    String eRegion,eDistrict,eWard,eVillage;
    String eBusinesstype,ePosition,eAssociation;


    public FarmerUpdateModel() {
    }

    public String geteNationalId() {
        return eNationalId;
    }

    public void seteNationalId(String eNationalId) {
        this.eNationalId = eNationalId;
    }

    public String geteAssociationId() {
        return eAssociationId;
    }

    public void seteAssociationId(String eAssociationId) {
        this.eAssociationId = eAssociationId;
    }

    public String geteFirstname() {
        return eFirstname;
    }

    public void seteFirstname(String eFirstname) {
        this.eFirstname = eFirstname;
    }

    public String geteSurname() {
        return eSurname;
    }

    public void seteSurname(String eSurname) {
        this.eSurname = eSurname;
    }

    public String geteOthernames() {
        return eOthernames;
    }

    public void seteOthernames(String eOthernames) {
        this.eOthernames = eOthernames;
    }

    public String geteDob() {
        return eDob;
    }

    public void seteDob(String eDob) {
        this.eDob = eDob;
    }

    public String getePhoneno() {
        return ePhoneno;
    }

    public void setePhoneno(String ePhoneno) {
        this.ePhoneno = ePhoneno;
    }

    public String geteGender() {
        return eGender;
    }

    public void seteGender(String eGender) {
        this.eGender = eGender;
    }

    public String geteRegion() {
        return eRegion;
    }

    public void seteRegion(String eRegion) {
        this.eRegion = eRegion;
    }

    public String geteDistrict() {
        return eDistrict;
    }

    public void seteDistrict(String eDistrict) {
        this.eDistrict = eDistrict;
    }

    public String geteWard() {
        return eWard;
    }

    public void seteWard(String eWard) {
        this.eWard = eWard;
    }

    public String geteVillage() {
        return eVillage;
    }

    public void seteVillage(String eVillage) {
        this.eVillage = eVillage;
    }

//    public String geteBusinesstype() {
//        return eBusinesstype;
//    }
//
//    public void seteBusinesstype(String eBusinesstype) {
//        this.eBusinesstype = eBusinesstype;
//    }

//    public String getePosition() {
//        return ePosition;
//    }
//
//    public void setePosition(String ePosition) {
//        this.ePosition = ePosition;
//    }

    public String geteAssociation() {
        return eAssociation;
    }

    public void seteAssociation(String eAssociation) {
        this.eAssociation = eAssociation;
    }
}
