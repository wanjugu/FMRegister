package com.advantech.mobile.fmregister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.advantech.mobile.fmregister.controller.DBHandler;
import com.advantech.mobile.fmregister.controller.FarmerSyncService;
import com.advantech.mobile.fmregister.loginmanager.SessionManager;
import com.advantech.mobile.fmregister.view.LoginActivity;
import com.advantech.mobile.fmregister.view.RegisterAssociationViewPager;
import com.advantech.mobile.fmregister.view.RegisterFarmerViewPager;
import com.advantech.mobile.fmregister.view.RegisterTraderViewPager;
import com.advantech.mobile.fmregister.view.ViewRegisteredAssociation;
import com.advantech.mobile.fmregister.view.ViewRegisteredFarmers;
import com.advantech.mobile.fmregister.view.ViewRegisteredTraders;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DBHandler dbHandler;

  /*------------------------------------------------------------------------------
    ---------SYNC DATA------------------
    ---------------------------------------------------------------------------------*/
  //1 means data is synced and 0 means data is not synced
    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;
//
//    NetworkChecker_AssociationSync networkChecker_associationSync;
//    NetworkChecker_FarmerSync networkChecker_farmerSync;
//    NetworkChecker_TraderSync networkChecker_traderSync;
//
//    private BroadcastReceiver traderbroadcastReceiver1;
//    private BroadcastReceiver farmerbroadcastReceiver1;
//    private BroadcastReceiver assocbroadcastReceiver1;

    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST_ASSOCIATION = "com.advantech.mobile.fmregister.controller.networkcheckerassociationsync";
    public static final String DATA_SAVED_BROADCAST_FARMER= "com.advantech.mobile.fmregister.controller.networkcheckerfarmersync";
    public static final String DATA_SAVED_BROADCAST_TRADER = "com.advantech.mobile.fmregister.controller.networkcheckertradersync";

//    //Broadcast receiver to know the sync status
//    private BroadcastReceiver broadcastReceiver;


    Context context;




    /*------------------------------------------------------------------------------
    ----------This code takes care of the Grid view in Home Screen-------------------
    ---------------------------------------------------------------------------------*/

    GridView androidGridView;

    String[] gridViewString = {
            "FARMERS", "TRADERS", "ASSOCIATIONS", "LOGOUT",

    };

    int[] gridViewImageId = {
            R.drawable.icons8_farmer_100, R.drawable.icons8_money_bag_100, R.drawable.icons8_headquarters_100, R.drawable.icons_shutdown_100,
    };

    /*-----------------------------------------------------------------------------------
    -----------------------------end Grid view-------------------------------------------------
    -------------------------------------------------------------------------------------------------*/

    private SessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //create a session
        this.session = new SessionManager(this.getApplicationContext());
        if (!this.session.isLoggedIn()) {
            this.logoutUser();
        }

        /*------------------------------------------------------------------------------
    ----------This code takes care of the Grid view in Home Screen-------------------
    ----------------------------onCreate()-----------------------------------------------------*/
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(MainActivity.this,
                gridViewString,gridViewImageId);

        androidGridView = (GridView) findViewById(R.id.grid_view_image_text);

        androidGridView.setAdapter(adapterViewAndroid);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(MainActivity.this, "GridView Item: " + gridViewImageId[+i], Toast.LENGTH_LONG).show();
                if (position == 0){//view registered farmers
                    //Toast.makeText(MainActivity.this, "This is Farmers View", Toast.LENGTH_LONG).show();
                    Intent farmerIntent = new Intent(MainActivity.this, RegisterFarmerViewPager.class);
                    startActivity(farmerIntent);
                }
                if(position == 1){//Register Farmer
                    MainActivity.this.startActivity(new Intent((Context)MainActivity.this,
                            (Class)RegisterTraderViewPager.class));
                }

                if (position == 2){//New Collection
                    //return;

                    // MainActivity.this.startActivity(new Intent((Context)MainActivity.this,
                    //       (Class)CollectionMainActivity.class));

                    // Toast.makeText(MainActivity.this, "Collection View", Toast.LENGTH_LONG).show();
                    MainActivity.this.startActivity(new Intent((Context)MainActivity.this,
                            (Class)RegisterAssociationViewPager.class));

                }

                if (position == 3){
                    MainActivity.this.logoutUser();
                    //Toast.makeText(MainActivity.this, "Logout View", Toast.LENGTH_LONG).show();
                }


            }
        });
