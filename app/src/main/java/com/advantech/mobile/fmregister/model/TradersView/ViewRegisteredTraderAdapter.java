package com.advantech.mobile.fmregister.model.TradersView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.advantech.mobile.fmregister.R;
import com.advantech.mobile.fmregister.controller.DBHandler;

import java.util.List;


/**
 * Created by ADVANTECH CONSULTING on 5/24/2018.
 */

public class ViewRegisteredTraderAdapter  extends RecyclerView.Adapter<ViewTraderActivityHolder>{
    private Context context;
    List<ViewTradersModel> listTraders;
    private DBHandler dbHandler;

    public ViewRegisteredTraderAdapter(Context context, List<ViewTradersModel> listTraders) {
        this.context = context;
        this.listTraders = listTraders;
        dbHandler = new DBHandler(context);
    }

    @Override
    public ViewTraderActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.trader_activity_list_layout,parent,false);

        return new ViewTraderActivityHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewTraderActivityHolder holder, int position) {
        final ViewTradersModel details = listTraders.get(position);

        holder.trader_name.setText(details.toString());
        holder.tv_trader_phone.setText(details.getMobile());
        holder.tv_trader_region.setText(details.getRegion());
        holder.tv_trader_district.setText(details.getDistrict());

        //hidden views
      //  holder.tv_trader_nationalid.setText(details.getNationalID());
        holder.tv_trader_firstname.setText(details.getFirstname());
        holder.tv_trader_surname.setText(details.getSurname());
        holder.tv_trader_othernames.setText(details.getOthername());
      //  holder.tv_trader_dob.setText(details.getDob());
        holder.tv_trader_gender.setText(details.getGender());

       // TODO fix the holder error
        holder.tv_trader_regionID.setText(String.valueOf(details.getRegionID()));
        holder.tv_trader_districtID.setText(String.valueOf(details.getDistrictID()));

        holder.tv_trader_ward.setText(details.getWard());
        holder.tv_trader_wardID.setText(String.valueOf(details.getWardID()));

        holder.tv_trader_village.setText(details.getVillage());
        holder.tv_trader_villageID.setText(String.valueOf(details.getVillageID()));
        //holder.tv_trader_moreinfo.setText(details.getAdditionalInfo());

        holder.tv_trader_business_name.setText(details.getBusinessname());
      //  holder.tv_trader_telephoneno.setText(details.getPhoneno2());

       // holder.tv_trader_businessType.setText(details.getBusinessTypename());
       // holder.tv_trader_businessTypeID.setText(String.valueOf(details.getBusinessType_ID()));

       // holder.tv_trader_commodity.setText(details.getCommodity());




    }

    @Override
    public int getItemCount() {
        return listTraders.size();
    }
}
