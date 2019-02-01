package com.advantech.mobile.ubiasoko.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.MainActivity;
import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.model.AssociationDemographicEventHandler;
import com.advantech.mobile.ubiasoko.model.AssociationOtherEventHandler;
import com.advantech.mobile.ubiasoko.model.AssociationRegistrationModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Timestamp;

/**
 * Created by ADVANTECH CONSULTING on 5/22/2018.
 */

public class ReviewAssociationData extends Fragment implements View.OnClickListener {
    EditText assoc_name,assoc_mobile;
    EditText  assoc_commodities;
    EditText assoc_region,assoc_District;//,assoc_ward;
    EditText assoc_firstname,assoc_surname,assoc_othername;


    EditText txt_rid,txt_did;//,txt_wid;
    FloatingActionButton fab_save,fab_cancel;

    DBHandler dbHandler;


    //get Current time stamp
    Timestamp timestamp;

    public String MyMessage;

    RegisterAssociationViewPager registerAssociationViewPager;

    EventBus bus =EventBus.getDefault();
    AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerAssociationViewPager = (RegisterAssociationViewPager) getActivity();

        View v = inflater.inflate(R.layout.review_association_activity,container,false);
        assoc_name = (EditText) v.findViewById(R.id.txt_association_name);
        assoc_name.setEnabled(false);

        assoc_firstname = (EditText) v.findViewById(R.id.txt_association_firstname);
        assoc_firstname.setEnabled(false);

        assoc_surname = (EditText) v.findViewById(R.id.txt_association_surname);
        assoc_surname.setEnabled(false);

        assoc_othername = (EditText) v.findViewById(R.id.txt_association_othername);
        assoc_othername.setEnabled(false);


       // assoc_regno = (EditText) v.findViewById(R.id.txt_registration_number);
        //assoc_regno.setEnabled(false);

        assoc_mobile = (EditText) v.findViewById(R.id.txt_assoc_phoneno);
        assoc_mobile.setEnabled(false);

       // assoc_telephone = (EditText) v.findViewById(R.id.txt_assoc_telno);
       // assoc_telephone.setEnabled(false);

       // assoc_bsn_type = (EditText) v.findViewById(R.id.txt_business_type);
       // assoc_bsn_type.setEnabled(false);

       // assoc_commodities = (EditText) v.findViewById(R.id.txt_assoc_commodities);
       // assoc_commodities.setEnabled(false);

        assoc_region = (EditText) v.findViewById(R.id.txt_region);
        assoc_region.setEnabled(false);

        assoc_District = (EditText) v.findViewById(R.id.txt_association);
        assoc_District.setEnabled(false);

//        assoc_ward = (EditText) v.findViewById(R.id.txt_village);
//        assoc_ward.setEnabled(false);

      //  assoc_street = (EditText) v.findViewById(R.id.txt_street);
      //  assoc_street.setEnabled(false);

      //  assoc_postal_address = (EditText) v.findViewById(R.id.txt_postal_address);
       // assoc_postal_address.setEnabled(false);

      //  assoc_zip = (EditText) v.findViewById(R.id.txt_zip_code);
      //  assoc_zip.setEnabled(false);

        txt_rid = (EditText) v.findViewById(R.id.txt_region_id);
        txt_rid.setEnabled(false);

        txt_did = (EditText) v.findViewById(R.id.txt_districtid);
        txt_did.setEnabled(false);
//
//        txt_wid = (EditText) v.findViewById(R.id.txt_wardid);
//        txt_wid.setEnabled(false);

      //  txt_bid = (EditText) v.findViewById(R.id.txt_business_id);
      //  txt_bid.setEnabled(false);

        fab_save = (FloatingActionButton) v.findViewById(R.id.fab_assoc_save);
        fab_save.setOnClickListener(this);

        fab_cancel = (FloatingActionButton) v.findViewById(R.id.fab_assoc_cancel);
        fab_cancel.setOnClickListener(this);

