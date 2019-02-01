package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 4/25/2018.
 */

public class FarmerDemographicsEventHandler {
    public String firstname,surname, assoc_id,association_name;
    public String othernames,phoneno,gender;

    public FarmerDemographicsEventHandler(String firstname, String surname,
                                          String othernames,String phoneno, String gender, String assoc_id,String association_name) {

        this.firstname = firstname;
        this.surname = surname;
        this.othernames = othernames;
        this.phoneno = phoneno;
        this.gender = gender;
        this.assoc_id = assoc_id;
        this.association_name = association_name;
    }

    public FarmerDemographicsEventHandler() {
    }
}
