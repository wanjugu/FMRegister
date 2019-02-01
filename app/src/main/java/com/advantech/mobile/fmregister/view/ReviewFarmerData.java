package com.advantech.mobile.fmregister.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.advantech.mobile.fmregister.MainActivity;
import com.advantech.mobile.fmregister.R;
import com.advantech.mobile.fmregister.controller.DBHandler;
import com.advantech.mobile.fmregister.model.FarmerDemographicsEventHandler;
import com.advantech.mobile.fmregister.model.FarmerLocationEventHandler;
import com.advantech.mobile.fmregister.model.FarmersRegistrationModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Timestamp;

/**
 * Created by ADVANTECH CONSULTING on 4/27/2018.
 */

public class ReviewFarmerData extends Fragment implements View.OnClickListener {

    //get Current time stamp
    Timestamp timestamp;
    MainActivity mainActivity;

    public String MyMessage;

    RegisterFarmerViewPager registerFarmerViewPager;

    EventBus bus =EventBus.getDefault();
    AlertDialog alertDialog;

    EditText txt_firstname,txt_surname,txt_othernames;
    EditText txt_phoneno,txt_gender;
    EditText txt_region,txt_district,txt_ward,txt_village;
    EditText txt_association_name;


    FloatingActionButton fab_save,fab_cancel;

    //un
    EditText txt_rid,txt_did,txt_wid,txt_vill,txt_assoc_id,txt_pid;

    DBHandler dbHandler;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerFarmerViewPager = (RegisterFarmerViewPager) getActivity();

        View v =  inflater.inflate(R.layout.farmer_review, container, false);
//        txt_nationalid = (EditText) v.findViewById(R.id.txt_national_id);
//        txt_nationalid.setEnabled(false);
        txt_firstname = (EditText) v.findViewById(R.id.txt_firstname);
        txt_firstname.setEnabled(false);
        txt_surname = (EditText) v.findViewById(R.id.txt_surname);
        txt_surname.setEnabled(false);
        txt_othernames = (EditText) v.findViewById(R.id.txt_othername);
        txt_othernames.setEnabled(false);
//        txt_dob = (EditText) v.findViewById(R.id.txt_dob);
        //txt_dob.setEnabled(false);
        txt_phoneno= (EditText) v.findViewById(R.id.txt_phoneno2);
        txt_phoneno.setEnabled(false);
        txt_gender= (EditText) v.findViewById(R.id.txt_gender);
        txt_gender.setEnabled(false);
        txt_region= (EditText) v.findViewById(R.id.txt_region);
        txt_region.setEnabled(false);
        txt_district= (EditText) v.findViewById(R.id.txt_association);
        txt_district.setEnabled(false);
        txt_ward= (EditText) v.findViewById(R.id.txt_ward);
        txt_ward.setEnabled(false);
        txt_village= (EditText) v.findViewById(R.id.txt_village);
        txt_village.setEnabled(false);
//        txt_myAssociation_id= (EditText) v.findViewById(R.id.txt_assoc_id);
//        txt_myAssociation_id.setEnabled(false);
//        txt_businesstype= (EditText) v.findViewById(R.id.txt_business);
//        txt_businesstype.setEnabled(false);

        txt_association_name= (EditText) v.findViewById(R.id.txt_assoc_name);
        txt_association_name.setEnabled(false);
//        txt_position=(EditText) v.findViewById(R.id.txt_position);
//        txt_position.setEnabled(false);
//        txt_commodity= (EditText) v.findViewById(R.id.txt_commodities);
//        txt_commodity.setEnabled(false);

        txt_rid =  (EditText) v.findViewById(R.id.txt_region_id);
        txt_did =  (EditText) v.findViewById(R.id.txt_districtid);
        txt_wid = (EditText) v.findViewById(R.id.txt_wardid);
        txt_vill =  (EditText) v.findViewById(R.id.txt_villageid);
        txt_assoc_id =  (EditText) v.findViewById(R.id.associd);
        txt_pid =  (EditText) v.findViewById(R.id.txt_pid);
        //txt_Bsn_type_id = (EditText) v.findViewById(R.id.txt_Bsn_type_id);

        fab_save = (FloatingActionButton) v.findViewById(R.id.fab_farmer_save);
        fab_cancel = (FloatingActionButton) v.findViewById(R.id.fab_farmer_cancel);

        fab_save.setOnClickListener(this);
        fab_cancel.setOnClickListener(this);

        dbHandler = new DBHandler(getActivity());

        //current timestamp
        timestamp = new Timestamp(System.currentTimeMillis());

        mainActivity = new MainActivity();

