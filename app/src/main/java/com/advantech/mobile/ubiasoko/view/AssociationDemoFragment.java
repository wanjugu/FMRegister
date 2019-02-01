package com.advantech.mobile.ubiasoko.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.BusinessTypeSpinnerController;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.controller.ProductSpinnerController;
import com.advantech.mobile.ubiasoko.model.AssociationDemographicEventHandler;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.thomashaertel.widget.MultiSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssociationDemoFragment extends Fragment implements View.OnClickListener {

    TextInputLayout tv_associationName,tv_mobileNo;
    EditText txt_assoc_name, txt_regno,txt_mobileno;
    TextInputLayout tv_firstname,tv_surname,tv_othernames;
    EditText txt_firstname,txt_surname,txt_othernames;


    //MultiSpinner spinner_commodities;
    Spinner spinner_CC1;//country codes spinner
    DBHandler dbHandler;
    //List<ProductSpinnerController> product_adapter;
   // List<BusinessTypeSpinnerController> business_adapter;


   // String builder_bsn_type;
   // String builder_products;
    boolean[] selectedItems2;


    FloatingActionButton btnNext;
    //initialize the View pager
    RegisterAssociationViewPager registerAssociationViewPager;

    //get the country code
    String countryCode1;
    String countryCode2;

    //International formated phone number
    String phone_international_format;
    String phone_international_format2;

    EventBus bus= EventBus.getDefault();




    public AssociationDemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.association_demo_fragment, container, false);

        tv_associationName = (TextInputLayout) v.findViewById(R.id.input_assoc_name);
        txt_assoc_name = (EditText) v.findViewById(R.id.txt_assoc_name);
        txt_assoc_name.addTextChangedListener(new MyTextWatcher(txt_assoc_name));

        tv_firstname =(TextInputLayout) v.findViewById(R.id.input_first_name);
        txt_firstname =(EditText) v.findViewById(R.id.txt_first_name);
        txt_firstname.addTextChangedListener(new AssociationDemoFragment.MyTextWatcher(txt_firstname));

        tv_surname =(TextInputLayout) v.findViewById(R.id.input_surname);
        txt_surname =(EditText) v.findViewById(R.id.txt_surname);
        txt_surname.addTextChangedListener(new AssociationDemoFragment.MyTextWatcher(txt_surname));

        tv_othernames =(TextInputLayout) v.findViewById(R.id.input_othername);
        txt_othernames =(EditText) v.findViewById(R.id.txt_othername);
        txt_othernames.addTextChangedListener(new AssociationDemoFragment.MyTextWatcher(txt_othernames));

     //   txt_regno.addTextChangedListener(new MyTextWatcher(txt_regno));

        tv_mobileNo = (TextInputLayout) v.findViewById(R.id.input_phoneno);
        txt_mobileno = (EditText)v.findViewById(R.id.txt_phoneno);
        txt_mobileno.addTextChangedListener(new MyTextWatcher(txt_mobileno));




       // spinner_commodities = (MultiSpinner) v.findViewById(R.id.spinner_commodities);
        spinner_CC1 = (Spinner) v.findViewById(R.id.spinner_countrycodes);
            /*------------Validate the phone number using the google library-----------
        * --1. Get the country code from the  spinner*/
        spinner_CC1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //set the country code
                countryCode1 = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btnNext = (FloatingActionButton) v.findViewById(R.id.fab_assoc_next);
        btnNext.setOnClickListener(this);

        //initialize viewpager activit
        registerAssociationViewPager = (RegisterAssociationViewPager) getActivity();
        dbHandler = new DBHandler(getContext());

        populateCountryCodes();
        //loadBusinessSpinner();
        //loadProductsSpinner();

        clearTextFields(registerAssociationViewPager.clearText);

        return v;
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
                case R.id.txt_assoc_name:
                    //validateAssocName();

                    break;

