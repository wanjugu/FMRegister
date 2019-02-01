package com.advantech.mobile.ubiasoko.model.FarmersView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.AssociationSpinnerController;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.controller.DistrictSpinnerController;
import com.advantech.mobile.ubiasoko.controller.RegionSpinnerController;
import com.advantech.mobile.ubiasoko.controller.VillageSpinnerController;
import com.advantech.mobile.ubiasoko.controller.WardSpinnerController;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ADVANTECH CONSULTING on 5/25/2018.
 */

public class FarmersDetailedView extends AppCompatActivity implements View.OnClickListener {
     TextView farmer_name,farmer_id,farmer_region,farmer_assoc,tv_gender;
     TextView farmer_firstname, farmer_surname;

     TextInputLayout tv_dob;

     TextView othernames,farmer_phone;
     String regionId,districtId,villageId,wardId;
     String association_id,syncStatus;


    TextView tv_regionId,tv_districtId,tv_villageId,tv_wardId;
    TextView tv_association_id;


     String districtname,wardname,villagename,regionname,gender,genderId,assocname;
     TextView products;


     Spinner spinner_region,spinner_district,spinner_ward,spinner_village;
     Spinner spinner_gender,spinner_assoc;
     FloatingActionButton fab_save,fab_edit,fab_cancel;

    int SelectedRegionID;
    int SelectedWardID;
    int SelectedDistrictID;

    DBHandler dbHandler;
    Context context;

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    DatePickerDialog dateDatePickerDialog;
    Calendar dateCalendarPicker;
    AlertDialog.Builder alertDialog;

    Timestamp timestamp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_detailed_view_activity);

        context = this.getApplicationContext();
        dbHandler = new DBHandler(context);
        alertDialog = new AlertDialog.Builder(this);


        farmer_id = (TextView) findViewById(R.id.tv_id);
      //  farmer_id.setEnabled(false);
//        myAssoc_id = (TextView) findViewById(R.id.txt_assoc_id);
//        myAssoc_id.setEnabled(false);
        farmer_firstname = (TextView) findViewById(R.id.txt_firstName);
        farmer_firstname.setEnabled(false);
        farmer_surname = (TextView) findViewById(R.id.txt_surname);
        farmer_surname.setEnabled(false);
        othernames = (TextView) findViewById(R.id.txt_othernames);
        othernames.setEnabled(false);
        //tv_dob = (TextInputLayout) findViewById(R.id.tv_dob);
//        farmer_dob = (TextView) findViewById(R.id.txt_dob);
//        farmer_dob.setEnabled(false);
//        farmer_dob.setFocusable(false);//disable focus
//        if (savedInstanceState != null) {
//            dateCalendarPicker = Calendar.getInstance();
//            if (savedInstanceState.getLong("dateCalendar") != 0)
//                dateCalendarPicker.setTime(new Date(savedInstanceState
//                        .getLong("dateCalendar")));
//        }
//        farmer_dob.setOnClickListener(this);
//        setCalendar();
        farmer_phone = (TextView) findViewById(R.id.txt_phoneno);
        farmer_phone.setEnabled(false);

        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        spinner_gender.setEnabled(false);

        tv_gender = (TextView) findViewById(R.id.tv_edit_gender);

        spinner_district = (Spinner) findViewById(R.id.spinner_district);
        spinner_district.setEnabled(false);
        spinner_region = (Spinner) findViewById(R.id.spinner_region);
        spinner_region.setEnabled(false);
        spinner_ward = (Spinner) findViewById(R.id.spinner_wardid);
        spinner_ward.setEnabled(false);

        spinner_village =(Spinner) findViewById(R.id.spinner_village);
        spinner_village.setEnabled(false);

//        spinner_businessTYpe =(Spinner) findViewById(R.id.spinner_business);
//        spinner_businessTYpe.setEnabled(false);

