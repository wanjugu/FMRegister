package com.advantech.mobile.ubiasoko.model;

/**
 * Created by ADVANTECH CONSULTING on 5/22/2018.
 */

public class AssociationDemographicEventHandler {
    public String assoc_name,assoc_mobileno;
    public String assoc_firstname,assoc_surname,assoc_othername;


    public AssociationDemographicEventHandler(String assoc_name,String assoc_mobileno,
                                              String assoc_firstname,String assoc_surname,String assoc_othername) {
        this.assoc_name = assoc_name;
        this.assoc_mobileno = assoc_mobileno;
        this.assoc_firstname =assoc_firstname;
        this.assoc_surname =assoc_surname;
        this.assoc_othername = assoc_othername;

    }
}
