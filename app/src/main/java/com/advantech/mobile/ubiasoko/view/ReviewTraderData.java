package com.advantech.mobile.ubiasoko.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.MainActivity;
import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.model.TraderLocationEventHandler;
import com.advantech.mobile.ubiasoko.model.TraderRegistrationModel;
import com.advantech.mobile.ubiasoko.model.TradersDemographicsEventHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Timestamp;

/**
 * Created by ADVANTECH CONSULTING on 5/21/2018.
 */

public class ReviewTraderData extends android.support.v4.app.Fragment implements View.OnClickListener {
    Context context;
    //get Current time stamp
    Timestamp timestamp;

    public String MyMessage;

    RegisterTraderViewPager registerTraderViewPager;

    EventBus bus =EventBus.getDefault();
    AlertDialog alertDialog;

    EditText txt_nationalid, txt_firstname,txt_surname,txt_othernames;
    EditText txt_dob,txt_phoneno,txt_gender;
    EditText txt_region,txt_district;
    ///,txt_ward,txt_village;

    EditText txt_morelocinfo,txt_businessname,txt_telno,txt_businessType,txt_commodities;
    EditText assocname,associationId;



    FloatingActionButton fab_save,fab_cancel,fab_previous;
    EditText txt_rid,txt_did;//,txt_wid,txt_vill,txt_bsh_typeId,txt_pid;
    DBHandler dbHandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerTraderViewPager = (RegisterTraderViewPager) getActivity();
        context = this.getContext();
        View v = inflater.inflate(R.layout.trader_review,container,false);
//
//        txt_nationalid = (EditText) v.findViewById(R.id.txt_national_id);
//        txt_nationalid.setEnabled(false);
        txt_firstname = (EditText) v.findViewById(R.id.txt_firstname);
        txt_firstname.setEnabled(false);
        txt_surname = (EditText) v.findViewById(R.id.txt_surname);
        txt_surname.setEnabled(false);
        txt_othernames = (EditText) v.findViewById(R.id.txt_othername);
        txt_othernames.setEnabled(false);
//        txt_dob = (EditText) v.findViewById(R.id.txt_dob);
//        txt_dob.setEnabled(false);
        txt_phoneno= (EditText) v.findViewById(R.id.txt_trader_phoneno);
        txt_phoneno.setEnabled(false);
        txt_gender= (EditText) v.findViewById(R.id.txt_gender);
        txt_gender.setEnabled(false);
        txt_region= (EditText) v.findViewById(R.id.txt_region);
        txt_region.setEnabled(false);
        txt_district= (EditText) v.findViewById(R.id.txt_association);
        txt_district.setEnabled(false);
//        txt_ward= (EditText) v.findViewById(R.id.txt_ward);
//        txt_ward.setEnabled(false);
//        txt_village= (EditText) v.findViewById(R.id.txt_village);
//        txt_village.setEnabled(false);
//        txt_morelocinfo= (EditText) v.findViewById(R.id.txt_additional_loc_info);
//        txt_morelocinfo.setEnabled(false);

        txt_businessname= (EditText) v.findViewById(R.id.txt_business);
        txt_businessname.setEnabled(false);

        assocname = (EditText) v.findViewById(R.id.txt_associationname);
        assocname.setEnabled(false);

        associationId = (EditText) v.findViewById(R.id.txt_associationid);
        associationId.setEnabled(false);

//        txt_telno = (EditText) v.findViewById(R.id.txt_telephone_no);
//        txt_telno.setEnabled(false);

//        txt_businessType = (EditText) v.findViewById(R.id.txt_businessType);
//        txt_businessType.setEnabled(false);

//        txt_commodities = (EditText) v.findViewById(R.id.txt_commodities);
//        txt_commodities.setEnabled(false);

        txt_rid =  (EditText) v.findViewById(R.id.txt_region_id);
        txt_did =  (EditText) v.findViewById(R.id.txt_districtid);
//        txt_wid = (EditText) v.findViewById(R.id.txt_wardid);
//        txt_vill =  (EditText) v.findViewById(R.id.txt_villageid);
//        txt_bsh_typeId=  (EditText) v.findViewById(R.id.bsn_id);


        fab_save = (FloatingActionButton) v.findViewById(R.id.fab_trader_save);
        fab_cancel = (FloatingActionButton) v.findViewById(R.id.fab_trader_cancel);
        fab_previous = (FloatingActionButton) v.findViewById(R.id.fab_trader_previous);

        fab_save.setOnClickListener(this);
        fab_cancel.setOnClickListener(this);
        fab_previous.setOnClickListener(this);

        dbHandler = new DBHandler(getActivity());

        //current timestamp
        timestamp = new Timestamp(System.currentTimeMillis());



