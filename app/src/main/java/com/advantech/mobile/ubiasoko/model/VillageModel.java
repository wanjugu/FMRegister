package com.advantech.mobile.ubiasoko.model;

/**
 * Created by ADVANTECH CONSULTING on 7/11/2018.
 */

public class VillageModel {
    private String villagename;
    private String wardId;

    public VillageModel() {
    }

    public VillageModel(String villagename, String wardId) {
        this.villagename = villagename;
        this.wardId = wardId;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }
}
