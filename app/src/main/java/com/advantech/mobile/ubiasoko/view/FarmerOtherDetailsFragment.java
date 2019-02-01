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
import com.advantech.mobile.ubiasoko.controller.AssociationSpinnerController;
import com.advantech.mobile.ubiasoko.controller.BusinessTypeSpinnerController;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.controller.PositionSpinnerController;
import com.advantech.mobile.ubiasoko.controller.ProductSpinnerController;
import com.advantech.mobile.ubiasoko.model.FarmerOtherInfoEventHandler;
import com.thomashaertel.widget.MultiSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FarmerOtherDetailsFragment extends Fragment implements View.OnClickListener, MultiSpinner.MultiSpinnerListener, AdapterView.OnItemSelectedListener {
    FloatingActionButton fabPrevious,fabnext,fabSave,fabCancel;

    private final String TAG = DBHandler.class.getSimpleName();


    //initialize the View pager
    RegisterFarmerViewPager registerFarmerViewPager;

    //alert dialog
    AlertDialog alertDialog;

    TextInputLayout tv_farmer_assoc_id;

    Spinner spinner_association;
    MultiSpinner spinner_products;
    boolean[] selectedItems;
    boolean[] selectedItems2;
    List<BusinessTypeSpinnerController> business_adapter;
    List<ProductSpinnerController> product_adapter;
    String builder_bsn_type;
    String builder_products;

    EditText txt_nationalid, txt_firstname,txt_surname,txt_othernames;


    //eventbus for fragment data passing
    EventBus bus = EventBus.getDefault();



    DBHandler dbHandler;


    public FarmerOtherDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_farmer_other_details, container, false);


      //  tv_farmer_assoc_id = (TextInputLayout) view.findViewById(R.id.input_assoc_id);
        //txt_farmer_assoc_id = (EditText) view.findViewById(R.id.txt_assoc_id);
       // txt_farmer_assoc_id.addTextChangedListener(new MyTextWatcher(txt_farmer_assoc_id));

       // spinner_business_type = (Spinner) view.findViewById(R.id.spinner_business_type);
        //spinner_business_type.setOnItemSelectedListener(this);

        spinner_association = (Spinner) view.findViewById(R.id.spinner_association);
        spinner_association.setOnItemSelectedListener(this);
       // spinner_position = (Spinner) view.findViewById(R.id.spinner_position);
        //spinner_position.setOnItemSelectedListener(this);
        spinner_products = (MultiSpinner) view.findViewById(R.id.spinner_commodities);
        spinner_products.setOnItemsSelectedListener(this);


        //Button init
        fabPrevious = (FloatingActionButton) view.findViewById(R.id.fab_farmer_other_details_previous);
        fabnext = (FloatingActionButton) view.findViewById(R.id.fab_farmer_other_details_next);


        //onclick listeners
        fabPrevious.setOnClickListener(this);
        fabnext.setOnClickListener(this);


        //initialize viewpager activity
        registerFarmerViewPager = (RegisterFarmerViewPager) getActivity();

        //load spinner with sqlite data
        //loadBusinessSpinner();
        loadAssociationSpinner();
        loadProductsSpinner();
        //loadpositionsSpinner();

        return view;
    }


    //method to populate spinner
//    private void loadBusinessSpinner() {
//        //database handle
//        dbHandler = new DBHandler(getActivity());
//        //spinner drop down
//         business_adapter = dbHandler.getAllBusinessType();
//        //create adapter for spiner
//        ArrayAdapter<BusinessTypeSpinnerController> adapter= new
//                ArrayAdapter<BusinessTypeSpinnerController>(getActivity(),
//                android.R.layout.simple_spinner_item, business_adapter);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_business_type.setAdapter(adapter);
//
//        //set a default selected item
////       selectedItems = new boolean[adapter.getCount()];
////        selectedItems[0] = true;
////       spinner_business_type.setSelected(selectedItems);
//    }

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

    //method to populate spinner
//    private void loadpositionsSpinner() {
//        //database handle
//        dbHandler = new DBHandler(getActivity());
//        //spinner drop down
//        List<PositionSpinnerController> data = dbHandler.getAllPositions();
//        //create adapter for spiner
//        ArrayAdapter<PositionSpinnerController> adapter= new
//                ArrayAdapter<PositionSpinnerController>(getActivity(),
//                android.R.layout.simple_spinner_item, data);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_position.setAdapter(adapter);
//    }


    //method to populate spinner
    private void loadProductsSpinner() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        product_adapter = dbHandler.getAllProducts();
        //create adapter for spiner
        ArrayAdapter<ProductSpinnerController> adapter= new
                ArrayAdapter<ProductSpinnerController>(getActivity(),
                R.layout.multi_select_spinner, product_adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_products.setAdapter(adapter,false,onSelectedListener2);

//        //set a default selected item
        selectedItems2 = new boolean[adapter.getCount()];
        selectedItems2[0] = true;
        spinner_products.setSelected(selectedItems2);
    }