//
                case R.id.txt_phoneno:
                   //validatePhoneNo();

                    break;
                case R.id.txt_phoneno2:
                    //validatePhoneNo2();

                    break;

            }


        }
    }//end the inner class

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

    private boolean validateUsing_lib2(String countryCode, String phone_Number){
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
            phone_international_format2 = internationalFormat;
            return  true;
        }else{
            // Toast.makeText(getActivity(),"phone Number is invalid " + phoneNumber,Toast.LENGTH_LONG).show();
            return false;
        }

    }//end method

    public boolean validatePhoneNumberonBtnClick(){
        String phonenumber = txt_mobileno.getText().toString().trim();
        if(countryCode1.length() > 0 && phonenumber.length() >0 ){
            if(isValidPhoneNumber(phonenumber)){


                String cce  = removeCharAt(countryCode1,0);

                boolean status = validateUsing_lib(cce,phonenumber);

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

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public boolean validateTelNumberonBtnClick(){
        String phonenumber = txt_mobileno.getText().toString().trim();
        if(countryCode2.length() > 0 && phonenumber.length() >0 ){
            if(isValidPhoneNumber(phonenumber)){
                boolean status = validateUsing_lib2(countryCode2,phonenumber);

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

      /*-------validate other text fields---------
    * 1. phone number validate--with textInputLayoutr*/

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

    private boolean validatePhoneNo2(){
        if(!validateTelNumberonBtnClick()){
            txt_mobileno.setError(getString(R.string.err_phoneno));
            requestFocus(txt_mobileno);
            return false;
        }else{
            txt_mobileno.setError(null);
        }
        return true;
    }


    /*...........phone Validatation ends here...........................
       .....................................................................*/


    /*--Request edit text focus*/
    private void requestFocus(View view){
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }//end fn

    //validate Association name filed
    private boolean validateAssocName(){
        if(txt_assoc_name.getText().toString().trim().isEmpty() ||
                txt_assoc_name.getText().toString().trim().length() < 2){
            txt_assoc_name.setError(getString(R.string.err_assoc));
            requestFocus(txt_assoc_name);
            return false;
        }else{
            txt_assoc_name.setError(null);
        }
        return true;
    }

    //validate Association registration number filed
//    private boolean validateRegNo(){
//        if(txt_regno.getText().toString().trim().isEmpty() ||
//                txt_regno.getText().toString().trim().length() < 2){
//            txt_regno.setError(getString(R.string.err_assoc));
//            requestFocus(txt_regno);
//            return false;
//        }else{
//            txt_assoc_name.setError(null);
//        }
//        return true;
//    }


//    //pass textt o event bus provuded by user
//    private boolean validateBusinessTypeSpinerText(){
//        String business = ((BusinessTypeSpinnerController) spinner_business_type.getSelectedItem()).getDatabase_id();
//        if(business == "" || business == null ){
//            Toast.makeText(getActivity(),"Missing Entry:Please provide Business Type details",Toast.LENGTH_LONG).show();
//            return false;
//        }
//        else{ return true;}
//    }
//    private boolean validateProductSpinner(){
//        if(builder_products == "" || builder_products == null){
//            Toast.makeText(getActivity(),"Missing Entry!Please provide Commodities details",Toast.LENGTH_LONG).show();
//            return false;
//        }
//        else{ return true;}
//    }


    /*-----------------------------------------------------------------------------/
    Load Spinners
     */
//    //method to populate spinner
//    private void loadProductsSpinner() {
//        //database handle
//        dbHandler = new DBHandler(getActivity());
//        //spinner drop down
//        product_adapter = dbHandler.getAllProducts();
//        //create adapter for spiner
//        ArrayAdapter<ProductSpinnerController> adapter= new
//                ArrayAdapter<ProductSpinnerController>(getActivity(),
//                R.layout.multi_select_spinner, product_adapter);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_commodities.setAdapter(adapter,false,onSelectedListener);
//
////        //set a default selected item
//        selectedItems2 = new boolean[adapter.getCount()];
//        selectedItems2[0] = true;
//        spinner_commodities.setSelected(selectedItems2);
//    }
//    private MultiSpinner.MultiSpinnerListener onSelectedListener2 = new MultiSpinner.MultiSpinnerListener() {
//        @Override
//        public void onItemsSelected(boolean[] selected) {
//
//            StringBuilder builder = new StringBuilder();
//
//            for (int i = 0; i < selected.length; i++) {
//                if (selected[i]) {
//                    builder.append(business_adapter.get(i)).append(" ");
//
//                }
//            }
//
//            builder_bsn_type =  builder.toString();
//
//            // Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
//
//
//        }
//    };


//
//    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
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

     /*-----------------------------------------------------------------------------/
    Load Spinners
     */
//     //method to populate spinner
//     private void loadBusinessSpinner() {
//         //database handle
//         dbHandler = new DBHandler(getActivity());
//         //spinner drop down
//         business_adapter = dbHandler.getAllBusinessType();
//         //create adapter for spiner
//         ArrayAdapter<BusinessTypeSpinnerController> adapter= new
//                 ArrayAdapter<BusinessTypeSpinnerController>(getActivity(),
//                 android.R.layout.simple_spinner_item, business_adapter);
//
//         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//         spinner_business_type.setAdapter(adapter);
//
//         //set a default selected item
////       selectedItems = new boolean[adapter.getCount()];
////        selectedItems[0] = true;
////       spinner_business_type.setSelected(selectedItems);
//     }

    //populate phone code spinner
    private void populateCountryCodes(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(),R.array.ea_country_codes, R.layout.spinner_item
        );


        //specift the layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter
        spinner_CC1.setAdapter(adapter);
       // spinner_CC2.setAdapter(adapter);
    }


    //method to clear Edittext
    public void clearTextFields(boolean status){
        status = registerAssociationViewPager.clearText;

        if(status == true) {

            txt_assoc_name.setText("");
            txt_firstname.setText("");
            txt_surname.setText("");
            txt_othernames.setText("");

            //txt_regno.setText("");
            txt_mobileno.setText("");
            txt_mobileno.setText("");

            //  Toast.makeText(getActivity(),"STATUS!!"+status,Toast.LENGTH_LONG).show();
        }


    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fab_assoc_next:
                validateUserInput();

                break;
        }//end switch

    }//end onclick


    public void validateUserInput(){
        if(!validateAssocName()){
            return;
        }


        if(!validateFirstName()){
            return;
        }


        if(!validateSurname()){
            return;
        }

        if(!validateOtherNames()){
            return;
        }


        if(!validatePhoneNo()){
            return;
        }

//        if(!validatePhoneNo2()){
//            return;
//        }
//        if(!validateBusinessTypeSpinerText()){
//            return;
//        }
//
//        if(!validateProductSpinner()){
//            return;
//        }


        //String businessType = ((BusinessTypeSpinnerController) spinner_business_type.getSelectedItem()).getDatabase_id();

         /*send tte event to all the subscribers*/
        bus.postSticky(new AssociationDemographicEventHandler(
                txt_assoc_name.getText().toString(),
                phone_international_format,txt_firstname.getText().toString(),
                txt_surname.getText().toString(),txt_othernames.getText().toString()));

        registerAssociationViewPager.setCurrentItem(1, true);
        //activate/highlight  the tab as active
        registerAssociationViewPager.activateTab();
    }

    @Override
    public void onResume() {
        super.onResume();
        clearTextFields(registerAssociationViewPager.clearText);
    }
}