/*-------------------------------------------------------------------------------------*/

        dbHandler = new DBHandler(this);
        //the broadcast receiver to update sync status
//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                //loading the names again
//                //loadNames();
//            }
//        };

        context =this.getApplicationContext();

       // manageFarmerSyncJob(context);



//        //create a network change broadcast
//        networkChecker_associationSync = new NetworkChecker_AssociationSync();
//        networkChecker_farmerSync = new NetworkChecker_FarmerSync();
//        networkChecker_traderSync = new NetworkChecker_TraderSync();
//
//        traderbroadcastReceiver1 = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//            }
//        };
//
//        farmerbroadcastReceiver1 = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//            }
//        };
//
//        assocbroadcastReceiver1 =new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//            }
//        };

//        //registering the broadcast receiver to update sync status
//        registerReceiver(traderbroadcastReceiver1, new IntentFilter(DATA_SAVED_BROADCAST_TRADER));
//        registerReceiver(assocbroadcastReceiver1, new IntentFilter(DATA_SAVED_BROADCAST_ASSOCIATION));
//        registerReceiver(farmerbroadcastReceiver1, new IntentFilter(DATA_SAVED_BROADCAST_FARMER));

//        registerReceiver(new NetworkChecker_AssociationSync(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        registerReceiver(new NetworkChecker_TraderSync(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        registerReceiver(new NetworkChecker_FarmerSync(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));



//        //registering the broadcast receiver to update sync status
//        //registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));
//        IntentFilter intent = new IntentFilter();
//        intent.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        //intentFilter.setPriority(100);
//        registerReceiver(networkChecker_farmerSync,intent);
//
//
//        //register Intent Receiver for Trader registration
//        IntentFilter traderintent = new IntentFilter();
//        traderintent.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkChecker_traderSync,traderintent);
//
//        //register Intent Receiver for Trader registration
//        IntentFilter associntent = new IntentFilter();
//        associntent.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkChecker_associationSync,associntent);

////Get User records from SQLite DB
//        ArrayList<HashMap<String, String>> userList =  dbHandler.fetchFarmersForSync();
//        //
//        if(userList.size()!=0){
////            //Set the User Array list in ListView
////            ListAdapter adapter = new SimpleAdapter( MainActivity.this,userList, R.layout.view_user_entry, new String[] { "userId","userName"}, new int[] {R.id.userId, R.id.userName});
////            ListView myList=(ListView)findViewById(android.R.id.list);
////            myList.setAdapter(adapter);
//            //Display Sync status of SQLite DB
//            Toast.makeText(getApplicationContext(), dbHandler.getSyncStatus(), Toast.LENGTH_LONG).show();
//        }
////trigger the Job
     //   manageSyncJob(this);
    }//end oncreate

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void logoutUser() {
        this.session.setLogin(false);
        this.startActivity(new Intent((Context)this, (Class)LoginActivity.class));
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_farmers) {
            // open the Fragment
           Intent intent = new Intent(this,RegisterFarmerViewPager.class);
           startActivity(intent);

        } else if (id == R.id.nav_traders) {
            // open the Fragment
            Intent intent = new Intent(this,RegisterTraderViewPager.class);
            startActivity(intent);
        } else if (id == R.id.nav_fos) {
            // open the Fragment
            Intent intent = new Intent(this,RegisterAssociationViewPager.class);
            startActivity(intent);

        }
