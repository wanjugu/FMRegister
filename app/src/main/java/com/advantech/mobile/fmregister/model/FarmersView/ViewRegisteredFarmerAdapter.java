package com.advantech.mobile.fmregister.model.FarmersView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.advantech.mobile.fmregister.R;
import com.advantech.mobile.fmregister.controller.DBHandler;

import java.util.List;

/**
 * Created by ADVANTECH CONSULTING on 5/23/2018.
 */

public class ViewRegisteredFarmerAdapter extends RecyclerView.Adapter<ViewFarmerActivityHolder> {
    private Context context;
    List<ViewFarmerModel> listFarmers;
    private DBHandler dbHandler;

    public ViewRegisteredFarmerAdapter(Context context,List<ViewFarmerModel> listFarmers) {
        this.context = context;
        this.listFarmers = listFarmers;
        dbHandler = new DBHandler(context);
    }

    @Override
    public ViewFarmerActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.farmer_activity_list_layout,parent,false);

        return new ViewFarmerActivityHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewFarmerActivityHolder holder, int position) {
        final ViewFarmerModel details = listFarmers.get(position);

        holder.farmer_name.setText(details.toString());
        holder.farmer_id.setText(details.getNationalId());

        holder.farmer_region.setText(details.getRegionname());
        holder.farmer_assoc.setText(details.getAssociation_name());

        //These are hidden view used the Intent
        holder.farmer_firstname.setText(details.getFirstname());
        holder.farmer_surname.setText(details.getSurname());
        holder.othernames.setText(details.getOthernames());
       // holder.dob.setText(details.getDob());
        holder.phone.setText(details.getPhone());

        holder.gender.setText(details.getGender());

        holder.regionId.setText(String.valueOf(details.getRegionId()));
        holder.districtname.setText(details.getDistrictname());
        holder.districtId.setText(String.valueOf(details.getDistrictId()));
        holder.wardname.setText(details.getWardname());
        holder.wardId.setText(String.valueOf(details.getWardId()));
        holder.villagename.setText(details.getVillagename());
        holder.villageId.setText(String.valueOf(details.getVillageId()));
//        holder.myAssoc_id.setText(String.valueOf(details.getMyAssoc_id()));
//        holder.businessType_name.setText(details.getBusinessType_name());
//        holder.businessType_id.setText(String.valueOf(details.getBusinessType_id()));
        holder.association_id.setText(String.valueOf(details.getAssociation_id()));
        holder.farmer_assoc.setText(String.valueOf(details.getAssociation_name()));
//        holder.positionName.setText(details.getPositionName());
//        holder.position_id.setText(String.valueOf(details.getPosition_id()));
        holder.products.setText(details.getProducts());
        holder.syncStatus.setText(String.valueOf(details.getSyncStatus()));


    }

    @Override
    public int getItemCount() {
        return listFarmers.size();
    }
}