        return v;

    }

      @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDemoSent(FarmerDemographicsEventHandler demoHandler){


        txt_firstname.setText(demoHandler.firstname);
        txt_surname.setText(demoHandler.surname);
        txt_othernames.setText(demoHandler.othernames);
        txt_phoneno.setText(demoHandler.phoneno);
        txt_gender.setText(demoHandler.gender);
        txt_assoc_id.setText(demoHandler.assoc_id);
        txt_association_name.setText(demoHandler.association_name);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRegionSent(FarmerLocationEventHandler regionHandler){
        txt_region.setText(regionHandler.region);
        txt_district.setText(regionHandler.district);
        txt_ward.setText(regionHandler.ward);
        txt_village.setText(regionHandler.village);
        txt_rid.setText(regionHandler.region_id);
        txt_did.setText(regionHandler.district_id);
        txt_wid.setText(regionHandler.ward_id);
        txt_vill.setText(regionHandler.village_id);

    }
//
//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    public void onOtherDetailsSent(FarmerOtherInfoEventHandler otherInfo){
//
//        txt_association_name.setText(otherInfo.association_name);
//        txt_commodity.setText(otherInfo.my_commodities);
//        txt_assoc_id.setText(otherInfo.assoc_id);
//
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
        switch (view.getId()){
            case R.id.fab_farmer_save:
                checkdata();
                break;
            case R.id.fab_farmer_cancel:
                cancelDialog();
                break;
        }
    }//end


    //CHECK IF FIELDS ARE EMPTY
    public void checkdata(){
        if(txt_firstname.getText().toString().isEmpty() ||
                txt_surname.getText().toString().isEmpty() || txt_othernames.getText().toString().isEmpty()
                || txt_phoneno.getText().toString().isEmpty() ||
                txt_gender.getText().toString().isEmpty() || txt_region.getText().toString().isEmpty() ||
                txt_district.getText().toString().isEmpty() || txt_ward.getText().toString().isEmpty() ||
                txt_village.getText().toString().isEmpty() || txt_association_name.getText().toString().isEmpty()
                ){

            alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("INPUT VALIDATION")
                    .setMessage("INPUT VALIDATION ERROR: FILL ALL FIELD AND TRY AGAIN.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                            //goto next tab

                            registerFarmerViewPager.setCurrentItem(0, true);
                            //activate/highlight  the tab as active
                            registerFarmerViewPager.activateTab();
                        }
                    })

                    .show();

        }else{
            //save farmer data to database
           saveDialog();
           // Toast.makeText(getActivity(),"DATA VALIDATION PASSED!!",Toast.LENGTH_LONG).show();
        }

    }//end checkdata
    //Custom dialog for save
    public void saveDialog(){
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("EXIT")
                .setMessage("Are you sure you want register this farmer to Jukwaa platform.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegisterFarmer();

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
    //Custom dialog for cancel
    public void cancelDialog(){
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("EXIT")
                .setMessage("Are you sure you want to cancel the transaction?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity().getApplicationContext(),"Registration cancelled by user", Toast.LENGTH_LONG).show();
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

    private void RegisterFarmer(){

        FarmersRegistrationModel model = new FarmersRegistrationModel();

        model.setFirstname(txt_firstname.getText().toString());
        model.setSurname(txt_surname.getText().toString());
        model.setOthernames(txt_othernames.getText().toString());

        model.setPhoneno(txt_phoneno.getText().toString());
        model.setGender(txt_gender.getText().toString());

        model.setRegion(Integer.parseInt(txt_rid.getText().toString()));
        model.setDistrict(Integer.parseInt(txt_did.getText().toString()));
       // model.setWard(Integer.parseInt(txt_wid.getText().toString()));
       // model.setVillage(Integer.parseInt(txt_vill.getText().toString()));

        model.setAssoc_name(Integer.parseInt(txt_assoc_id.getText().toString()));

        model.setCommodity("MPUNGA"); //Only collecting Mpunga
        model.setmTimestamp(String.valueOf(timestamp));
        model.setUpdatestatus(0);
        model.setUser_id(1);
        model.setIsdeleted(0);

        dbHandler.RegisterFarmer(model);
       // MyMessage = dbHandler.MyMessage;

     //  Toast.makeText(getActivity(),MyMessage,Toast.LENGTH_LONG).show();

       if(dbHandler.MyError == false){

           clearFields();
           Intent intent = new Intent(getActivity(),MainActivity.class);
           startActivity(intent);

                ///registerFarmerViewPager.clearText = true;
               // registerFarmerViewPager.setCurrentItem(0, true);
                //activate/highlight  the tab as active
              //  registerFarmerViewPager.activateTab();


           }
//          else if(dbHandler.MyError == true){
//                 Toast.makeText(getActivity(),MyMessage,Toast.LENGTH_LONG).show();
//
//       }

    }//end method

    private void clearFields(){

        //txt_nationalid.setText("");
        txt_firstname.setText("");
        txt_surname.setText("");
        txt_othernames.setText("");
      //  txt_dob.setText("");
        txt_phoneno.setText("");
        txt_gender.setText("");
        txt_region.setText("");
        txt_district.setText("");
        txt_ward.setText("");
        txt_village.setText("");

        //txt_businesstype.setText("");
        txt_association_name.setText("");
        //txt_position.setText("");
        //txt_commodity.setText("");
        txt_rid.setText("");
        txt_did.setText("");
        txt_wid.setText("");
        txt_vill.setText("");

//        if (txt_nationalid.toString().isEmpty() && txt_firstname.getText().toString().isEmpty() && txt_surname.getText().toString().isEmpty()
//                && txt_othernames.getText().toString().isEmpty() && txt_dob.getText().toString().isEmpty()
//                &&txt_phoneno.getText().toString().isEmpty() && txt_gender.getText().toString().isEmpty()
//                && txt_region.getText().toString().isEmpty() && txt_district.getText().toString().isEmpty() &&
//                txt_ward.getText().toString().isEmpty() && txt_village.getText().toString().isEmpty() &&
//                txt_association_name.getText().toString().isEmpty() && txt_businesstype.getText().toString().isEmpty()
//                ){
//                    return true;
//        }else{
//            return false;
        }



}