// else if (id == R.id.nav_help) {
//
//        } else if (id == R.id.nav_about) {
//
//        }
        else if (id == R.id.nav_logout) {
            logoutUser();

        }else if(id == R.id.nav_view_farmers){
            Intent intent = new Intent(this,ViewRegisteredFarmers.class);
            startActivity(intent);

        }else if(id == R.id.nav_view_traders){
            Intent intent = new Intent(this,ViewRegisteredTraders.class);
            startActivity(intent);

        }else if(id == R.id.nav_view_fos){
            Intent intent = new Intent(this,ViewRegisteredAssociation.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(networkChecker_associationSync != null) {
//            unregisterReceiver(networkChecker_associationSync);
//        }
//        if(networkChecker_farmerSync != null) {
//            unregisterReceiver(networkChecker_farmerSync);
//        }
//        if(networkChecker_traderSync != null) {
//            unregisterReceiver(networkChecker_traderSync);
//        }
//
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if(networkChecker_associationSync == null){
//            IntentFilter associntent = new IntentFilter();
//        associntent.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkChecker_associationSync,associntent);
//
//        }
//
//        if(networkChecker_farmerSync == null){
//            IntentFilter intent = new IntentFilter();
//        intent.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        //intentFilter.setPriority(100);
//        registerReceiver(networkChecker_farmerSync,intent);
//
//        }
//
//        if(networkChecker_traderSync == null){
//            IntentFilter associntent = new IntentFilter();
//        associntent.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkChecker_traderSync,associntent);
//
//        }
//
//    }//end onResume

//    @Override
//    protected void onPause() {
//
//        if(networkChecker_associationSync != null){
//            unregisterReceiver(networkChecker_associationSync);
//            networkChecker_associationSync=null;
//        }
//        if(networkChecker_farmerSync != null){
//            unregisterReceiver(networkChecker_farmerSync);
//            networkChecker_farmerSync = null;
//        }
//
//        if(networkChecker_traderSync != null){
//            unregisterReceiver(networkChecker_traderSync);
//            networkChecker_traderSync=null;
//
//        }
//        super.onPause();
//    }

    /*------------------------------------------------------------------------------
    ----------Variables for running sync  Activity-------------------*/
//    public static final String SYNC_JOB_TAG = "sync-users-tag";
//
//    private static final int REMINDER_INTERVAL_MINUTES = 2;
//    private static final int REMINDER_INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES));
//    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;
    /*---------------------------------------------------------------------------------*/

    private void manageFarmerSyncJob(Context context){

        final String JobTag = "farmer_sync_tag";
        FirebaseJobDispatcher jobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job job = jobDispatcher.newJobBuilder()
                .setService(FarmerSyncService.class)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTag(JobTag)
                .setTrigger(Trigger.executionWindow(0,60))
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();

        jobDispatcher.mustSchedule(job);
        Toast.makeText(this,"Job Scheduled..", Toast.LENGTH_SHORT).show();






//        JobScheduler jobScheduler = (JobScheduler) context
//                .getSystemService(Context.JOB_SCHEDULER_SERVICE);



//        //Run the Job approximately 15 minutes
//        long jobInterval = 900000L;
//
//        ComponentName jobService = new ComponentName(this,FarmerSyncService.class);
//        JobInfo task = null;
//
//		task = new JobInfo.Builder(SYNC_JOB_ID,jobService)
//				.setMinimumLatency(jobInterval)
//				.setOverrideDeadline(jobInterval)
//				.setPersisted(true)
//				.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//				.build();
//
//		if(jobScheduler.schedule(task) != JobScheduler.RESULT_SUCCESS){
//
//		}


//        Driver driver = new GooglePlayDriver(context);
//        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
//        Job syncuserJob = dispatcher.newJobBuilder()
//                .setService(FarmerSyncService.class)
//                .setTag(SYNC_JOB_TAG)
//                .setConstraints(Constraint.ON_ANY_NETWORK)
//                .setLifetime(Lifetime.FOREVER)
//                .setRecurring(true)
//                .setTrigger(Trigger.executionWindow(
//                        REMINDER_INTERVAL_SECONDS,
//                        REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS
//                ))
//                .setReplaceCurrent(true)
//                .build();
//
//        dispatcher.schedule(syncuserJob);
   }

}//end main activity
