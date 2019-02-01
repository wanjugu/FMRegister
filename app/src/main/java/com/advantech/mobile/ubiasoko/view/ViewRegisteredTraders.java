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
import com.advantech.mobile.ubiasoko.model.TradersView.ViewRegisteredTraderAdapter;
import com.advantech.mobile.ubiasoko.model.TradersView.ViewTradersModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADVANTECH CONSULTING on 5/24/2018.
 */

public class ViewRegisteredTraders extends AppCompatActivity{
    DBHandler dbHandler;
    ViewRegisteredTraderAdapter mAdapter;
    RecyclerView traderRecyclerView;

    EditText search;
    List<ViewTradersModel> allTraders = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_registered_traders_activity);



        traderRecyclerView = (RecyclerView) findViewById(R.id.traders_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        traderRecyclerView.setLayoutManager(linearLayoutManager);
        traderRecyclerView.setHasFixedSize(true);

        dbHandler = new DBHandler(this);

        allTraders = dbHandler.getAllTraderModel();
        if(allTraders.size() > 0){
            traderRecyclerView.setVisibility(View.VISIBLE);
            mAdapter = new ViewRegisteredTraderAdapter(this,allTraders);

            traderRecyclerView.setAdapter(mAdapter);
        }else{
            traderRecyclerView.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Farmer's Database is Empty",Toast.LENGTH_LONG).show();
        }

        //define search
        search = (EditText) findViewById(R.id.search_trader);

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

                final List<ViewTradersModel> filteredList = new ArrayList<>();

                for(int i =0; i<allTraders.size(); i++){
                    final String text =  String.valueOf(allTraders.get(i).toString()).toLowerCase();
                    final String id = String.valueOf(allTraders.get(i).getTraderId());
                    final String Region = String.valueOf(allTraders.get(i).getRegion().toString().toLowerCase());

                    if (text.contains(query) ||  id.contains(query) || Region.contains(query)){
                        filteredList.add(allTraders.get(i));
                    }
                }

                traderRecyclerView.setLayoutManager(new LinearLayoutManager(ViewRegisteredTraders.this));

                mAdapter = new ViewRegisteredTraderAdapter(ViewRegisteredTraders.this,filteredList);

                traderRecyclerView.setAdapter(mAdapter);
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
}//end