//    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
//        @Override
//        public void onItemsSelected(boolean[] selected) {
//
//           StringBuilder builder = new StringBuilder();
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
//           // Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
//
//
//        }
//    };

    private MultiSpinner.MultiSpinnerListener onSelectedListener2 = new MultiSpinner.MultiSpinnerListener() {
        @Override
        public void onItemsSelected(boolean[] selected) {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    builder.append(product_adapter.get(i)).append(" ");
                }
            }

            builder_products = builder.toString();

           // Toast.makeText(getActivity(), builder_products.toString(), Toast.LENGTH_SHORT).show();


        }
    };


   //pass textt o event bus provuded by user
    private boolean validateBusinessTypeSpinerText(){
        if(builder_bsn_type == "" || builder_bsn_type == null ){
            Toast.makeText(getActivity(),"Missing Entry:Please provide Business Type details",Toast.LENGTH_LONG).show();
            return false;
        }
       else{ return true;}
    }
    private boolean validateProductSpinner(){
        if(builder_products == "" || builder_products == null){
            Toast.makeText(getActivity(),"Missing Entry!Please provide Commodities details",Toast.LENGTH_LONG).show();
            return false;
        }
        else{ return true;}
    }
    private boolean validateAssociationpinner(){
        if(spinner_association.getCount() == 0 ){
            Toast.makeText(getActivity(),"Missing Entry!Please Provide Association details",Toast.LENGTH_LONG).show();
            return false;
        }
        else{ return true;}
    }
//    private boolean validatePositionSpinner(){
//        if(spinner_position.getCount() == 0 ){
//            Toast.makeText(getActivity(),"Missing Entry!Please Provide Farmer's Position details",Toast.LENGTH_LONG).show();
//            return false;
//        }
//        else{ return true;}
//    }

public void validateUserEntries() {


//    if (!validateAsscoId()) {
//        return;
//    }
//    if (!validateBusinessTypeSpinerText()) {
//
//        return;
//    }
    if (!validateProductSpinner()) {
        return;
    }
    if (!validateAssociationpinner()) {
        return;
    }
//    if (!validatePositionSpinner()) {
//        return;
//    }


    String associationid = ((AssociationSpinnerController) spinner_association.getSelectedItem()).getDatabase_id();
  //  String position = ((PositionSpinnerController) spinner_position.getSelectedItem()).getDatabase_id();
   // String businesstype = ((BusinessTypeSpinnerController) spinner_business_type.getSelectedItem()).getDatabase_id();


    bus.postSticky(new FarmerOtherInfoEventHandler(

            String.valueOf(spinner_association.getSelectedItem()),
            builder_products,associationid));


    //goto next tab
    registerFarmerViewPager.setCurrentItem(3, true);
    //activate/highlight  the tab as active
    registerFarmerViewPager.activateTab();


}//end method

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab_farmer_other_details_previous:
                registerFarmerViewPager.onBackPressed();
                break;

            case R.id.fab_farmer_other_details_next:

                validateUserEntries();
                //Toast.makeText(getActivity(), "Business::"+builder_bsn_type, Toast.LENGTH_SHORT).show();

                break;


        }//end switch
    }//end onclick

    //Custom dialog for save
   /* public void saveDialog(){
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("EXIT")
                .setMessage("Save Data to database.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        //Toast.makeText(getActivity().getApplicationContext(),"You want to save data", Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();

    }*/
    //Custom dialog for cancel
  /*  public void cancelDialog(){
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
    }*/





    @Override
    public void onItemsSelected(boolean[] selected) {


    }

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
//                case R.id.txt_assoc_id:
//
//                    break;


            }


        }
    }//end the inner class

    /*--Request edit text focus*/
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
//
//    /*-------validate text fields---------*/
//    private boolean validateAsscoId(){
//        if(txt_farmer_assoc_id.getText().toString().trim().isEmpty() ||
//                txt_farmer_assoc_id.getText().toString().trim().length() < 2){
//            txt_farmer_assoc_id.setError(getString(R.string.err_assoc));
//            requestFocus(txt_farmer_assoc_id);
//            return false;
//        }else{
//            txt_farmer_assoc_id.setError(null);
//        }
//        return true;
//    }


}//enc class
