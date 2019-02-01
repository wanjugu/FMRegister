package com.advantech.mobile.fmregister.controller;

/**
 * Created by ADVANTECH CONSULTING on 4/26/2018.
 */

public class WardSpinnerController {
    private String database_id;
    private String databasevalue;

    public String getDatabase_id() {
        return database_id;
    }

    public String getDatabasevalue() {
        return databasevalue;
    }

    public WardSpinnerController(String database_id, String databasevalue) {

        this.database_id = database_id;
        this.databasevalue = databasevalue;
    }

    @Override
    public String toString() {
        return databasevalue;
    }

}