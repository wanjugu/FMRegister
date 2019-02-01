package com.advantech.mobile.fmregister.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import com.advantech.mobile.fmregister.MainActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ADVANTECH CONSULTING on 7/25/2018.
 */

public class FarmerSyncService extends JobService {
    //context and database helper object
    private Context context;
    private DBHandler db = new DBHandler(this);

    @Override
    public boolean onStartJob(JobParameters job) {
        syncfarmers();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }


    private void syncfarmers(){

        //getting all the unsynced names
        Cursor cursor = db.getUnsyncedNames();
        if (cursor.moveToFirst()) {
            do {
                //calling the method to save the unsynced name to MySQL
                saveName(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_FIRSTNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_SURNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_OTHERNAMES)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_PHONE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_GENDER)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_REGION)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_DISTRICT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_WARD)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_VILLAGE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_ASSOCIATION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_COMMODITIES)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_FARMER_USER_ID))


                );
            } while (cursor.moveToNext());
        }
    }

    private void saveName(final int id, final String firstname, final String surname,
                          final String othernames, final String phone,
                          final String gender, final int region, final int district, final int ward,
                          final int village,final int association_id, final String commodities, final int user_id) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST,AppConfig.URL_INSERT_FARMER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            // System.out.println("JSON RESPONSE: " + obj.toString());
                            //Log.d("JSONR", obj.toString());
                            if (!obj.getBoolean("error")) {
                                //updating the status in sqlite
                                db.updateSyncStatus(id, MainActivity.NAME_SYNCED_WITH_SERVER);

                                Log.d("PHP RESPONSE!!",obj.getString("message"));
                                //sending the broadcast to refresh the list
                                context.sendBroadcast(new Intent(MainActivity.DATA_SAVED_BROADCAST_FARMER));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // params.put("national_id", String.valueOf(id));
                params.put("firstname", firstname);
                params.put("surname",surname);
                params.put("othernames",othernames);
                params.put("phone", phone);
                params.put("gender", gender);
                params.put("region", String.valueOf(region));
                params.put("district", String.valueOf(district));
                params.put("ward", String.valueOf(ward));
                params.put("village", String.valueOf(village));
                params.put("associationid", String.valueOf(association_id));
                params.put("commodities",commodities);
                params.put("userId", String.valueOf(user_id));
                // params.put("user_id", String.valueOf(user_id));

                Log.d("Fetcing Farmers",params.toString());

                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

}
