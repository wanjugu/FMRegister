package com.advantech.mobile.fmregister.controller;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by ADVANTECH CONSULTING on 6/27/2018.
 */

public class UserSyncService extends JobService {
    private AsyncTask mBackgroundTask;
    private Context ctx= UserSyncService.this;

    private static final String TAG = UserSyncService.class.getSimpleName();
    DBHandler controller;
    @Override
    public boolean onStartJob(final JobParameters job) {
        controller = new DBHandler(ctx);
        //final ReminderTasks reminderTasks = new ReminderTasks();

        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
              //  syncFarmers();
                // reminderTasks.executeTask(ctx,ReminderTasks.ACTION_SYNC_USERS);
                /// ReminderTasks.executeTask(ctx,ReminderTasks.ACTION_SYNC_USERS);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job,false);
            }
        };

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
//
//    @Override
//    public boolean onStopJob(JobParameters job) {
//        return false;
//    }
//
//    public void  syncFarmers(){
//        //Create AsycHttpClient object
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        ArrayList<HashMap<String, String>> userList =  controller.fetchFarmersForSync();
//        if(userList.size()!=0){
//            if(controller.dbSyncCount() != 0){
//                //prgDialog.show();http://18.216.188.180/jukwaa/insertuser.php
//                params.put("usersJSON", controller.composeJSONfromSQLite());
//                client.post("http://18.216.188.180/jukwaa/insertuser.php",params ,new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(String response) {
//                        System.out.println(response);
//                        // prgDialog.hide();
//                        try {
//                            JSONArray arr = new JSONArray(response);
//                            System.out.println(arr.length());
//                            for(int i=0; i<arr.length();i++){
//                                JSONObject obj = (JSONObject)arr.get(i);
//                                System.out.println(obj.get("nationalid"));
//                                System.out.println(obj.get("status"));
//                                controller.updateSyncStatus(obj.get("nationalid").toString(),obj.get("status").toString());
//                            }
//                            Log.d("SYNC SUCCESS:","DB Sync completed!");
//                           // Toast.makeText(getApplicationContext(), "DB Sync completed!", Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                           // Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
//                            Log.d("SYNC ERROR:","Error Occured [Server's JSON response might be invalid]!");
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Throwable error,
//                                          String content) {
//                        // TODO Auto-generated method stub
//                        //prgDialog.hide();
//                        if(statusCode == 404){
//                           // Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
//                            Log.d("SYNC ERROR:","Requested resource not found");
//                        }else if(statusCode == 500){
//                            //Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
//                            Log.d("SYNC ERROR:","Something went wrong at server end");
//                        }else{
//                           // Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
//                            Log.d("SYNC ERROR:","Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]");
//                        }
//                    }
//                });
//            }else{
//               // Toast.makeText(getApplicationContext(), "SQLite and Remote MySQL DBs are in Sync!", Toast.LENGTH_LONG).show();
//                Log.d("SYNC ERROR:","SQLite and Remote MySQL DBs are in Sync!");
//            }
//        }else{
//            //Toast.makeText(getApplicationContext(), "No data in SQLite DB, please do enter User name to perform Sync action", Toast.LENGTH_LONG).show();
//            Log.d("SYNC ERROR:","No data in SQLite DB, please do enter User name to perform Sync action");
//        }
//    }
}
