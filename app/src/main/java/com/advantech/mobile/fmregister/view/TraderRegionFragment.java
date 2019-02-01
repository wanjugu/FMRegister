package com.advantech.mobile.fmregister.view;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.advantech.mobile.fmregister.R;
import com.advantech.mobile.fmregister.controller.DBHandler;
import com.advantech.mobile.fmregister.controller.DistrictSpinnerController;
import com.advantech.mobile.fmregister.controller.RegionSpinnerController;
import com.advantech.mobile.fmregister.model.TraderLocationEventHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TraderRegionFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Spinner spinner_region,spinner_district;
    //,spinner_ward,spinner_village;

    FloatingActionButton fab_next,fab_previous;
    RegisterTraderViewPager registerTraderViewPager;

    DBHandler dbHandler;
    Context context;


    /*Variables to help load SPinner onItemSelected*/
    int SELECTED_REGION_ID;
    int SELECTED_DISTRICT_ID;
    int SELECTED_WARD_ID;

    /**...............EventBus -
            * Assist in passing data from one fragment to another
     * ...in this case from contactsTab to ReviewFraqment*/
    EventBus eventBus = EventBus.getDefault();




    public TraderRegionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.traders_region_fragment, container, false);

//        tv_additionalInfo = (TextInputLayout)view.findViewById(R.id.input_additional_details);
//        txt_additionalInfo = (EditText)view.findViewById(R.id.txt_additional_details);
//        txt_additionalInfo.addTextChangedListener(new MyTextWatcher(txt_additionalInfo));
    /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
//loadR
        /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
//        spinner_ward = (Spinner) view.findViewById(R.id.spinner_wardid);
//        spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//
//                String ward_id = ((WardSpinnerController)spinner_ward.getSelectedItem()).getDatabase_id();
//                SELECTED_WARD_ID = Integer.parseInt(ward_id);
//
//                //load  spinner
//                loadVillages();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
//        spinner_village = (Spinner) view.findViewById(R.id.spinner_village);
//
//
        fab_next = (FloatingActionButton) view.findViewById(R.id.fab_trader_next);
        fab_previous = (FloatingActionButton) view.findViewById(R.id.fab_trader_previous);
//
//        //handle click
       fab_previous.setOnClickListener(this);
       fab_next.setOnClickListener(this);
//
        //initialize viewpager activit
        registerTraderViewPager = (RegisterTraderViewPager) getActivity();
        context = this.getContext();
//
        dbHandler = new DBHandler(getContext().getApplicationContext());
//
//        //load spinners with sqlite db data
//        loadRegions();
//        loadWards();
//        loadVillages();
//
//
//
/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
        spinner_region = (Spinner) view.findViewById(R.id.spinner_region);
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
       /* ---------------------------------------------------------------------------------------------------*/

        spinner_district = (Spinner) view.findViewById(R.id.spinner_district);
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String district_id = ((DistrictSpinnerController)spinner_district.getSelectedItem()).getDatabase_id();
                SELECTED_DISTRICT_ID = Integer.parseInt(district_id);

                //load district spinner
                //loadWards();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //----------------------------------------------------------------------------------------------------


        loadRegions();
        loadDistricts();
        return view;
   }


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
//
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
//
//    //method to populate spinner
//    private void loadVillages() {
//        //database handle
//        dbHandler = new DBHandler(getActivity());
//        //spinner drop down
//        List<VillageSpinnerController> data = dbHandler.getAllVillages(SELECTED_WARD_ID);
//        //create adapter for spiner
//        ArrayAdapter<VillageSpinnerController> adapter= new
//                ArrayAdapter<VillageSpinnerController>(getActivity(),
//                android.R.layout.simple_spinner_item, data);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_village.setAdapter(adapter);
//    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fab_trader_next:
                validateSpinners();

                break;

            case R.id.fab_trader_previous:
                registerTraderViewPager.setCurrentItem(-1, true);
                break;

        }

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

//    private boolean validateAdditinalInfoText(){
//        if(txt_additionalInfo.getText().toString().trim().isEmpty() ||
//                txt_additionalInfo.getText().toString().trim().length() < 2){
//            txt_additionalInfo.setError(getString(R.string.err_assoc));
//            requestFocus(txt_additionalInfo);
//            return false;
//        }else{
//            txt_additionalInfo.setError(null);
//        }
//        return true;
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
//                case R.id.txt_additional_details:
//                    validateAdditinalInfoText();
//                    break;


            }


        }
    }//end the inner class

    //validate teh spinner inpute
    public void validateSpinners(){


        if(spinner_region.getCount() == 0 || spinner_district.getCount() == 0){
            Toast.makeText(getActivity().getApplication(),"PLease Provide all Location Data",Toast.LENGTH_LONG).show();



        }else{
            String region = ((RegionSpinnerController) spinner_region.getSelectedItem()).getDatabase_id();
            String district = ((DistrictSpinnerController) spinner_district.getSelectedItem()).getDatabase_id();
//            String ward = ((WardSpinnerController) spinner_ward.getSelectedItem()).getDatabase_id();
//            String village = ((VillageSpinnerController) spinner_village.getSelectedItem()).getDatabase_id();
           // String additionalIfo = txt_additionalInfo.getText().toString();

            eventBus.postSticky(new TraderLocationEventHandler(
                    String.valueOf(spinner_region.getSelectedItem()),
                    String.valueOf(spinner_district.getSelectedItem()),
                    region.toString(),
                    district.toString()));

            //Toast.makeText(getActivity().getApplicationContext(),"Hurray Next",Toast.LENGTH_LONG).show();
            registerTraderViewPager.setCurrentItem(2, true);
            registerTraderViewPager.activateTab();


        }
    }

}
