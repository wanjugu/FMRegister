package com.advantech.mobile.ubiasoko.view;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.AssociationSpinnerController;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.model.TradersDemographicsEventHandler;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TraderDemoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextInputLayout tv_firstname,tvsurname,tv_othernames,tv_mobileno,tv_businessname;
    EditText txt_firstname,txt_surname,txt_othernames,txt_mobileno,txt_businessname;
    RadioGroup btngender;
    RadioButton rd_male,rd_female;
    Spinner spinner_countrycodes;
    Spinner spinner_association;

    FloatingActionButton btnNext;

    DBHandler dbHandler;



    //date formatter
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    //date picker
    DatePickerDialog dateDatePickerDialog;
    Calendar dateCalendarPicker;

    //initialize the View pager
    RegisterTraderViewPager registerTraderViewPager;


    //eventbus for fragment data passing
    EventBus bus = EventBus.getDefault();


    //get the country code
    String countryCode;


    //International formated phone number
    String phone_international_format;


    public TraderDemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.traders_demo_fragment, container, false);

//        tv_nationalID = (TextInputLayout) view.findViewById(R.id.input_national_id);
//        txt_nationalID = (EditText) view.findViewById(R.id.txt_national_id);
//        txt_nationalID.addTextChangedListener(new MyTextWatcher(txt_nationalID));

        spinner_association = (Spinner) view.findViewById(R.id.spinner_association);
        spinner_association.setOnItemSelectedListener(this);

        tv_firstname = (TextInputLayout) view.findViewById(R.id.input_first_name);
        txt_firstname = (EditText) view.findViewById(R.id.txt_first_name);
        txt_firstname.addTextChangedListener(new MyTextWatcher(txt_firstname));

        tvsurname = (TextInputLayout) view.findViewById(R.id.input_surname);
        txt_surname = (EditText) view.findViewById(R.id.txt_surname);
        txt_surname.addTextChangedListener(new MyTextWatcher(txt_surname));

        tv_othernames = (TextInputLayout) view.findViewById(R.id.input_othername);
        txt_othernames = (EditText) view.findViewById(R.id.txt_othername);
        txt_othernames.addTextChangedListener(new MyTextWatcher(txt_othernames));

        tv_businessname = (TextInputLayout) view.findViewById(R.id.input_businessname);
        txt_businessname = (EditText) view.findViewById(R.id.txt_businessname);
        txt_businessname.addTextChangedListener(new MyTextWatcher(txt_businessname));


//        tv_DOB = (TextInputLayout) view.findViewById(R.id.input_dob);
//        txt_dob = (EditText) view.findViewById(R.id.txt_dob);
//        txt_dob.setText(this.getDateTime());
//        txt_dob.setFocusable(false);//disable focus
//        if (savedInstanceState != null) {
//            dateCalendarPicker = Calendar.getInstance();
//            if (savedInstanceState.getLong("dateCalendar") != 0)
//                dateCalendarPicker.setTime(new Date(savedInstanceState
//                        .getLong("dateCalendar")));
//        }
//
//        txt_dob.setOnClickListener(this);
//        setCalendar();


        spinner_countrycodes = (Spinner)view.findViewById(R.id.spinner_countrycodes);
        tv_mobileno = (TextInputLayout) view.findViewById(R.id.input_phoneno);
        txt_mobileno = (EditText) view.findViewById(R.id.txt_phoneno);
        txt_mobileno.addTextChangedListener(new MyTextWatcher(txt_mobileno));

        btngender = (RadioGroup) view.findViewById(R.id.gender);
        rd_male = (RadioButton) view.findViewById(R.id.radio_male);
        rd_male.setChecked(true);
        rd_female = (RadioButton) view.findViewById(R.id.radio_female);


        //populate the country codes from string array
        populateCountryCodes();

        btnNext = (FloatingActionButton) view.findViewById(R.id.fab_trader_next);
        btnNext.setOnClickListener(this);

        //initialize viewpager activit
        registerTraderViewPager = (RegisterTraderViewPager) getActivity();


        //set spinner onClickListener
        spinner_countrycodes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //set the country code
                String cce = (String) adapterView.getItemAtPosition(position);
                countryCode = removeCharAt(cce,0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loadAssociationSpinner();
       dbHandler = new DBHandler(getContext().getApplicationContext());


        return view;
    }

    //method to populate spinner
    private void loadAssociationSpinner() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<AssociationSpinnerController> data = dbHandler.getAllAssociations();
        //create adapter for spiner
        ArrayAdapter<AssociationSpinnerController> adapter= new
                ArrayAdapter<AssociationSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_association.setAdapter(adapter);
    }

    private boolean validateAssociationpinner(){
        if(spinner_association.getCount() == 0 ){
            Toast.makeText(getActivity(),"Missing Entry!Please Provide Association details",Toast.LENGTH_LONG).show();
            return false;
        }
        else{ return true;}
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        clearTextFields(registerTraderViewPager.clearText);
    }
    //method to clear Edittext
    public void clearTextFields(boolean status){
        status = registerTraderViewPager.clearText;

        if(status == true) {


            //txt_nationalID.setText("");
            txt_firstname.setText("");
            txt_surname.setText("");
            txt_othernames.setText("");
            txt_mobileno.setText("");
            txt_businessname.setText("");
            //  Toast.makeText(getActivity(),"STATUS!!"+status,Toast.LENGTH_LONG).show();
        }


    }


    //populate phone code spinner
    private void populateCountryCodes(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(),R.array.ea_country_codes, R.layout.spinner_item
        );


        //specift the layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter
        spinner_countrycodes.setAdapter(adapter);
    }

    //sdet the calendar dialog