//        spinner_position =(Spinner) findViewById(R.id.spinner_position);
//        spinner_position.setEnabled(false);

        spinner_assoc = (Spinner) findViewById(R.id.spinner_association);
        spinner_assoc.setEnabled(false);

        tv_regionId = (TextView) findViewById(R.id.tv_edit_region_id);
        tv_districtId = (TextView) findViewById(R.id.tv_edit_district_id);
        tv_villageId = (TextView) findViewById(R.id.tv_edit_village_id);
        tv_wardId = (TextView) findViewById(R.id.tv_edit_ward_id);
       // tv_businessType_id = (TextView) findViewById(R.id.tv_edit_businesstype_id);
        tv_association_id = (TextView) findViewById(R.id.tv_edit_association_id);
       // tv_position_id = (TextView) findViewById(R.id.tv_edit_position_id);




        fab_edit = (FloatingActionButton) findViewById(R.id.fab_edit);

        fab_save = (FloatingActionButton) findViewById(R.id.fab_save);
        fab_save.setEnabled(false);


        fab_cancel = (FloatingActionButton) findViewById(R.id.fab_cancel);


        //set onclick listener
        fab_edit.setOnClickListener(this);
        fab_save.setOnClickListener(this);
        fab_cancel.setOnClickListener(this);



        String firstname = getIntent().getStringExtra("fnames");
        String surname = getIntent().getStringExtra("lnames");
        String othername = getIntent().getStringExtra("othernames");
        String dob = getIntent().getStringExtra("dob");
        String phone = getIntent().getStringExtra("phoneno");
        String nationalId = getIntent().getStringExtra("id");
        //String myassocID = getIntent().getStringExtra("myassocid");
        String assoc  =  getIntent().getStringExtra("assocID");

        gender = getIntent().getStringExtra("gender");
      //  bsntype = getIntent().getStringExtra("bsntype");
        //position =  getIntent().getStringExtra("position");
        regionname =  getIntent().getStringExtra("regionname");
        villagename =  getIntent().getStringExtra("village");
        districtname =  getIntent().getStringExtra("districtname");
        wardname =  getIntent().getStringExtra("wardname");
        assocname =  getIntent().getStringExtra("assocname");


        regionId =  getIntent().getStringExtra("regionID");
        districtId =  getIntent().getStringExtra("districtID");
        wardId =  getIntent().getStringExtra("wardID");
        villageId =  getIntent().getStringExtra("villageId");
       // businessType_id =  getIntent().getStringExtra("bsntypeID");
        association_id =  getIntent().getStringExtra("association_id");
      //  position_id =  getIntent().getStringExtra("positionID");



        farmer_firstname.setText(firstname);
        farmer_surname.setText(surname);
        othernames.setText(othername);
        //farmer_dob.setText(dob);
        farmer_phone.setText(phone);
        farmer_id.setText(nationalId);
      //  myAssoc_id.setText(myassocID);


        //load spinners
         /*-----------------
        ------------------------------------------------------*/
        //loadBusinessSpinner();
        loadDistrictSpinner();
        loadWardSpinner();
        loadRegionSpinner();
        //loadPositionSpinner();
        loadVillageSpinner();
        populateGenderSpinner();
        loadAssociationsSpinner();
        /*-----------------
        ------------------------------------------------------*/



    }


    //Method to enable Edittexts
    private void enableEditTextEditing(){

//        farmer_id.setEnabled(true);
        farmer_firstname.setEnabled(true);
        farmer_surname.setEnabled(true);
        othernames.setEnabled(true);
        //farmer_dob.setEnabled(true);
        farmer_phone.setEnabled(true);
        spinner_gender.setEnabled(true);
        spinner_gender.setEnabled(true);
       // myAssoc_id.setEnabled(true);

        spinner_district.setEnabled(true);
        spinner_region.setEnabled(true);
        spinner_ward.setEnabled(true);
        spinner_village.setEnabled(true);
       // spinner_businessTYpe.setEnabled(true);
        //spinner_position.setEnabled(true);
        spinner_assoc.setEnabled(true);

        fab_save.setEnabled(true);
    }//end method


    //Method to disable Edittexts
    private void disableEditTextEditing(){

       // farmer_id.setEnabled(false);
        farmer_firstname.setEnabled(false);
        farmer_surname.setEnabled(false);
        othernames.setEnabled(false);
       // farmer_dob.setEnabled(false);
        farmer_phone.setEnabled(false);
        spinner_gender.setEnabled(false);
        spinner_gender.setEnabled(false);
       // myAssoc_id.setEnabled(false);

        spinner_district.setEnabled(false);
        spinner_region.setEnabled(false);
        spinner_ward.setEnabled(false);
        spinner_village.setEnabled(false);
//        spinner_businessTYpe.setEnabled(false);
//        spinner_position.setEnabled(false);
        spinner_assoc.setEnabled(false);

        fab_save.setEnabled(false);
    }//end method


    public void validateSpinners(){
        String regionId = ((RegionSpinnerController) spinner_region.getSelectedItem()).getDatabase_id();
        String districtId = ((DistrictSpinnerController) spinner_district.getSelectedItem()).getDatabase_id();
        String villageId = ((VillageSpinnerController) spinner_village.getSelectedItem()).getDatabase_id();
        String wardId = ((WardSpinnerController) spinner_ward.getSelectedItem()).getDatabase_id();
       // String bsnTypeId = ((BusinessTypeSpinnerController) spinner_businessTYpe.getSelectedItem()).getDatabase_id();
        String associationId = ((AssociationSpinnerController) spinner_assoc.getSelectedItem()).getDatabase_id();
       // String positionId = ((PositionSpinnerController) spinner_position.getSelectedItem()).getDatabase_id();

        tv_regionId.setText(regionId.toString());
        tv_districtId.setText(districtId.toString());
        tv_villageId.setText(villageId.toString());
        tv_wardId.setText(wardId.toString());
       // tv_businessType_id.setText(bsnTypeId.toString());
        tv_association_id.setText(associationId.toString());
       // tv_position_id.setText(positionId.toString());

    }

    //TODO method to get the time and date
    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

