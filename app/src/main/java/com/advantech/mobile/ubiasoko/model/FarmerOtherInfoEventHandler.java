package com.advantech.mobile.ubiasoko.model;

/**
 * Created by ADVANTECH CONSULTING on 4/25/2018.
 */

public class FarmerOtherInfoEventHandler {
    public String association_name,my_commodities;
    public String assoc_id;

    public FarmerOtherInfoEventHandler(String association_name,String my_commodities, String assoc_id) {

        this.association_name = association_name;

        this.my_commodities = my_commodities;
        this.assoc_id = assoc_id;


    }
}
