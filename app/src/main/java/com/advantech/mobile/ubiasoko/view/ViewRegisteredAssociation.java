package com.advantech.mobile.ubiasoko.view;

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

import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.model.AssocView.ViewAssociationsModel;
import com.advantech.mobile.ubiasoko.model.AssocView.ViewRegisteredAssociationsAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADVANTECH CONSULTING on 5/25/2018.
 */

public class ViewRegisteredAssociation extends AppCompatActivity {
    DBHandler dbHandler;
    private ViewRegisteredAssociationsAdapter mAdapter;
    private RecyclerView recyclerView;

    public EditText search;
    List<ViewAssociationsModel> allAssocs = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_registered_associations_activity);

        search = (EditText) findViewById(R.id.search_assoc);

        recyclerView = (RecyclerView) findViewById(R.id.assoc_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        dbHandler = new DBHandler(this);

        allAssocs = dbHandler.getAllAssocModel();
        if(allAssocs.size() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            mAdapter = new ViewRegisteredAssociationsAdapter(this,allAssocs);
            recyclerView.setAdapter(mAdapter);
        }else{
            recyclerView.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Association's Database is Empty",Toast.LENGTH_LONG).show();
        }

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

                final List<ViewAssociationsModel> filteredList = new ArrayList<>();

                for(int i =0; i<allAssocs.size(); i++){
                    final String text =  String.valueOf(allAssocs.get(i).getAssoc_name().toLowerCase());
                    final String id = String.valueOf(allAssocs.get(i).getAssoc_ID());
                    final String Region = String.valueOf(allAssocs.get(i).getRegion().toString().toLowerCase());

                    if (text.contains(query) ||  id.contains(query) || Region.contains(query)){
                        filteredList.add(allAssocs.get(i));
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(ViewRegisteredAssociation.this));

                mAdapter = new ViewRegisteredAssociationsAdapter(ViewRegisteredAssociation.this,filteredList);

                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
