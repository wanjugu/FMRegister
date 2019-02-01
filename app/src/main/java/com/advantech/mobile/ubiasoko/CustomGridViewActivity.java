package com.advantech.mobile.ubiasoko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ADVANTECH CONSULTING on 5/23/2018.
 */

public class CustomGridViewActivity extends BaseAdapter {

    private Context context;
    private final String[] gridViewString;
    private  final int[] gridViewImageId;


    public CustomGridViewActivity(Context context, String[] gridViewString, int[] gridViewImageId) {
        this.context = context;
        this.gridViewString = gridViewString;
        this.gridViewImageId = gridViewImageId;
    }



    @Override
    public int getCount() {
        return gridViewImageId.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            gridViewAndroid = new View(context);
            gridViewAndroid = inflater.inflate(R.layout.grid_view_layout,null);

            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridView_text);
            ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);

            textViewAndroid.setText(gridViewString[i]);
            imageViewAndroid.setImageResource(gridViewImageId[i]);
        }else{
            gridViewAndroid = (View) convertView;
        }
        return gridViewAndroid;
    }
}
