package com.advantech.mobile.fmregister.model.AssocView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.advantech.mobile.fmregister.R;

/**
 * Created by ADVANTECH CONSULTING on 5/25/2018.
 */

public class ViewAssocActivityHolder extends RecyclerView.ViewHolder {
    public TextView assoc_name,assoc_region,assoc_district,assoc_phone;


    public ImageView assoc_avatar;
    public CardView cardView;
    Context context;
    public ViewAssocActivityHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        assoc_name = (TextView) itemView.findViewById(R.id.txt_name);
        assoc_region = (TextView) itemView.findViewById(R.id.txt_region);
        assoc_district = (TextView) itemView.findViewById(R.id.txt_association);
        assoc_phone = (TextView) itemView.findViewById(R.id.txt_mobileno);

        cardView = (CardView) itemView.findViewById(R.id.viewAssoc_cardView);
    }
}