        return v;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDemoSent(TradersDemographicsEventHandler demoHandler){


        txt_firstname.setText(demoHandler.firstname);
        txt_surname.setText(demoHandler.surname);
        txt_othernames.setText(demoHandler.othernames);
        txt_phoneno.setText(demoHandler.phoneno);
        txt_gender.setText(demoHandler.gender);
        txt_businessname.setText(demoHandler.businessname);
        assocname.setText(demoHandler.asssoc_name);
        associationId.setText(demoHandler.assoc_id);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRegionSent(TraderLocationEventHandler regionHandler){
        txt_region.setText(regionHandler.region);
        txt_district.setText(regionHandler.district);
//        txt_ward.setText(regionHandler.ward);
//        txt_village.setText(regionHandler.village);
        txt_rid.setText(regionHandler.region_id);
        txt_did.setText(regionHandler.district_id);
//        txt_wid.setText(regionHandler.ward_id);
//        txt_vill.setText(regionHandler.village_id);

    }

//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    public void onOtherDetailsSent(TraderOtherDetailsEventHandler otherInfo){
//        txt_businessname.setText(otherInfo.businessName);
//        txt_telno.setText(otherInfo.phoneNo);
//        txt_businessType.setText(otherInfo.businessType);
//        txt_commodities.setText(otherInfo.commodities);
//        txt_bsh_typeId.setText(otherInfo.businessType_ID);
//
//    }
    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        bus.unregister(this);
        super.onStop();
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab_trader_save:
                checkdata();
                break;

            case R.id.fab_trader_cancel:
                cancelDialog();
                break;

            case R.id.fab_trader_previous:
                registerTraderViewPager.onBackPressed();

                break;


        }

    }


    //CHECK IF FIELDS ARE EMPTY
    public void checkdata() {
        if ( txt_firstname.getText().toString().isEmpty()
              ||  txt_surname.getText().toString().isEmpty()
                || txt_othernames.getText().toString().isEmpty() ||
              txt_phoneno.getText().toString().isEmpty() ||
                txt_gender.getText().toString().isEmpty() || txt_region.getText().toString().isEmpty() ||
                txt_district.getText().toString().isEmpty() ||txt_businessname.getText().toString().isEmpty()) {

            alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("INPUT VALIDATION")
                    .setMessage("INPUT VALIDATION ERROR: FILL ALL FIELD AND TRY AGAIN.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                            //goto next tab

                            registerTraderViewPager.setCurrentItem(0, true);
                            //activate/highlight  the tab as active
                            registerTraderViewPager.activateTab();
                        }
                    })

                    .show();

        } else {
            //save farmer data to database
            saveDialog();
            // Toast.makeText(getActivity(),"DATA VALIDATION PASSED!!",Toast.LENGTH_LONG).show();
        }
    }//end method

    //Custom dialog for save
    public void saveDialog(){
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("EXIT")
                .setMessage("Are you sure you want register this Trader to Jukwaa platform.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegisterTrader();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }

    private void RegisterTrader(){
        TraderRegistrationModel model = new TraderRegistrationModel();
        //model.setNationalid(Integer.parseInt(txt_nationalid.getText().toString().trim()));
        model.setFirstname(txt_firstname.getText().toString().trim().toUpperCase());
        model.setSurname(txt_surname.getText().toString().trim().toUpperCase());
        model.setOthernames(txt_othernames.getText().toString().trim().toUpperCase());
      //  model.setDob(txt_dob.getText().toString());
        model.setPhoneno(txt_phoneno.getText().toString());
        model.setGender(txt_gender.getText().toString());
        model.setRegion(Integer.parseInt(txt_rid.getText().toString()));
        model.setDistrict(Integer.parseInt(txt_did.getText().toString()));
       model.setWard(3);
       model.setVillage(1);
        //model.setMore_loc_info(txt_morelocinfo.getText().toString());
        model.setBusinessname(txt_businessname.getText().toString().toUpperCase());
      //  model.setTelephoneNo(txt_telno.getText().toString());
      //  model.setBusinessType(Integer.parseInt(txt_bsh_typeId.getText().toString()));
      //  model.setCommodity(txt_commodities.getText().toString());
        model.setAssociationname(assocname.getText().toString());
        model.setAssociation_id(Integer.parseInt(associationId.getText().toString()));

        model.setSyncStatus(0);
        model.setUser_id(1);
        model.setmTimestamp(String.valueOf(timestamp));
        model.setIsdeleted(0);


        dbHandler.RegisterTrader(model);

        // MyMessage = dbHandler.MyMessage;

        //  Toast.makeText(getActivity(),MyMessage,Toast.LENGTH_LONG).show();

        if(dbHandler.MyError == false){

            clearFields();
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
           // registerTraderViewPager.clearText = true;
           // registerTraderViewPager.setCurrentItem(0, true);
            //activate/highlight  the tab as active
          //  registerTraderViewPager.activateTab();
        }
//          else if(dbHandler.MyError == true){
//                 Toast.makeText(getActivity(),MyMessage,Toast.LENGTH_LONG).show();
//
//       }



    }

    private void clearFields() {

        //txt_nationalid.setText("");
        txt_firstname.setText("");
        txt_surname.setText("");
        txt_othernames.setText("");
      //  txt_dob.setText("");
        txt_phoneno.setText("");
        txt_gender.setText("");
        txt_region.setText("");
        txt_district.setText("");
//        txt_ward.setText("");
//        txt_village.setText("");
        //txt_morelocinfo.setText("");
        txt_businessname.setText("");
        //txt_telno.setText("");
       // txt_businessType.setText("");
       // txt_commodities.setText("");
        txt_rid.setText("");
        txt_did.setText("");
//        txt_wid.setText("");
//        txt_vill.setText("");

    }

    //Custom dialog for cancel
    public void cancelDialog(){
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("EXIT")
                .setMessage("Are you sure you want to cancel the transaction?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getActivity().getApplicationContext(),"Registration cancelled by user", Toast.LENGTH_LONG).show();

                        clearFields();
                        registerTraderViewPager.setCurrentItem(0, true);
                        //activate/highlight  the tab as active
                        registerTraderViewPager.activateTab();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }




    }
