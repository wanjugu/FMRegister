package com.advantech.mobile.ubiasoko.model.AssocView;

/**
 * Created by ADVANTECH CONSULTING on 7/18/2018.
 */

public class GetAssociationIdModel {
    private int associationId,farmerId;

    public GetAssociationIdModel() {
    }

    public GetAssociationIdModel(int associationId, int farmerId) {
        this.associationId = associationId;
        this.farmerId = farmerId;
    }

    public int getAssociationId() {
        return associationId;
    }

    public void setAssociationId(int associationId) {
        this.associationId = associationId;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }
}