//    public void setCalendar() {
//
//        Calendar newcal = Calendar.getInstance();
//
//        dateDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                dateCalendarPicker = Calendar.getInstance();
//                dateCalendarPicker.set(year, monthOfYear, dayOfMonth);
//                txt_dob.setText(formatter.format(dateCalendarPicker.getTime()));
//            }
//        },newcal.get(Calendar.YEAR),
//                newcal.get(Calendar.MONTH),
//                newcal.get(Calendar.DAY_OF_MONTH));
//
//
//    }


    /*...........Validate the inputed phone number...........................
   .....................................................................*/
    public boolean isValidPhoneNumber(CharSequence phoneNo){
        if(!TextUtils.isEmpty(phoneNo)){
            return Patterns.PHONE.matcher(phoneNo).matches();
        }
        return  false;
    }

//    /*-------validate text fields---------*/
//    private boolean validateNationalId(){
//        if(txt_nationalID.getText().toString().trim().isEmpty() ||
//                txt_nationalID.getText().toString().trim().length() < 4){
//            txt_nationalID.setError(getString(R.string.err_national_id));
//            requestFocus(txt_nationalID);
//            return false;
//        }else{
//            txt_nationalID.setError(null);
//        }
//        return true;
//    }

      /*/ 1. phone number validate--with textInputLayoutr*/

    private boolean validatePhoneNo(){
        if(!validatePhoneNumberonBtnClick()){
            txt_mobileno.setError(getString(R.string.err_phoneno));
            requestFocus(txt_mobileno);
            return false;
        }else{
            txt_mobileno.setError(null);
        }
        return true;
    }

    private boolean validateFirstName(){
        if(txt_firstname.getText().toString().trim().isEmpty() ||
                txt_firstname.getText().toString().trim().length() < 3){
            txt_firstname.setError(getString(R.string.err_firstname));
            requestFocus(txt_firstname);
            return false;
        }else{
            txt_firstname.setError(null);
        }
        return true;
    }

    private boolean validateSurname(){
        if(txt_surname.getText().toString().trim().isEmpty() ||
                txt_surname.getText().toString().trim().length() < 3){
            txt_surname.setError(getString(R.string.err_surname));
            requestFocus(txt_surname);
            return false;
        }else{
            txt_surname.setError(null);
        }
        return true;
    }


    private boolean validateOtherNames(){
        if(txt_othernames.getText().toString().trim().isEmpty() ||
                txt_othernames.getText().toString().trim().length() < 2){
            txt_othernames.setError(getString(R.string.err_othernames));
            requestFocus(txt_othernames);
            return false;
        }else{
            txt_othernames.setError(null);
        }
        return true;
    }

    private boolean validateBusinessName(){
        if(txt_businessname.getText().toString().trim().isEmpty() ||
                txt_businessname.getText().toString().trim().length() < 3){
            txt_businessname.setError(getString(R.string.err_bsnname));
            requestFocus(txt_businessname);
            return false;
        }else{
            txt_firstname.setError(null);
        }
        return true;
    }


