package com.advantech.mobile.ubiasoko.model.TradersView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.controller.DistrictSpinnerController;
import com.advantech.mobile.ubiasoko.controller.RegionSpinnerController;
import com.advantech.mobile.ubiasoko.controller.VillageSpinnerController;
import com.advantech.mobile.ubiasoko.controller.WardSpinnerController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by ADVANTECH CONSULTING on 5/29/2018.
 */

public class TradersDetailedView extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout tv_firstname,tv_surname,tv_othernames,tv_mobileno;
    TextInputLayout tv_business_name;

    //TextView trader_id;
    TextView trader_firstname,trader_surname,trader_othernames,trader_mobile;
    TextView trader_business;

    Spinner spinner_region,spinner_district,spinner_ward,spinner_village,spinner_gender;
   // MultiSpinner spinner_commodity;

    TextView tv_regionId, tv_districtId, tv_villageId, tv_wardId, tv_genderId;
    String regionId, districtId, villageId, wardId, genderId;
    String districtname,wardname,villagename,regionname,gender;

    //List<ProductSpinnerController> product_adapter;
  //  String builder_products;

    FloatingActionButton fab_save,fab_edit,fab_cancel;

    DBHandler dbHandler;
    Context context;

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    DatePickerDialog dateDatePickerDialog;
    Calendar dateCalendarPicker;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trader_detailed_view_activity);

        context = this.getApplicationContext();
        dbHandler = new DBHandler(context);

//        trader_id = (TextView) findViewById(R.id.txt_nationalID);
//        trader_id.setEnabled(false);
//        tv_nationalID = (TextInputLayout) findViewById(R.id.tv_nationalID);
//        trader_id.addTextChangedListener(new MyTextWatcher(tv_nationalID));

        trader_firstname = (TextView) findViewById(R.id.txt_firstName);
        trader_firstname.setEnabled(false);
        tv_firstname = (TextInputLayout) findViewById(R.id.tv_firstname);
        trader_firstname.addTextChangedListener(new MyTextWatcher(tv_firstname));

        trader_surname = (TextView) findViewById(R.id.txt_surname);
        trader_surname.setEnabled(false);
        tv_surname = (TextInputLayout) findViewById(R.id.tv_surname);
        trader_surname.addTextChangedListener(new MyTextWatcher(tv_surname));


        trader_othernames = (TextView) findViewById(R.id.txt_othernames);
        trader_othernames.setEnabled(false);
        tv_othernames = (TextInputLayout) findViewById(R.id.tv_othernames);
        trader_othernames.addTextChangedListener(new MyTextWatcher(tv_othernames));

//        trader_dob = (TextView) findViewById(R.id.txt_dob);
//        trader_dob.setEnabled(false);
//
//        trader_dob.setFocusable(false);//disable focus
//        tv_dob = (TextInputLayout) findViewById(R.id.tv_dob);
//        trader_dob.addTextChangedListener(new MyTextWatcher(tv_dob));
//
//        if (savedInstanceState != null) {
//            dateCalendarPicker = Calendar.getInstance();
//            if (savedInstanceState.getLong("dateCalendar") != 0)
//                dateCalendarPicker.setTime(new Date(savedInstanceState
//                        .getLong("dateCalendar")));
//        }
//
//        trader_dob.setOnClickListener(this);
//        setCalendar();

        trader_mobile = (TextView) findViewById(R.id.txt_phoneno);
        trader_mobile.setEnabled(false);
        tv_mobileno = (TextInputLayout) findViewById(R.id.tv_phoneno);
        trader_mobile.addTextChangedListener(new MyTextWatcher(tv_mobileno));

        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        spinner_gender.setEnabled(false);
        spinner_region = (Spinner) findViewById(R.id.spinner_region);
        spinner_region.setEnabled(false);
        spinner_district = (Spinner) findViewById(R.id.spinner_district);
        spinner_district.setEnabled(false);
        spinner_ward = (Spinner) findViewById(R.id.spinner_wardid);
        spinner_ward.setEnabled(false);
        spinner_village = (Spinner) findViewById(R.id.spinner_village);
        spinner_village.setEnabled(false);
