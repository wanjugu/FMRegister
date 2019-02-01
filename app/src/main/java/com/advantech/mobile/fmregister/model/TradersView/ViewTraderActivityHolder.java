package com.advantech.mobile.fmregister.model.TradersView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.advantech.mobile.fmregister.R;

/**
 * Created by ADVANTECH CONSULTING on 5/24/2018.
 */

public class ViewTraderActivityHolder extends RecyclerView.ViewHolder{
    public TextView trader_name,tv_trader_phone,tv_trader_gender;
    public TextView tv_trader_firstname, tv_trader_surname,tv_trader_othernames;
    public TextView tv_trader_region,tv_trader_district,tv_trader_ward,tv_trader_village;
    public TextView tv_trader_business_name;

    public TextView tv_trader_regionID,tv_trader_districtID,tv_trader_wardID,tv_trader_villageID;



    public TextView othernames,dob,phone,gender;

    public ImageView trader_avatar;
    public CardView cardView;
    Context context;
    public ViewTraderActivityHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        trader_name = (TextView) itemView.findViewById(R.id.txt_name);
        tv_trader_phone = (TextView) itemView.findViewById(R.id.txt_mobileno);
        tv_trader_region = (TextView) itemView.findViewById(R.id.txt_region);
        tv_trader_district = (TextView) itemView.findViewById(R.id.txt_association);

        //VISIBLE:GONE VIEW --- DEFINED AS PLACE HOLDERS
        //tv_trader_nationalid  = (TextView) itemView.findViewById(R.id.txt_trader_nationalid);
        tv_trader_firstname = (TextView) itemView.findViewById(R.id.txt_firstname);
        tv_trader_surname = (TextView) itemView.findViewById(R.id.txt_lname);
        tv_trader_othernames = (TextView) itemView.findViewById(R.id.txt_othernames);
      //  tv_trader_dob = (TextView) itemView.findViewById(R.id.txt_DOB);
        tv_trader_gender  = (TextView) itemView.findViewById(R.id.txt_gender);


        tv_trader_ward = (TextView) itemView.findViewById(R.id.txt_village);
        tv_trader_village = (TextView) itemView.findViewById(R.id.txt_village);
       // tv_trader_moreinfo = (TextView) itemView.findViewById(R.id.txt_moreLocationInfo);
        tv_trader_business_name = (TextView) itemView.findViewById(R.id.txt_bsnname);
       // tv_trader_telephoneno = (TextView) itemView.findViewById(R.id.txt_telephone);
       // tv_trader_businessType = (TextView) itemView.findViewById(R.id.txt_businesstype);
       // tv_trader_commodity = (TextView) itemView.findViewById(R.id.txt_commodities);

        tv_trader_regionID = (TextView) itemView.findViewById(R.id.txt_trader_regionID);
        tv_trader_districtID = (TextView) itemView.findViewById(R.id.txt_districtID);
        tv_trader_wardID = (TextView) itemView.findViewById(R.id.txt_wardID);
        tv_trader_villageID = (TextView) itemView.findViewById(R.id.txt_villageId);
     //   tv_trader_businessTypeID = (TextView) itemView.findViewById(R.id.txt_businesstypeID);


        cardView = (CardView) itemView.findViewById(R.id.viewTrader_cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TradersDetailedView.class);

              //  intent.putExtra("nationalid", tv_trader_nationalid.getText().toString());
                intent.putExtra("firstname", tv_trader_firstname.getText().toString());
                intent.putExtra("surname", tv_trader_surname.getText().toString());
                intent.putExtra("othernames", tv_trader_othernames.getText().toString());
              //  intent.putExtra("DOB", tv_trader_dob.getText().toString());
                intent.putExtra("phone", tv_trader_phone.getText().toString());
                intent.putExtra("gender", tv_trader_gender.getText().toString());

                intent.putExtra("region", tv_trader_region.getText().toString());
                intent.putExtra("regionID", tv_trader_regionID.getText().toString());

                intent.putExtra("district", tv_trader_district.getText().toString());
                intent.putExtra("districtID", tv_trader_districtID.getText().toString());

                intent.putExtra("ward", tv_trader_ward.getText().toString());
                intent.putExtra("wardID", tv_trader_wardID.getText().toString());

                intent.putExtra("village", tv_trader_village.getText().toString());
                intent.putExtra("villageID", tv_trader_villageID.getText().toString());

              //  intent.putExtra("morelocinfo", tv_trader_moreinfo.getText().toString());
                intent.putExtra("businessname", tv_trader_business_name.getText().toString());
              //  intent.putExtra("telephoneno", tv_trader_telephoneno.getText().toString());

               // intent.putExtra("businesstype", tv_trader_businessType.getText().toString());
               // intent.putExtra("businesstypeID", tv_trader_businessTypeID.getText().toString());

               // intent.putExtra("commodities", tv_trader_commodity.getText().toString());

                view.getContext().startActivity(intent);

            }

        });
    }
}
