package com.advantech.mobile.ubiasoko.model;

/**
 * Created by ADVANTECH CONSULTING on 5/21/2018.
 */

public class TraderOtherDetailsEventHandler {
    public String businessName,phoneNo;
    public String businessType;
    public String commodities;
    public String businessType_ID;

    public TraderOtherDetailsEventHandler(String businessName, String phoneNo,
                                          String businessType, String commodities,String businessType_ID) {
        this.businessName = businessName;
        this.phoneNo = phoneNo;
        this.businessType = businessType;
        this.commodities = commodities;
        this.businessType_ID = businessType_ID;
    }
}
