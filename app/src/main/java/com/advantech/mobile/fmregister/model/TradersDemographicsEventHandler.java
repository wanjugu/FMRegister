package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 5/21/2018.
 */

public class TradersDemographicsEventHandler {
    public String  firstname,surname,businessname;
    public String othernames,phoneno,gender;
    public String assoc_id,asssoc_name;

    public TradersDemographicsEventHandler( String firstname, String surname,
                                          String othernames, String phoneno, String gender,
                                            String businessname,String assoc_id,String assoc_name) {

        this.firstname = firstname;
        this.surname = surname;
        this.othernames = othernames;
        this.businessname = businessname;

        this.phoneno = phoneno;
        this.gender = gender;
        this.assoc_id = assoc_id;
        this.asssoc_name=assoc_name;
    }

    public TradersDemographicsEventHandler() {
    }
}