//    public boolean validateDOB(){
//        SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
//
//        String currentDate = this.getDateTime();
//
//
//        try {
//            String farmerDOB = txt_dob.getText().toString();
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
//                tv_DOB.setError(getString(R.string.err_dob));
//                requestFocus(txt_dob);
//                Toast.makeText(getActivity(),"Disclaimer!You are too young to drive",Toast.LENGTH_LONG).show();
//                return false;
//            }else{
//                tv_DOB.setError(null);
//                //return true;
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return true;
//
//    }//end fn



    private boolean validateUsing_lib(String countryCode, String phone_Number){
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode));
        Phonenumber.PhoneNumber phoneNumber = null;

        try{
            phoneNumber =phoneNumberUtil.parse(phone_Number,isoCode);

        }catch (NumberParseException e){
            System.err.println(e);
        }

        boolean isValid = phoneNumberUtil.isValidNumber(phoneNumber);
        if(isValid){
            String internationalFormat = phoneNumberUtil.format(phoneNumber,PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            //Toast.makeText(getActivity(),"Phone Number is valid "+ internationalFormat,Toast.LENGTH_LONG).show();
            phone_international_format = internationalFormat;
            return  true;
        }else{
            // Toast.makeText(getActivity(),"phone Number is invalid " + phoneNumber,Toast.LENGTH_LONG).show();
            return false;
        }

    }//end method


    //custom merthod to validate phone inputs
    public boolean validatePhoneNumberonBtnClick(){
        String phonenumber = txt_mobileno.getText().toString().trim();
        if(countryCode.length() > 0 && phonenumber.length() >0 ){
            if(isValidPhoneNumber(phonenumber)){
                boolean status = validateUsing_lib(countryCode,phonenumber);

                if(status){
                    //Toast.makeText(getActivity(),"Valid Phone number:: (libphonenumber)",Toast.LENGTH_LONG).show();

                    return true;
                }else{
                    //Toast.makeText(getActivity(),"InValid Phone number::  (libphonenumber)",Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            else{
                //Toast.makeText(getActivity(),"InValid Phone number::(isValidPhoneNumber)",Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else{
            // Toast.makeText(getActivity(),"Phone Nubber input is required",Toast.LENGTH_LONG).show();
            return false;

        }

    }//end custom method

    /*...........phone Validatation ends here...........................
       .....................................................................*/


    //hod to get the current time and date
    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /*--Request edit text focus*/
    private void requestFocus(View view){
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }//end fn

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
               // case R.id.txt_national_id:
                  //  break;
//
                case R.id.txt_first_name:
                case R.id.txt_surname:
                case R.id.txt_othername:
                case R.id.txt_businessname:

            }


        }
    }//end the inner class

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_trader_next:
                //goto next tab

//                if(!validateNationalId()){
//                    return;
//                }

                if(!validateFirstName()){
                    return;
                }

                if(!validateSurname()){
                    return;
                }

                if(!validateOtherNames()){
                    return;
                }

                if(!validateBusinessName()){
                    return;
                }


                if(!validatePhoneNo()){
                    // Toast.makeText(registerFarmerViewPager, "Invalid Phone No:: ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(!validateAssociationpinner()){
                    return;
                }


                //get farmer genser
                int selectedId = btngender.getCheckedRadioButtonId();
                String gender = "";

                //which radio button is checked
                if(selectedId == rd_male.getId()){
                    gender = "Male";
                }else if(selectedId == rd_female.getId()){
                    gender = "Female";
                }else{
                    Toast.makeText(getActivity(),"Gender not selected",Toast.LENGTH_LONG).show();
                    return;
                }

                String associationid = ((AssociationSpinnerController) spinner_association.getSelectedItem()).getDatabase_id();
                String associationname = String.valueOf(spinner_association.getSelectedItem());

                 /*send tte event to all the subscribers*/
                bus.postSticky(new TradersDemographicsEventHandler(

                        txt_firstname.getText().toString(),
                        txt_surname.getText().toString(),
                        txt_othernames.getText().toString(),
                        phone_international_format,
                        gender,txt_businessname.getText().toString(),associationid,associationname));


                registerTraderViewPager.setCurrentItem(1, true);
                //activate/highlight  the tab as active
                registerTraderViewPager.activateTab();
                break;

//            case R.id.txt_dob:
//                dateDatePickerDialog.show();
//                break;
        }
    }



}
