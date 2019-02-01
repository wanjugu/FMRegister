package com.advantech.mobile.ubiasoko.model;

/**
 * Created by ADVANTECH CONSULTING on 5/22/2018.
 */

public class AssociationOtherEventHandler {
    public String region,district;//,ward;

    public String region_id,district_id;//,ward_id;


    public AssociationOtherEventHandler(String region, String district,
                                        String region_id, String district_id) {
        this.region = region;
        this.district = district;

        this.region_id = region_id;
        this.district_id = district_id;

    }
}
