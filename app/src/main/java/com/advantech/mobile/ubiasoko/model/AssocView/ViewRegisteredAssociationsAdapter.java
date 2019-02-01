package com.advantech.mobile.ubiasoko.model.AssocView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.DBHandler;

import java.util.List;

/**
 * Created by ADVANTECH CONSULTING on 5/25/2018.
 */

public class ViewRegisteredAssociationsAdapter extends RecyclerView.Adapter<ViewAssocActivityHolder> {
    private Context context;
    List<ViewAssociationsModel> listAssoc;
    private DBHandler dbHandler;

    public ViewRegisteredAssociationsAdapter(Context context, List<ViewAssociationsModel> listAssoc) {
        this.context = context;
        this.listAssoc = listAssoc;
        dbHandler = new DBHandler(context);
    }

    @Override
    public ViewAssocActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.association_activity_list_layout,parent,false);

        return new ViewAssocActivityHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewAssocActivityHolder holder, int position) {
        final ViewAssociationsModel details = listAssoc.get(position);

        holder.assoc_name.setText(details.getAssoc_name());
        holder.assoc_region.setText(details.getRegion());
        holder.assoc_district.setText(details.getDistrict());
        holder.assoc_phone.setText(details.getAssoc_mobile());

    }

    @Override
    public int getItemCount() {

        return listAssoc.size();
    }
}
