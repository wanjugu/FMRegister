package com.advantech.mobile.fmregister.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.advantech.mobile.fmregister.R;
import com.advantech.mobile.fmregister.controller.BusinessTypeSpinnerController;
import com.advantech.mobile.fmregister.controller.DBHandler;
import com.advantech.mobile.fmregister.controller.ProductSpinnerController;
import com.advantech.mobile.fmregister.model.TraderOtherDetailsEventHandler;
import com.thomashaertel.widget.MultiSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TraderOtherDetailsFragmentxxxx extends Fragment implements View.OnClickListener {

    TextInputLayout tv_Businessname,tv_phoneNo;
    EditText txt_Businessname,txt_phoneNo;
    Spinner spinner_bsnType;
    MultiSpinner spinner_commodity;

    FloatingActionButton fabPrevious,fabnext;

    //initialize the View pager
    RegisterTraderViewPager registerTraderViewPager;

    //alert dialog
    AlertDialog alertDialog;

    DBHandler dbHandler;

    List<BusinessTypeSpinnerController> business_adapter;
    List<ProductSpinnerController> product_adapter;
    String builder_products;
    String builder_bsn_type;


    //eventbus for fragment data passing
    EventBus bus = EventBus.getDefault();



    public TraderOtherDetailsFragmentxxxx() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.trader_other_details_fragment, container, false);

        tv_Businessname = (TextInputLayout) view.findViewById(R.id.input_bsn_name);
        txt_Businessname = (EditText) view.findViewById(R.id.txt_bsn_name);
        txt_Businessname.addTextChangedListener(new MyTextWatcher(txt_Businessname));

        tv_phoneNo = (TextInputLayout) view.findViewById(R.id.input_phoneno);
        txt_phoneNo = (EditText) view.findViewById(R.id.txt_phoneno);
        txt_phoneNo.addTextChangedListener(new MyTextWatcher(txt_phoneNo));


        spinner_bsnType = (Spinner) view.findViewById(R.id.spinner_business_type);
        spinner_commodity = (MultiSpinner) view.findViewById(R.id.spinner_commodities);

        //Button init
        fabPrevious = (FloatingActionButton) view.findViewById(R.id.fab_otherdetail_previous);
        fabnext = (FloatingActionButton) view.findViewById(R.id.fab_otherdetail_next);


        //onclick listeners
        fabPrevious.setOnClickListener(this);
        fabnext.setOnClickListener(this);


        //initialize viewpager activity
        registerTraderViewPager = (RegisterTraderViewPager) getActivity();

        dbHandler = new DBHandler(getContext());

        //load spinner
        loadBusinessSpinner();
        loadProductsSpinner();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab_otherdetail_previous:
                registerTraderViewPager.onBackPressed();
                break;

            case R.id.fab_otherdetail_next:
               validateUserEntry();
                break;

        }//end switch

    }//end onclick

    //Custom dialog for save
    public void saveDialog(){
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("EXIT")
                .setMessage("Save Data to database.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity().getApplicationContext(),"You want to save trader data", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getActivity().getApplicationContext(),"TradersRegistration cancelled by user", Toast.LENGTH_LONG).show();
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
                case R.id.txt_bsn_name:
                    validateBsnName();
                    break;

                case R.id.txt_phoneno:
                    validateMobilePhone();
                    break;

            }


        }
    }//end the inner class

    /*--Request edit text focus*/
    private void requestFocus(View view){
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }//end fn

   //validate businessname
    private boolean validateBsnName(){
        if(txt_Businessname.getText().toString().trim().isEmpty() ||
                txt_Businessname.getText().toString().trim().length() < 2){
            txt_Businessname.setError(getString(R.string.err_assoc));
            requestFocus(txt_Businessname);
            return false;
        }else{
            txt_Businessname.setError(null);
        }
        return true;
    }

    //validate Mobile, Simple Validation
    private boolean validateMobilePhone(){
        if(txt_phoneNo.getText().toString().trim().isEmpty() ||
                txt_phoneNo.getText().toString().trim().length() < 5){
            txt_phoneNo.setError(getString(R.string.err_assoc));
            requestFocus(txt_phoneNo);
            return false;
        }else{
            txt_phoneNo.setError(null);
        }
        return true;
    }


    //pass textt o event bus provuded by user
    private boolean validateBusinessTypeSpinerText(){
        if( builder_products == null ){
            Toast.makeText(getActivity(),"Missing Entry:Please provide Commodity details",Toast.LENGTH_LONG).show();
            return false;
        }
        else{ return true;}
    }


    //method to populate spinner
    private void loadBusinessSpinner() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        business_adapter = dbHandler.getAllBusinessType();
        //create adapter for spiner
        ArrayAdapter<BusinessTypeSpinnerController> adapter= new
                ArrayAdapter<BusinessTypeSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, business_adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_bsnType.setAdapter(adapter);

    }
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
        spinner_commodity.setAdapter(adapter,false,onSelectedListener2);

    }



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


    public void validateUserEntry(){
        if(!validateBsnName()){
            return;
        }
        if(!validateMobilePhone()) {
            return;
        }
        if(!validateBusinessTypeSpinerText()){
            //Toast.makeText(getContext().getApplicationContext(),"COMMODITY NOT SELECTED",Toast.LENGTH_LONG).show();
            return;
        }


        String businessTypeID = ((BusinessTypeSpinnerController) spinner_bsnType.getSelectedItem()).getDatabase_id();
        String businessTypeName = ((BusinessTypeSpinnerController) spinner_bsnType.getSelectedItem()).getDatabasevalue();
        bus.postSticky(new TraderOtherDetailsEventHandler(
                txt_Businessname.getText().toString(),
                txt_phoneNo.getText().toString(),
                businessTypeName,
                builder_products,
        businessTypeID));

        registerTraderViewPager.setCurrentItem(3, true);
        //activate/highlight  the tab as active
        registerTraderViewPager.activateTab();
    }//end method
}
