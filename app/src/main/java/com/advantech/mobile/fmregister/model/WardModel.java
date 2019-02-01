package com.advantech.mobile.fmregister.model;

/**
 * Created by ADVANTECH CONSULTING on 7/11/2018.
 */

public class WardModel {
    private String wardname;
    private String districtId;

    public WardModel() {
    }

    public WardModel(String wardname, String districtId) {
        this.wardname = wardname;
        this.districtId = districtId;

    }


    public String getWardname() {
        return wardname;
    }

    public void setWardname(String wardname) {
        this.wardname = wardname;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}