//
//        more_loc_info = (TextView) findViewById(R.id.txt_additional_loc_info);
//        more_loc_info.setEnabled(false);
//        tv_more_loc_info = (TextInputLayout) findViewById(R.id.tv_additional_loc_info);
//        more_loc_info.addTextChangedListener(new MyTextWatcher(tv_more_loc_info));

        trader_business = (TextView) findViewById(R.id.txt_businesname);
        trader_business.setEnabled(false);
        tv_business_name = (TextInputLayout) findViewById(R.id.tv_businessname);
        trader_business.addTextChangedListener(new MyTextWatcher(tv_business_name));
//
//        trader_telno = (TextView) findViewById(R.id.txt_telno);
//        trader_telno.setEnabled(false);
//        tv_phone2no = (TextInputLayout) findViewById(R.id.tv_telno);
        //trader_mobile.addTextChangedListener(new MyTextWatcher(tv_phone2no));

//        spinner_commodity = (MultiSpinner) findViewById(R.id.spinner_commodities);
//        spinner_commodity.setEnabled(false);
//
//        spinner_bsntype = (Spinner) findViewById(R.id.spinner_bsntype);
//        spinner_bsntype.setEnabled(false);

// TextView tv_regionId,tv_districtId,tv_villageId,tv_wardId,tv_genderId,tv_bsntypeID;
        tv_regionId = (TextView) findViewById(R.id.tv_edit_region_id);
        tv_districtId = (TextView) findViewById(R.id.tv_edit_district_id);
        tv_villageId = (TextView) findViewById(R.id.tv_edit_village_id);
        tv_wardId = (TextView) findViewById(R.id.tv_edit_ward_id);
       // tv_bsntypeID = (TextView) findViewById(R.id.tv_edit_businesstype_id);
        tv_genderId = (TextView) findViewById(R.id.tv_edit_gender);


        fab_edit = (FloatingActionButton) findViewById(R.id.fab_edit);

        fab_save = (FloatingActionButton) findViewById(R.id.fab_save);
        fab_save.setEnabled(false);


        fab_cancel = (FloatingActionButton) findViewById(R.id.fab_cancel);


        //set onclick listener
        fab_edit.setOnClickListener(this);
        fab_save.setOnClickListener(this);
        fab_cancel.setOnClickListener(this);


        //get databundles
       // String nationalId = getIntent().getStringExtra("nationalid");
        String firstname = getIntent().getStringExtra("firstname");
        String surname = getIntent().getStringExtra("surname");
        String othername = getIntent().getStringExtra("othernames");
        //String dob = getIntent().getStringExtra("DOB");
        String phone = getIntent().getStringExtra("phone");
        String businessname = getIntent().getStringExtra("businessname");
        String telphone_no = getIntent().getStringExtra("telephoneno");
      //  String more_loc_info_text  = getIntent().getStringExtra("morelocinfo");

        gender = getIntent().getStringExtra("gender");
        regionname = getIntent().getStringExtra("region");
        districtname  = getIntent().getStringExtra("district");
        wardname  = getIntent().getStringExtra("ward");
        villagename  = getIntent().getStringExtra("village");
        //Todo save the correct business type
       // bsntype  = getIntent().getStringExtra("businesstype");

        regionId = getIntent().getStringExtra("tv_trader_regionID");
        districtId = getIntent().getStringExtra("tv_trader_districtID");
        wardId = getIntent().getStringExtra("tv_trader_wardID");
        villageId = getIntent().getStringExtra("tv_trader_villageID");
        //genderId = getIntent().getStringExtra("");
       // bsntypeID = getIntent().getStringExtra("tv_trader_businessTypeID");


       // trader_id.setText(nationalId);
        trader_firstname.setText(firstname);
        trader_surname.setText(surname);
        trader_othernames.setText(othername);
        //trader_dob.setText(dob);
        trader_mobile.setText(phone);
        trader_business.setText(businessname);
       // trader_telno.setText(telphone_no);
      //  more_loc_info.setText(more_loc_info_text);

        //Load spinners
        //load spinners
         /*-----------------
        ------------------------------------------------------*/
      // loadBusinessSpinner();
        loadDistrictSpinner();
        loadWardSpinner();
        loadRegionSpinner();
        loadVillageSpinner();
        populateGenderSpinner();
      //  loadProductsSpinner();

        /*-----------------
        ------------------------------------------------------*/

    }//end oncreate()

    /*--Request edit text focus*/
    private void requestFocus(View view){
        if(view.requestFocus()){
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }//end fn
    //validate Mobile, Simple Validation
    private boolean validateMobilePhone(){
        if(trader_mobile.getText().toString().trim().isEmpty() ||
                trader_mobile.getText().toString().trim().length() < 5){
            tv_mobileno.setError(getString(R.string.err_assoc));
            requestFocus(tv_mobileno);
            return false;
        }else{
            trader_mobile.setError(null);
        }
        return true;
    }

    /*Create an inner class */
    private class MyTextWatcher implements TextWatcher {
        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch(view.getId()){
//                case R.id.txt_bsn_name:
//                    validateBsnName();
//                    break;
//
//                case R.id.txt_phoneno:
//                    validateMobilePhone();
//                    break;

            }


        }
    }//end the inner class


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
//    //pass textt o event bus provuded by user
//    private boolean validateBusinessTypeSpinerText(){
//        if( builder_products == null ){
//            Toast.makeText(this,"Missing Entry:Please provide Commodity details",Toast.LENGTH_LONG).show();
//            return false;
//        }
//        else{ return true;}
//    }

