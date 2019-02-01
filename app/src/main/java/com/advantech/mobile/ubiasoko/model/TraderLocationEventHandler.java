package com.advantech.mobile.ubiasoko.model;

/**
 * Created by ADVANTECH CONSULTING on 5/21/2018.
 */

public class TraderLocationEventHandler {
    public String region,district;//,ward,village;
    public String region_id,district_id;
    //,ward_id,village_id;



    public TraderLocationEventHandler(String region, String district,
                                      String region_id, String district_id
                                      ) {
        this.region = region;
        this.district = district;
//        this.ward = ward;
//        this.village = village;
        this.region_id = region_id;
        this.district_id = district_id;
//        this.ward_id = ward_id;
//        this.village_id = village_id;

    }

    public TraderLocationEventHandler() {
    }
}
