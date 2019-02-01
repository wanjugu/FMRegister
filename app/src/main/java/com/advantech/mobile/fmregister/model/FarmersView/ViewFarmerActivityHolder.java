package com.advantech.mobile.fmregister.model.FarmersView;

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

class ViewFarmerActivityHolder extends RecyclerView.ViewHolder {
    public TextView farmer_name,farmer_id,farmer_region,farmer_assoc;
    public TextView farmer_firstname, farmer_surname;

    public TextView othernames,phone,gender;
    public TextView regionId,districtId,villageId,wardId;
    public TextView districtname,wardname,villagename;
    public TextView products;
    public TextView association_id,syncStatus;

    public ImageView farmer_avatar;
    public CardView cardView;
    Context context;


    public ViewFarmerActivityHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        farmer_id = (TextView) itemView.findViewById(R.id.txt_mobileno);
        farmer_name = (TextView) itemView.findViewById(R.id.txt_name);
        farmer_region = (TextView) itemView.findViewById(R.id.txt_region);
        farmer_assoc = (TextView) itemView.findViewById(R.id.txt_association);

        //VISIBLE:GONE VIEW --- DEFINED AS PLACE HOLDERS
        farmer_firstname = (TextView) itemView.findViewById(R.id.txt_firstname);
        farmer_surname = (TextView) itemView.findViewById(R.id.txt_lname);
        othernames = (TextView) itemView.findViewById(R.id.txt_othernames);
       // dob = (TextView) itemView.findViewById(R.id.txt_DOB);
        phone = (TextView) itemView.findViewById(R.id.txt_phonenumber);
        gender = (TextView) itemView.findViewById(R.id.txt_gender);
        regionId = (TextView) itemView.findViewById(R.id.txt_regionID);
        districtId = (TextView) itemView.findViewById(R.id.txt_districtID);
        districtname = (TextView) itemView.findViewById(R.id.txt_district);
        wardId = (TextView) itemView.findViewById(R.id.txt_wardID);
        wardname = (TextView) itemView.findViewById(R.id.txt_wardname);
        villagename = (TextView) itemView.findViewById(R.id.txt_village);
        villageId = (TextView) itemView.findViewById(R.id.txt_villageID);
//        myAssoc_id = (TextView) itemView.findViewById(R.id.txt_myassocID);
//        businessType_name = (TextView) itemView.findViewById(R.id.txt_businesstype);
//        businessType_id = (TextView) itemView.findViewById(R.id.txt_businesstypeID);
        association_id = (TextView) itemView.findViewById(R.id.txt_assocId);

//        positionName = (TextView) itemView.findViewById(R.id.txt_positionName);
//        position_id = (TextView) itemView.findViewById(R.id.txt_positionId);
        products = (TextView) itemView.findViewById(R.id.txt_products);
        syncStatus = (TextView) itemView.findViewById(R.id.txt_syncstatus);


        cardView = (CardView) itemView.findViewById(R.id.viewFarmers_cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FarmersDetailedView.class);
                intent.putExtra("id",farmer_id.getText().toString());
                intent.putExtra("fnames", farmer_firstname.getText().toString());
                intent.putExtra("lnames", farmer_surname.getText().toString());
                intent.putExtra("othernames",othernames.getText().toString());
               // intent.putExtra("dob", dob.getText().toString());
                intent.putExtra("phoneno",phone.getText().toString());
                intent.putExtra("gender",gender.getText().toString());
                intent.putExtra("regionname",farmer_region.getText().toString());
                intent.putExtra("regionID",regionId.getText().toString());
                intent.putExtra("districtname",districtname.getText().toString());
                intent.putExtra("districtID",districtId.getText().toString());
                intent.putExtra("wardname",wardname.getText().toString());
                intent.putExtra("wardID",wardId.getText().toString());
                intent.putExtra("village",villagename.getText().toString());
                intent.putExtra("villageId",villageId.getText().toString());
//                intent.putExtra("myassocid",myAssoc_id.getText().toString());
//                intent.putExtra("bsntype",businessType_name.getText().toString());
//                intent.putExtra("bsntypeID",businessType_id.getText().toString());
                intent.putExtra("assocID",association_id.getText().toString());
                intent.putExtra("assocname",farmer_assoc.getText().toString());
//                intent.putExtra("position",positionName.getText().toString());
//                intent.putExtra("positionID",position_id.getText().toString());
                intent.putExtra("commodities",products.getText().toString());
                intent.putExtra("syncstatus",syncStatus.getText().toString());

                view.getContext().startActivity(intent);

            }
        });

    }
}