//    //method to populate spinner
//    private void loadProductsSpinner() {
//        //database handle
//        dbHandler = new DBHandler(this);
//        //spinner drop down
//        product_adapter = dbHandler.getAllProducts();
//        //create adapter for spiner
//        ArrayAdapter<ProductSpinnerController> adapter= new
//                ArrayAdapter<ProductSpinnerController>(this,
//                R.layout.multi_select_spinner, product_adapter);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_commodity.setAdapter(adapter,false,onSelectedListener2);
//
//    }

//    private MultiSpinner.MultiSpinnerListener onSelectedListener2 = new MultiSpinner.MultiSpinnerListener() {
//        @Override
//        public void onItemsSelected(boolean[] selected) {
//            StringBuilder builder = new StringBuilder();
//
//            for (int i = 0; i < selected.length; i++) {
//                if (selected[i]) {
//                    builder.append(product_adapter.get(i)).append(" ");
//                }
//            }
//
//            builder_products = builder.toString();
//
//            // Toast.makeText(getActivity(), builder_products.toString(), Toast.LENGTH_SHORT).show();
//
//
//        }
//    };


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
//
//    //method to populate  spinner
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
//        spinner_bsntype.setAdapter(adapter);
//        spinner_bsntype.setSelection(getIndex(spinner_bsntype, bsntype));
//    }

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
          //  Toast.makeText(getApplicationContext(),"GENDER IS --- "+ gender,Toast.LENGTH_LONG).show();
        }
    }

