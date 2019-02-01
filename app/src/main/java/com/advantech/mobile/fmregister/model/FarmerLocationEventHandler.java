package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 4/25/2018.
 */

public class FarmerLocationEventHandler {
    public String region,district,ward,village;
    public String region_id,district_id,ward_id,village_id;


    public FarmerLocationEventHandler(String region, String district, String ward,
                                      String village, String region_id, String district_id) {
        this.region = region;
        this.district = district;
        this.ward = ward;
        this.village = village;
        this.region_id = region_id;
        this.district_id = district_id;
        this.ward_id = ward_id;
        this.village_id = village_id;
    }
}
