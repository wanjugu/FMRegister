package com.advantech.mobile.fmregister.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.advantech.mobile.fmregister.R;
import com.advantech.mobile.fmregister.controller.DBHandler;
import com.advantech.mobile.fmregister.model.FarmersView.ViewFarmerModel;
import com.advantech.mobile.fmregister.model.FarmersView.ViewRegisteredFarmerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADVANTECH CONSULTING on 5/23/2018.
 */

public class ViewRegisteredFarmers extends AppCompatActivity {
    DBHandler dbHandler;
    private ViewRegisteredFarmerAdapter mAdapter;
    private RecyclerView farmerView;

    public EditText search;
    List<ViewFarmerModel> allFarmers = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_registered_farmers_activity);



        farmerView = (RecyclerView) findViewById(R.id.farmers_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        farmerView.setLayoutManager(linearLayoutManager);
        farmerView.setHasFixedSize(true);

        dbHandler = new DBHandler(this);

        allFarmers = dbHandler.getAllFarmersModel();
        if(allFarmers.size() > 0){
            farmerView.setVisibility(View.VISIBLE);
            mAdapter = new ViewRegisteredFarmerAdapter(this,allFarmers);

            farmerView.setAdapter(mAdapter);
        }else{
            farmerView.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Farmer's Database is Empty",Toast.LENGTH_LONG).show();
        }

        //define search
        search = (EditText) findViewById(R.id.search_farmer);

        //call to search function
        addSearchTextListener();


    }//end oncreate


    private void addSearchTextListener() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }



            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {





                query = query.toString().toLowerCase();

                final List<ViewFarmerModel> filteredList = new ArrayList<>();

                for(int i =0; i<allFarmers.size(); i++){
                    final String text =  String.valueOf(allFarmers.get(i).toString()).toLowerCase();
                    final String id = String.valueOf(allFarmers.get(i).getNationalId());
                    final String Region = String.valueOf(allFarmers.get(i).getRegionname().toString().toLowerCase());

                    if (text.contains(query) ||  id.contains(query) || Region.contains(query)){
                        filteredList.add(allFarmers.get(i));
                    }
                }

                farmerView.setLayoutManager(new LinearLayoutManager(ViewRegisteredFarmers.this));

                mAdapter = new ViewRegisteredFarmerAdapter(ViewRegisteredFarmers.this,filteredList);

                farmerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }




    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHandler != null) {
            dbHandler.close();
        }

    }
}//end main class