//
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
//                trader_dob.setText(formatter.format(dateCalendarPicker.getTime()));
//            }
//        }, newcal.get(Calendar.YEAR),
//                newcal.get(Calendar.MONTH),
//                newcal.get(Calendar.DAY_OF_MONTH));
//
//    }//end onClick()

    //Method to enable Edittexts
    private void enableEditTextEditing(){
        trader_firstname.setEnabled(true);
       // trader_id.setEnabled(true);
        trader_surname.setEnabled(true);
        trader_othernames.setEnabled(true);
       // trader_dob.setEnabled(true);

        trader_mobile.setEnabled(true);
       // more_loc_info.setEnabled(true);
        trader_business.setEnabled(true);
      //  trader_telno.setEnabled(true);
        spinner_region.setEnabled(true);
        spinner_district.setEnabled(true);
        spinner_ward.setEnabled(true);
        spinner_village.setEnabled(true);
       // spinner_bsntype.setEnabled(true);
        spinner_gender.setEnabled(true);
      ///  spinner_commodity.setEnabled(true);


        fab_save.setEnabled(true);

    }

    //Method to enable Edittexts
    private void disableEditTextEditing(){
        trader_firstname.setEnabled(false);
      //  trader_id.setEnabled(false);
        trader_surname.setEnabled(false);
        trader_othernames.setEnabled(false);
      //  trader_dob.setEnabled(false);
        trader_mobile.setEnabled(false);
      //  more_loc_info.setEnabled(false);
        trader_business.setEnabled(false);
      //  trader_telno.setEnabled(false);
        spinner_region.setEnabled(false);
        spinner_district.setEnabled(false);
        spinner_ward.setEnabled(false);
        spinner_village.setEnabled(false);
       // spinner_bsntype.setEnabled(false);
        spinner_gender.setEnabled(false);
       // spinner_commodity.setEnabled(false);


        fab_save.setEnabled(false);
    }//end method
    public void validateSpinners(){
        String regionId = ((RegionSpinnerController) spinner_region.getSelectedItem()).getDatabase_id();
        String districtId = ((DistrictSpinnerController) spinner_district.getSelectedItem()).getDatabase_id();
        String villageId = ((VillageSpinnerController) spinner_village.getSelectedItem()).getDatabase_id();
        String wardId = ((WardSpinnerController) spinner_ward.getSelectedItem()).getDatabase_id();
      //  String bsnTypeId = ((BusinessTypeSpinnerController) spinner_bsntype.getSelectedItem()).getDatabase_id();
       // String gender = ((BusinessTypeSpinnerController) spinner_gender.getSelectedItem()).getDatabase_id();

        tv_regionId.setText(regionId.toString());
        tv_districtId.setText(districtId.toString());
        tv_villageId.setText(villageId.toString());
        tv_wardId.setText(wardId.toString());
       // tv_bsntypeID.setText(bsnTypeId.toString());

    }//validate spinner

    //method to update the record
    private void updateRecord() {
        try {
            dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TraderUpdateModel model =new TraderUpdateModel();
       // model.seteTrader_nationalID(trader_id.getText().toString());
        model.seteTrader_phoneno(trader_mobile.getText().toString());
        model.seteTrader_firstname(trader_firstname.getText().toString());
        model.seteTrader_surname(trader_surname.getText().toString());
        model.seteTrader_othername(trader_othernames.getText().toString());
     //   model.seteTrader_dob(trader_dob.getText().toString());
        model.seteTrader_region(tv_regionId.getText().toString());
        model.seteTrader_district(tv_districtId.getText().toString());
        model.seteTrader_ward(tv_wardId.getText().toString());
        model.seteTrader_village(tv_villageId.getText().toString());
       // model.seteTrader_More_loc_info(more_loc_info.getText().toString());
        model.seteTrader_gender(gender);
      //  model.seteTrader_bsntype(bsntype);
      //  model.seteTrader_commodities(builder_products);
      //  model.seteTrade_telno(trader_telno.getText().toString());
        model.seteTrade_bsnname(trader_business.getText().toString());

        dbHandler.updateTrader(model);

       // Toast.makeText(context, "Twende Nayo!!", Toast.LENGTH_SHORT).show();
        disableEditTextEditing();

    }//end updateFarmerRecord()

///todo save updated fiels to database
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
                    updateRecord();


                    Toast.makeText(context,"Data Update Success" ,Toast.LENGTH_LONG).show();
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
        switch (view.getId()) {
//            case R.id.txt_dob:
//                dateDatePickerDialog.show();
//                break;

            case R.id.fab_edit:
                enableEditTextEditing();
                break;


            case R.id.fab_save:
                saveDialog();
                break;
        }

    }
}