//    //sdet the calendar dialog
//    public void setCalendar() {
//
//        Calendar newcal = Calendar.getInstance();
//
//        dateDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                dateCalendarPicker = Calendar.getInstance();
//                dateCalendarPicker.set(year, monthOfYear, dayOfMonth);
//                farmer_dob.setText(formatter.format(dateCalendarPicker.getTime()));
//            }
//        }, newcal.get(Calendar.YEAR),
//                newcal.get(Calendar.MONTH),
//                newcal.get(Calendar.DAY_OF_MONTH));
//
//
//    }

    //method to update the record
    private void updateFarmerRecord(){
        try{
            dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(!validatefield()){
            return;
        }

//        if(!validateDOB()){return;}

        FarmerUpdateModel model = new FarmerUpdateModel();
        model.seteNationalId(farmer_id.getText().toString().trim());
        //todo seteAssociationfield
       model.seteAssociationId(tv_association_id.getText().toString().trim());
        model.seteFirstname(farmer_firstname.getText().toString().trim());
        model.seteSurname(farmer_surname.getText().toString().trim());
        model.seteOthernames(othernames.getText().toString().trim());
//        model.seteDob(farmer_dob.getText().toString());
        model.setePhoneno(farmer_phone.getText().toString());
        model.seteGender(gender);


        model.seteRegion(tv_regionId.getText().toString());
        model.seteDistrict(tv_districtId.getText().toString());
        model.seteVillage(tv_villageId.getText().toString());
        model.seteWard(tv_wardId.getText().toString());
//        model.seteBusinesstype(tv_businessType_id.getText().toString());

        //todo seteAssociationfield
        model.seteAssociationId(tv_association_id.getText().toString());
//        model.setePosition(tv_position_id.getText().toString());

        dbHandler.updateFarmers(model);



            //Toast.makeText(context, "Twende Nayo!!", Toast.LENGTH_SHORT).show();
            disableEditTextEditing();



    }//end method


    private boolean validatefield(){
        if( farmer_firstname.getText().toString().isEmpty() || farmer_surname.getText().toString().isEmpty() ||
                farmer_surname.getText().toString().isEmpty() || othernames.getText().toString().isEmpty() ||
                farmer_phone.getText().toString().isEmpty()){

            errorAlertDialog();


            return false;

        }else{
            return true;
        }
    }


//    /*-----Validate dates of Birth-----*/
//    public boolean validateDOB(){
//        SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
//
//        String currentDate = this.getDateTime();
//
//
//        try {
//           // String farmerDOB = farmer_dob.getText().toString();
//
//            Date date1;
//            date1 = dates.parse(currentDate);
//            Date date2;
//            date2 = dates.parse(farmerDOB);
//
//
//            long difference = Math.abs(date1.getTime()-date2.getTime());
//            long differenceDates = difference / (24 * 60 * 60 * 1000) / 365;//get differnce in years
//
//            //Convert long to String
//            // String dayDifference = Long.toString(differenceDates);
//
//            Log.e("HERE","HERE: " + differenceDates);
//
//            if(differenceDates < 18){
//                tv_dob.setError(getString(R.string.err_dob));
//                requestFocus(farmer_dob);
//               // Toast.makeText(getApplicationContext(),"Disclaimer!You are too young to drive",Toast.LENGTH_LONG).show();
//                return false;
//            }else{
//                tv_dob.setError(null);
//                //return true;
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return true;
//
//    }//end fn



    /*--Request edit text focus*/
    private void requestFocus(View view){
        if(view.requestFocus()){
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }//end fn

    //save dialog method
    private void saveDialog(){
        //super.onBackPressed();
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("EXIT")
                .setMessage("Are you sure you want to update this record?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        validateSpinners();
                        updateFarmerRecord();


                        Toast.makeText(context,"Data Update Success",Toast.LENGTH_LONG).show();
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

    //Display error dialog to the users --- Error dialog method
    private void errorAlertDialog(){
        //super.onBackPressed();
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Detected Empty fields.\n' Populate All fields and try Again!")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }


    //get spinner index

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }


    private void populateGenderSpinner() {
        List<String> list = new ArrayList<>();
        list.add("Male");
        list.add("Female");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(dataAdapter);

        if(gender.toString().toUpperCase().equals("MALE")){
            spinner_gender.setSelection(0);
        }else{
            spinner_gender.setSelection(1);
            //Toast.makeText(getApplicationContext(),"GENDER IS --- "+ gender.toString(),Toast.LENGTH_LONG).show();
        }
    }

    //method to populate  spinner
    private void loadRegionSpinner() {

        //spinner drop down
        List<RegionSpinnerController> data = dbHandler.getAllRegions();

        //  data.add()
        //create adapter for spiner
        ArrayAdapter<RegionSpinnerController> adapter = new
                ArrayAdapter<RegionSpinnerController>(this,
                android.R.layout.simple_spinner_item, data);

        //int spinnerpos = adapter.getPosition(edCounty);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_region.setAdapter(adapter);
        spinner_region.setSelection(getIndex(spinner_region, regionname));

    }


    //method to populate  spinner
    private void loadWardSpinner() {

        //spinner drop down
        List<WardSpinnerController> data = dbHandler.getWard();

        //  data.add()
        //create adapter for spiner
        ArrayAdapter<WardSpinnerController> adapter = new
                ArrayAdapter<WardSpinnerController>(this,
                android.R.layout.simple_spinner_item, data);

        //int spinnerpos = adapter.getPosition(edCounty);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_ward.setAdapter(adapter);
        spinner_ward.setSelection(getIndex(spinner_ward, wardname));
    }


    //method to populate  spinner
    private void loadDistrictSpinner() {

        //spinner drop down
        List<DistrictSpinnerController> data = dbHandler.getDistricts();

        //  data.add()
        //create adapter for spiner
        ArrayAdapter<DistrictSpinnerController> adapter = new
                ArrayAdapter<DistrictSpinnerController>(this,
                android.R.layout.simple_spinner_item, data);

        //int spinnerpos = adapter.getPosition(edCounty);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_district.setAdapter(adapter);
        spinner_district.setSelection(getIndex(spinner_district, districtname));
    }


    //method to populate  spinner
    private void loadVillageSpinner() {

        //spinner drop down
        List<VillageSpinnerController> data = dbHandler.getVillages();

        //  data.add()
        //create adapter for spiner
        ArrayAdapter<VillageSpinnerController> adapter = new
                ArrayAdapter<VillageSpinnerController>(this,
                android.R.layout.simple_spinner_item, data);

        //int spinnerpos = adapter.getPosition(edCounty);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_village.setAdapter(adapter);
        spinner_village.setSelection(getIndex(spinner_village, villagename));
    }


    //method to populate  spinner
//    private void loadPositionSpinner() {
//
//        //spinner drop down
//        List<PositionSpinnerController> data = dbHandler.getAllPositions();
//
//        //  data.add()
//        //create adapter for spiner
//        ArrayAdapter<PositionSpinnerController> adapter = new
//                ArrayAdapter<PositionSpinnerController>(this,
//                android.R.layout.simple_spinner_item, data);
//
//        //int spinnerpos = adapter.getPosition(edCounty);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner_position.setAdapter(adapter);
//        spinner_position.setSelection(getIndex(spinner_position, position));
//    }
    //method to populate  spinner
//    private void loadBusinessSpinner() {
//
//        //spinner drop down
//        List<BusinessTypeSpinnerController> data = dbHandler.getAllBusinessType();
//
//        //  data.add()
//        //create adapter for spiner
//        ArrayAdapter<BusinessTypeSpinnerController> adapter = new
//                ArrayAdapter<BusinessTypeSpinnerController>(this,
//                android.R.layout.simple_spinner_item, data);
//
//        //int spinnerpos = adapter.getPosition(edCounty);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner_businessTYpe.setAdapter(adapter);
//        spinner_businessTYpe.setSelection(getIndex(spinner_businessTYpe, bsntype));
//    }

    //method to populate  spinner
    private void loadAssociationsSpinner() {

        //spinner drop down
        List<AssociationSpinnerController> data = dbHandler.getAllAssociations();

        //  data.add()
        //create adapter for spiner
        ArrayAdapter<AssociationSpinnerController> adapter = new
                ArrayAdapter<AssociationSpinnerController>(this,
                android.R.layout.simple_spinner_item, data);

        //int spinnerpos = adapter.getPosition(edCounty);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_assoc.setAdapter(adapter);
        spinner_assoc.setSelection(getIndex(spinner_assoc, assocname));
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_edit:
                enableEditTextEditing();
                break;

            case R.id.fab_save:
                saveDialog();
                break;



        }

    }
}
