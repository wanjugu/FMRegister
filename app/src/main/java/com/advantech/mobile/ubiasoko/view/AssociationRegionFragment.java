package com.advantech.mobile.ubiasoko.view;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.controller.DistrictSpinnerController;
import com.advantech.mobile.ubiasoko.controller.RegionSpinnerController;
import com.advantech.mobile.ubiasoko.controller.WardSpinnerController;
import com.advantech.mobile.ubiasoko.model.AssociationOtherEventHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssociationRegionFragment extends Fragment implements View.OnClickListener {
    Spinner spinner_region,spinner_district;
    //,spinner_ward;
    TextInputLayout tv_GPS;
    EditText txt_gps;

    FloatingActionButton fabPrevious,fabnext;
    RegisterAssociationViewPager registerAssociationViewPager;
    //alert dialog
    AlertDialog alertDialog;
    DBHandler dbHandler;


    /*Variables to help load SPinner onItemSelected*/
    int SELECTED_REGION_ID;
    int SELECTED_DISTRICT_ID;
    int SELECTED_WARD_ID;

    EventBus bus=EventBus.getDefault();





    public AssociationRegionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.association_region_fragment, container, false);

        spinner_region = (Spinner)view.findViewById(R.id.spinner_regionid);

/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/

        spinner_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String region_id = ((RegionSpinnerController)spinner_region.getSelectedItem()).getDatabase_id();
                SELECTED_REGION_ID = Integer.parseInt(region_id);

                //load district spinner
                loadDistricts();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
        spinner_district =(Spinner) view.findViewById(R.id.spinner_district);
         /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/

//        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String district_id = ((DistrictSpinnerController)spinner_district.getSelectedItem()).getDatabase_id();
//                SELECTED_DISTRICT_ID = Integer.parseInt(district_id);
//
//                //load district spinner
//                loadWards();
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
        //spinner_ward = (Spinner) view.findViewById(R.id.spinner_wardid);
          /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/

//        spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//
//                String ward_id = ((WardSpinnerController)spinner_ward.getSelectedItem()).getDatabase_id();
//                SELECTED_WARD_ID = Integer.parseInt(ward_id);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/




        tv_GPS = (TextInputLayout) view.findViewById(R.id.input_gps);


        txt_gps = (EditText) view.findViewById(R.id.zip_gps);
        txt_gps.addTextChangedListener(new MyTextWatcher(txt_gps));

        //Button init
        fabPrevious = (FloatingActionButton) view.findViewById(R.id.fab_farmer_assoc_previous);
        fabnext = (FloatingActionButton) view.findViewById(R.id.fab_farmer_assoc_next);


        //onclick listeners
        fabPrevious.setOnClickListener(this);
        fabnext.setOnClickListener(this);


        //initialize viewpager activity
        registerAssociationViewPager = (RegisterAssociationViewPager) getActivity();
        dbHandler = new DBHandler(getContext());


        loadRegions();
        loadDistricts();
        //loadWards();

        return view;
    }//end onCreate
    //method to populate spinner
    private void loadRegions() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<RegionSpinnerController> data = dbHandler.getAllRegions();
        //create adapter for spiner
        ArrayAdapter<RegionSpinnerController> adapter= new
                ArrayAdapter<RegionSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_region.setAdapter(adapter);
    }


    //method to populate spinner
    private void loadDistricts() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<DistrictSpinnerController> data = dbHandler.getAllDisstricts(SELECTED_REGION_ID);
        //create adapter for spiner
        ArrayAdapter<DistrictSpinnerController> adapter= new
                ArrayAdapter<DistrictSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_district.setAdapter(adapter);
    }
//    //method to populate spinner
//    private void loadWards() {
//        //database handle
//        dbHandler = new DBHandler(getActivity());
//        //spinner drop down
//        List<WardSpinnerController> data = dbHandler.getAllWards(SELECTED_DISTRICT_ID);
//        //create adapter for spiner
//        ArrayAdapter<WardSpinnerController> adapter= new
//                ArrayAdapter<WardSpinnerController>(getActivity(),
//                android.R.layout.simple_spinner_item, data);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_ward.setAdapter(adapter);
//    }

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
//                case R.id.txt_street:
//
//                    break;

//                case R.id.address:
//
//                    break;
//                case R.id.zip_code:
//
//                    break;

            }


        }
    }//end the inner class

//
//    private boolean validateStreet(){
//        if(txt_street.getText().toString().trim().isEmpty() ||
//                txt_street.getText().toString().trim().length() < 3){
//            txt_street.setError(getString(R.string.err_assoc));
//            requestFocus(txt_street);
//            return false;
//        }else{
//            txt_street.setError(null);
//        }
//        return true;
//    }

//    private boolean validatePostalStreet(){
//        if(txt_postalAddress.getText().toString().trim().isEmpty() ||
//                txt_postalAddress.getText().toString().trim().length() < 3){
//            txt_postalAddress.setError(getString(R.string.err_assoc));
//            requestFocus(txt_postalAddress);
//            return false;
//        }else{
//            txt_postalAddress.setError(null);
//        }
//        return true;
//    }
//
//    private boolean validateZipCode(){
//        if(txt_zipcode.getText().toString().trim().isEmpty() ||
//                txt_zipcode.getText().toString().trim().length() < 3){
//            txt_zipcode.setError(getString(R.string.err_assoc));
//            requestFocus(txt_zipcode);
//            return false;
//        }else{
//            txt_zipcode.setError(null);
//        }
//        return true;
//    }

//    private boolean validateGPS(){
//        if(txt_gps.getText().toString().trim().isEmpty() ||
//                txt_gps.getText().toString().trim().length() < 3){
//            txt_gps.setError(getString(R.string.err_assoc));
//            requestFocus(txt_gps);
//            return false;
//        }else{
//            txt_gps.setError(null);
//        }
//        return true;
//    }


    /*--Request edit text focus*/
    private void requestFocus(View view){
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }//end fn

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab_farmer_assoc_previous:
                registerAssociationViewPager.onBackPressed();
                break;

            case R.id.fab_farmer_assoc_next:
               validateUserEntries();
                //Toast.makeText(getActivity().getApplicationContext(),"Save Button", Toast.LENGTH_LONG).show();
                break;

        }//end switch

    }//end onClick()


    public void validateUserEntries() {

//
//        if (!validateStreet()) {
//            return;
//        }
//        if (!validatePostalStreet()) {
//            return;
//        }
//        if (!validateZipCode()) {
//            return;
//        }
//                if(!validateGPS()){
//                    return;
//                }

        if (spinner_region.getCount() == 0 || spinner_district.getCount() == 0) {
            Toast.makeText(getActivity().getApplication(), "PLease Provide all Location Data", Toast.LENGTH_LONG).show();


        } else {

            String region = ((RegionSpinnerController) spinner_region.getSelectedItem()).getDatabase_id();
            String district = ((DistrictSpinnerController) spinner_district.getSelectedItem()).getDatabase_id();
            //String ward = ((WardSpinnerController) spinner_ward.getSelectedItem()).getDatabase_id();

            bus.postSticky(new AssociationOtherEventHandler(
                    String.valueOf(spinner_region.getSelectedItem()),
                    String.valueOf(spinner_district.getSelectedItem()),

                    region.toString(),
                    district.toString()));


            //goto next tab
            registerAssociationViewPager.setCurrentItem(2, true);
            //activate/highlight  the tab as active
            registerAssociationViewPager.activateTab();


        }//end method

    }


}//end class
