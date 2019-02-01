package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 7/11/2018.
 */

public class DistrictModel {

    private String districtname;
    private String regionId;

    public DistrictModel() {
    }

    public DistrictModel(String districtname, String regionId) {
        this.districtname = districtname;
        this.regionId = regionId;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
