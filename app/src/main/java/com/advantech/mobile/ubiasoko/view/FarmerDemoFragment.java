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
import com.advantech.mobile.ubiasoko.model.FarmerDemographicsEventHandler;
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
public class FarmerDemoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

DBHandler dbHandler;


    //date formatter
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    //date picker
    DatePickerDialog dateDatePickerDialog;
    Calendar dateCalendarPicker;

    //A String to hlod the gender value
    String gender = "";

    //eventbus for fragment data passing
    EventBus bus = EventBus.getDefault();

    //Demographic Tab
    TextInputLayout tv_NationalId,tv_firstname,tv_surname,tv_othernames,tv_dob,tv_mobile;
    EditText txt_firstname,txt_surname,txt_othernames,txt_mobile,txt_phoneno2;
    Spinner spinner_countrycodes;
    Spinner spinner_association;
    RadioButton btnmale,btnfemale;
    RadioGroup btngender;
    //Region Tab
    //OtherInfo Tab
    //floating Buttons
    FloatingActionButton btnNext;
    //initialize the View pager
    RegisterFarmerViewPager registerFarmerViewPager;

    //get the country code
    String countryCode;

    //International formated phone number
    String phone_international_format;



    public FarmerDemoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onResume() {
        super.onResume();
        clearTextFields(registerFarmerViewPager.clearText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_farmer_demo, container, false);

        spinner_association = (Spinner) view.findViewById(R.id.spinner_association);
        spinner_association.setOnItemSelectedListener(this);


        tv_firstname =(TextInputLayout) view.findViewById(R.id.input_first_name);
        txt_firstname =(EditText) view.findViewById(R.id.txt_first_name);
        txt_firstname.addTextChangedListener(new MyTextWatcher(txt_firstname));

        tv_surname =(TextInputLayout) view.findViewById(R.id.input_surname);
        txt_surname =(EditText) view.findViewById(R.id.txt_surname);
        txt_surname.addTextChangedListener(new MyTextWatcher(txt_surname));

        tv_othernames =(TextInputLayout) view.findViewById(R.id.input_othername);
        txt_othernames =(EditText) view.findViewById(R.id.txt_othername);
        txt_othernames.addTextChangedListener(new MyTextWatcher(txt_othernames));


        tv_mobile = (TextInputLayout)view.findViewById(R.id.tv_phone2);
        txt_phoneno2 = (EditText) view.findViewById(R.id.txt_phoneno2);
        txt_phoneno2.addTextChangedListener(new MyTextWatcher(txt_phoneno2));

        spinner_countrycodes = (Spinner) view.findViewById(R.id.spinner_countrycodes);
        //populate the spinner
        populateCountryCodes();

        btngender = (RadioGroup) view.findViewById(R.id.btn_gender);
        btnfemale = (RadioButton) view.findViewById(R.id.radio_female);
        btnmale = (RadioButton) view.findViewById(R.id.radio_male);

        btnNext = (FloatingActionButton) view.findViewById(R.id.fab_next);
        btnNext.setOnClickListener(this);

        //initialize viewpager activit
        registerFarmerViewPager = (RegisterFarmerViewPager) getActivity();

        /*------------Validate the phone number using the google library-----------
        * --1. Get the country code from the  spinner*/
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

        clearTextFields(registerFarmerViewPager.clearText);
        dbHandler = new DBHandler(getContext().getApplicationContext());
        loadAssociationSpinner();

        return view;
    }//end oncreateview method

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

    //method to get the current time and date
    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
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





    /*...........Validate the inputed phone number...........................
    .....................................................................*/
    public boolean isValidPhoneNumber(CharSequence phoneNo){
        if(!TextUtils.isEmpty(phoneNo)){
            return Patterns.PHONE.matcher(phoneNo).matches();
        }
        return  false;
    }

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
        String phonenumber = txt_phoneno2.getText().toString().trim();
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



    /*******Button click listener************
    -- All the button click are handled here*/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_next:
                //goto next tab
                //  registerFarmerViewPager.setCurrentItem(1, true);
                //activate/highlight  the tab as active
                //registerFarmerViewPager.activateTab();
                /*if( validatePhoneNumberonBtnClick()){
                    registerFarmerViewPager.setCurrentItem(1, true);
                    Toast.makeText(registerFarmerViewPager, "You Phone number is: " + phone_international_format, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(registerFarmerViewPager, "Invalid phone number: ", Toast.LENGTH_SHORT).show();
                }*/

