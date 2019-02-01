package com.advantech.mobile.ubiasoko.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.MainActivity;
import com.advantech.mobile.ubiasoko.model.AssocView.GetAssociationIdModel;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ADVANTECH CONSULTING on 7/15/2018.
 */

public class NetworkChecker_AssociationSync extends BroadcastReceiver {
    //context and database helper object
    private Context context;
    private DBHandler db;
    GetAssociationIdModel assocmodel;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        db = new DBHandler(context);
        assocmodel = new GetAssociationIdModel();

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                //getting all the unsynced names
                Cursor cursor = db.getUnsyncedAssociationname();
                if (cursor.moveToFirst()) {
                    do {
                        //calling the method to save the unsynced name to MySQL
                        saveName(
                                cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOCIATION_ID)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOCIATION_NAME)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOCIATION_FIRSTNAME)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOCIATION_SURNAME)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOCIATION_OTHERNAME)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOC_MOBILENO)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOC_COMMODITIES)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOC_REGION)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOC_DISTRICT)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOC_WARD)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOC_CREATED_AT)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_ASSOC_USER_ID))


                        );
                    } while (cursor.moveToNext());
                }
            }
        }


    }


    private void saveName(final int id, final String name, final String firstname, final String surname, final String othername, final String phone,
                          final String commodity,final int region, final int district, final int ward,
                      final String createdat,final int user_id) {

        StringRequest assocstringRequest = new StringRequest(Request.Method.POST,AppConfig.URL_INSERT_ASSOCIATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            // System.out.println("JSON RESPONSE: " + obj.toString());
                            //Log.d("JSONR", obj.toString());
                            if (!obj.getBoolean("error")) {
                                //new id is
                                int newId = Integer.parseInt(obj.getString("id"));

                                //updating the status in sqlite
                                db.updateAssocSyncStatus(id, MainActivity.NAME_SYNCED_WITH_SERVER,newId);
                                Log.d("THIS is Our ID::", String.valueOf(newId));//
                                //sending the broadcast to refresh the list
                                context.sendBroadcast(new Intent(MainActivity.DATA_SAVED_BROADCAST_ASSOCIATION));
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
                //params.put("farmerId", String.valueOf(id));
                params.put("name", name);
                params.put("firstname", firstname);
                params.put("surname", surname);
                params.put("othername", othername);
                params.put("phone",phone);
                params.put("commodity",commodity);
                params.put("region", String.valueOf(region));
                params.put("district", String.valueOf(district));
                params.put("ward", String.valueOf(ward));

                params.put("created_at",createdat);
                params.put("userId", String.valueOf(user_id));
                // params.put("user_id", String.valueOf(user_id));

                Log.d("FetchingASSOC",params.toString());

                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(assocstringRequest);

    }

//
//    private void updateAssociationId(){
//
//        String URL_INSERT_ASSOCIATION = "http://18.216.188.180/jukwaa/insertassociation.php";
//
//        final List<GetAssociationIdModel> associationIdModels = new ArrayList<>();
//
//////        GetAssociationIdModel associationIdModel = new GetAssociationIdModel();
////
////        int oldId = associationIdModel.getFarmerId();
////        int newId = associationIdModel.getAssociationId();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_INSERT_ASSOCIATION,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONArray myArray = new JSONArray(response);
//
//                            //traverse through
//                            for (int i = 0; i < myArray.length(); i++) {
//                                JSONObject jsonObject = myArray.getJSONObject(i);
//
//                                //add the id to list
//                                associationIdModels.add(new GetAssociationIdModel(
//                                        jsonObject.getInt("id"),
//                                        jsonObject.getInt("newId")
//
//                                ));
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        //adding our stringrequest to queue
//        Volley.newRequestQueue(context).add(stringRequest);
//
//
//    }

}