        dbHandler = new DBHandler(getActivity());

        //current timestamp
        timestamp = new Timestamp(System.currentTimeMillis());








        return v;
    }

    private void clearFields() {
        assoc_name.setText("");
        assoc_firstname.setText("");
        assoc_surname.setText("");
        assoc_othername.setText("");
       // assoc_regno.setText("");
        assoc_mobile.setText("");
       // assoc_telephone.setText("");
       // assoc_bsn_type.setText("");
        //assoc_commodities.setText("");
        assoc_region.setText("");
        assoc_District.setText("");
       // assoc_ward.setText("");
       // assoc_street.setText("");
       // assoc_postal_address.setText("");
       // assoc_zip.setText("");

        txt_rid.setText("");
        txt_did.setText("");
       // txt_wid.setText("");
      //  txt_bid.setText("");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDetailsSent(AssociationDemographicEventHandler demoHandler){

        assoc_name.setText(demoHandler.assoc_name);
        assoc_mobile.setText(demoHandler.assoc_mobileno);
        assoc_firstname.setText(demoHandler.assoc_firstname);
        assoc_surname.setText(demoHandler.assoc_surname);
        assoc_othername.setText(demoHandler.assoc_othername);


    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDetails2Sent(AssociationOtherEventHandler demoHandler){
        assoc_region.setText(demoHandler.region);
        assoc_District.setText(demoHandler.district);
        //assoc_ward.setText(demoHandler.ward);
        txt_rid.setText(demoHandler.region_id);
        txt_did.setText(demoHandler.district_id);
        //txt_wid.setText(demoHandler.ward_id);

    }

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





    //Custom dialog for save
    public void saveDialog(){
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("EXIT")
                .setMessage("Save Data to database.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegisterAssociation();
                      // Toast.makeText(getActivity().getApplicationContext(),"Save Association Data!", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Association Registration cancelled by user", Toast.LENGTH_LONG).show();

                       // clearFields();
                        registerAssociationViewPager.setCurrentItem(0, true);
                        //activate/highlight  the tab as active
                        registerAssociationViewPager.activateTab();
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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab_assoc_previous:
                registerAssociationViewPager.onBackPressed();
                break;

            case R.id.fab_assoc_cancel:
                cancelDialog();
                break;

            case R.id.fab_assoc_save:
                saveDialog();
                break;
        }

    }//end onclick

    private void RegisterAssociation(){
        AssociationRegistrationModel model = new AssociationRegistrationModel();
        model.setAssoc_name(assoc_name.getText().toString());
        model.setAssoc_firstname(assoc_firstname.getText().toString());
        model.setAssoc_surname(assoc_surname.getText().toString());
        model.setAssoc_other_name(assoc_othername.getText().toString());
        //model.setAssoc_regno(assoc_regno.getText().toString());
        model.setAssoc_mobile(assoc_mobile.getText().toString());
       // model.setAssoc_telephone(assoc_telephone.getText().toString());
       // model.setBusiness_type(Integer.parseInt(txt_bid.getText().toString()));
        model.setAssoc_commodities("Mpunga");
        model.setRegion(Integer.parseInt(txt_rid.getText().toString()));
        model.setDistrict(Integer.parseInt(txt_did.getText().toString()));
        model.setWard(3);
      //  model.setStreet(assoc_street.getText().toString());
      //  model.setPostaladdress(assoc_postal_address.getText().toString());
       // model.setZipcode(assoc_zip.getText().toString());
        model.setmTimestamp(String.valueOf(timestamp));
        model.setUser_id(1);
        model.setSyncStatus(0);
        model.setIsdeleted(0);

        dbHandler.RegisterAssociation(model);

        if(dbHandler.MyError == false){

            clearFields();
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
//            registerAssociationViewPager.clearText = true;
//            registerAssociationViewPager.setCurrentItem(0, true);
//           // activate/highlight  the tab as active
//            registerAssociationViewPager.activateTab();
        }


    }
}