//                if(!validateNationalId()){
//                    return;
//                }

                if(!validateFirstName()){
                    return;
                }

                if(!validateAssociationpinner()){
                    return;
                }

                if(!validateSurname()){
                    return;
                }

                if(!validateOtherNames()){
                    return;
                }

//                if(!validateDOB()){
//                    return;
//                }

                if(!validatePhoneNo()){
                   // Toast.makeText(registerFarmerViewPager, "Invalid Phone No:: ", Toast.LENGTH_SHORT).show();
                    return;
                }

                //get farmer genser
                int selectedId = btngender.getCheckedRadioButtonId();
                String gender = "";

                //which radio button is checked
                if(selectedId == btnmale.getId()){
                    gender = "Male";
                }else if(selectedId == btnfemale.getId()){
                    gender = "Female";
                }else{
                    Toast.makeText(getActivity(),"Gender not selected",Toast.LENGTH_LONG).show();
                }

                String associationid = ((AssociationSpinnerController) spinner_association.getSelectedItem()).getDatabase_id();
                String associationname = String.valueOf(spinner_association.getSelectedItem());
                /*send tte event to all the subscribers*/
                bus.postSticky(new FarmerDemographicsEventHandler(
                        txt_firstname.getText().toString(),
                        txt_surname.getText().toString(),
                        txt_othernames.getText().toString(),
                        phone_international_format,
                        gender,associationid,associationname));


                //Toast.makeText(registerFarmerViewPager, "Validate check passed:: ", Toast.LENGTH_SHORT).show();
                //goto next tab
                registerFarmerViewPager.setCurrentItem(1, true);
                //activate/highlight  the tab as active
                registerFarmerViewPager.activateTab();
                break;

            //Handle the Date of birth button
//                case R.id.txt_dob:
//                dateDatePickerDialog.show();
//                break;

        }//end switch
    }//end onclick

///*-----Validate dates of Birth-----*/
//public boolean validateDOB(){
//    SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
//
//    String currentDate = this.getDateTime();
//
//
//    try {
//        String farmerDOB = txt_dob.getText().toString();
//
//        Date date1;
//        date1 = dates.parse(currentDate);
//        Date date2;
//        date2 = dates.parse(farmerDOB);
//
//
//        long difference = Math.abs(date1.getTime()-date2.getTime());
//        long differenceDates = difference / (24 * 60 * 60 * 1000) / 365;//get differnce in years
//
//        //Convert long to String
//       // String dayDifference = Long.toString(differenceDates);
//
//        Log.e("HERE","HERE: " + differenceDates);
//
//        if(differenceDates < 18){
//            tv_dob.setError(getString(R.string.err_dob));
//            requestFocus(txt_dob);
//            Toast.makeText(getActivity(),"Disclaimer!You are too young to drive",Toast.LENGTH_LONG).show();
//            return false;
//        }else{
//            tv_dob.setError(null);
//           //return true;
//        }
//
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }
//    return true;
//
//}//end fn

//
///*-------validate text fields---------*/
//private boolean validateNationalId(){
//    if(txt_nationalId.getText().toString().trim().isEmpty() ||
//            txt_nationalId.getText().toString().trim().length() < 4){
//        txt_nationalId.setError(getString(R.string.err_national_id));
//        requestFocus(txt_nationalId);
//        return false;
//    }else{
//     txt_nationalId.setError(null);
//    }
//    return true;
//}
    /*-------validate other text fields---------
    * 1. phone number validate--with textInputLayoutr*/

    private boolean validatePhoneNo(){
        if(!validatePhoneNumberonBtnClick()){
            txt_phoneno2.setError(getString(R.string.err_phoneno));
            requestFocus(txt_phoneno2);
            return false;
        }else{
            txt_phoneno2.setError(null);
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
private class MyTextWatcher implements TextWatcher{
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
//            case R.id.txt_national_id:
//                validateNationalId();
//                break;

            case R.id.txt_first_name:
                //validateFirstName();
                break;
            case R.id.txt_surname:
               // validateSurname();
                break;
            case R.id.txt_othername:
               // validateOtherNames();
                break;
//            case R.id.txt_dob:
//                validateDOB();
//                break;
        }


    }
}//end the inner class


    //method to clear Edittext
    public void clearTextFields(boolean status){
        status = registerFarmerViewPager.clearText;

    if(status == true) {
        //txt_nationalId.setText("");
        txt_firstname.setText("");
        txt_surname.setText("");
        txt_othernames.setText("");
        txt_phoneno2.setText("");
      //  Toast.makeText(getActivity(),"STATUS!!"+status,Toast.LENGTH_LONG).show();
    }


    }



}//end class


