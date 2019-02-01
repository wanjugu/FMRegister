package com.advantech.mobile.ubiasoko.view;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CheckableImageButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.advantech.mobile.ubiasoko.R;
import com.advantech.mobile.ubiasoko.controller.DBHandler;
import com.advantech.mobile.ubiasoko.controller.DistrictSpinnerController;
import com.advantech.mobile.ubiasoko.controller.RegionSpinnerController;
import com.advantech.mobile.ubiasoko.controller.VillageSpinnerController;
import com.advantech.mobile.ubiasoko.controller.WardSpinnerController;
import com.advantech.mobile.ubiasoko.model.DistrictModel;
import com.advantech.mobile.ubiasoko.model.FarmerLocationEventHandler;
import com.advantech.mobile.ubiasoko.model.RegionModel;
import com.advantech.mobile.ubiasoko.model.VillageModel;
import com.advantech.mobile.ubiasoko.model.WardModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FarmerRegionFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    //dbhandler
    DBHandler dbHandler;
    CheckableImageButton addRegion,addDistrict,addWard,addVillage;
    FloatingActionButton fab_next,fab_previous;
    RegisterFarmerViewPager registerFarmerViewPager;
    Spinner spinner_regions,spinner_districts,spinner_wards,spinner_villages;

    Context context;

    /*Variables to help load SPinner onItemSelected*/
    int SELECTED_REGION_ID;
    int SELECTED_DISTRICT_ID;
    int SELECTED_WARD_ID;




    /**...............EventBus -
     * Assist in passing data from one fragment to another
     * ...in this case from contactsTab to ReviewFraqment*/
    EventBus eventBus = EventBus.getDefault();



    public FarmerRegionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_farmer_region, container, false);
/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
        spinner_regions = (Spinner) view.findViewById(R.id.spinner_region);
        spinner_regions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String region_id = ((RegionSpinnerController)spinner_regions.getSelectedItem()).getDatabase_id();
                SELECTED_REGION_ID = Integer.parseInt(region_id);

                //load district spinner
                loadDistricts();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/

   /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/

        spinner_districts = (Spinner) view.findViewById(R.id.spinner_district);
        spinner_districts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String district_id = ((DistrictSpinnerController)spinner_districts.getSelectedItem()).getDatabase_id();
                SELECTED_DISTRICT_ID = Integer.parseInt(district_id);

                //load district spinner
                loadWards();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/



        /*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/
        spinner_wards = (Spinner) view.findViewById(R.id.spinner_wardid);
        spinner_wards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String ward_id = ((WardSpinnerController)spinner_wards.getSelectedItem()).getDatabase_id();
                SELECTED_WARD_ID = Integer.parseInt(ward_id);

                //load  spinner
                loadVillages();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


/*----------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------*/


        spinner_villages = (Spinner) view.findViewById(R.id.spinner_village);
        spinner_villages.setOnItemSelectedListener(this);


        fab_next = (FloatingActionButton) view.findViewById(R.id.fab_region_next);
        fab_previous = (FloatingActionButton) view.findViewById(R.id.fab_region_previous);

        //handle click
        fab_previous.setOnClickListener(this);
        fab_next.setOnClickListener(this);

        //initialize viewpager activit
        registerFarmerViewPager = (RegisterFarmerViewPager) getActivity();

        //checkable
        addRegion = (CheckableImageButton) view.findViewById(R.id.btn_add_region);
        addRegion.setOnClickListener(this);
        addDistrict = (CheckableImageButton) view.findViewById(R.id.btn_add_district);
        addDistrict.setOnClickListener(this);
        addWard = (CheckableImageButton) view.findViewById(R.id.btn_add_ward);
        addWard.setOnClickListener(this);
        addVillage = (CheckableImageButton) view.findViewById(R.id.btn_add_village);
        addVillage.setOnClickListener(this);

        //load spinners with sqlite db data
        loadRegions();
        loadWards();
        loadVillages();

        context = view.getContext();



        return view;
    }


    //method to populate spinner
    private void loadRegions() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<RegionSpinnerController> data = dbHandler.getAllRegions();
        //create adapter for spiner
        ArrayAdapter<RegionSpinnerController> adapter= new
                ArrayAdapter<RegionSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_regions.setAdapter(adapter);
    }



    //method to populate spinner
    private void loadDistricts() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<DistrictSpinnerController> data = dbHandler.getAllDisstricts(SELECTED_REGION_ID);
        //create adapter for spiner
        ArrayAdapter<DistrictSpinnerController> adapter= new
                ArrayAdapter<DistrictSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_districts.setAdapter(adapter);
    }

    //method to populate spinner
    private void loadWards() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<WardSpinnerController> data = dbHandler.getAllWards(SELECTED_DISTRICT_ID);
        //create adapter for spiner
        ArrayAdapter<WardSpinnerController> adapter= new
                ArrayAdapter<WardSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_wards.setAdapter(adapter);
    }

    //method to populate spinner
    private void loadVillages() {
        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<VillageSpinnerController> data = dbHandler.getAllVillages(SELECTED_WARD_ID);
        //create adapter for spiner
        ArrayAdapter<VillageSpinnerController> adapter= new
                ArrayAdapter<VillageSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_villages.setAdapter(adapter);
    }

    //validate teh spinner inpute
    public void validateSpinners(){


        if(spinner_regions.getCount() == 0 || spinner_districts.getCount() == 0){
            Toast.makeText(getActivity().getApplication(),"PLease Provide all Location Data",Toast.LENGTH_LONG).show();



        }else{
            String region = ((RegionSpinnerController) spinner_regions.getSelectedItem()).getDatabase_id();
            String district = ((DistrictSpinnerController) spinner_districts.getSelectedItem()).getDatabase_id();
          //  String ward = ((WardSpinnerController) spinner_wards.getSelectedItem()).getDatabase_id();
           // String village = ((VillageSpinnerController) spinner_villages.getSelectedItem()).getDatabase_id();


//            eventBus.postSticky(new FarmerLocationEventHandler(
//                    String.valueOf(spinner_regions.getSelectedItem()),
//                    String.valueOf(spinner_districts.getSelectedItem()),
//                    String.valueOf(spinner_wards.getSelectedItem()),
//                    String.valueOf(spinner_villages.getSelectedItem()),
//                    region.toString(),
//                    district.toString(),
//                    ward.toString(),
//                    village.toString()));
            eventBus.postSticky(new FarmerLocationEventHandler(
                    String.valueOf(spinner_regions.getSelectedItem()),
                    String.valueOf(spinner_districts.getSelectedItem()),
                    String.valueOf(spinner_wards.getSelectedItem()),
                    String.valueOf(spinner_villages.getSelectedItem()),
                    region.toString(),
                    district.toString()));

            //Toast.makeText(getActivity().getApplicationContext(),"Hurray Next",Toast.LENGTH_LONG).show();
            registerFarmerViewPager.setCurrentItem(2, true);
            registerFarmerViewPager.activateTab();


        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fab_region_next:
                validateSpinners();
                break;

            case R.id.fab_region_previous:
                registerFarmerViewPager.setCurrentItem(-1, true);
                break;

//            case R.id.btn_add_region:
//                createRegionDialog();
//                break;
//            case R.id.btn_add_district:
//                createDistrictDialog();
//                break;

//            case R.id.btn_add_ward:
//                createWardDialog();
//                break;
//            case R.id.btn_add_village:
//                createVillageDialog();
//                break;

        }
    }

    //create new Region
    private void createRegionDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View regionView = layoutInflater.inflate(R.layout.add_region_layout,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(regionView);

        final EditText regionname = (EditText) regionView.findViewById(R.id.txt_regionname);

        //set dialog message
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // Toast.makeText(context.getApplicationContext(),
                             //   "PYOOO",Toast.LENGTH_LONG).show();
                        //create ward
                        RegionModel regionModel = new RegionModel();
                        dbHandler = new DBHandler(context);

                        String region = regionname.getText().toString().trim().toUpperCase();

                        if(region.isEmpty() || region.length()< 3){
                            Toast.makeText(context, "ERROR!!:Invalid Region Name", Toast.LENGTH_SHORT).show();
                        }else{
                            regionModel.setName(region);
                            dbHandler.createRegion(regionModel);
                            loadRegions();//rerload the spinner

                        }


                    }
                }).setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


    //create new Region
    private void createDistrictDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View districtview = layoutInflater.inflate(R.layout.add_district_layout,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(districtview);

        final EditText districtname = (EditText) districtview.findViewById(R.id.txt_district_name);
        final Spinner spinner_district = (Spinner) districtview.findViewById(R.id.spinner_did);

        //set dialog message
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context.getApplicationContext(),
//                                "DYOO",Toast.LENGTH_LONG).show();
                        DistrictModel districtModel = new DistrictModel();
                        dbHandler = new DBHandler(context);

                        String district = districtname.getText().toString().trim().toUpperCase();
                        String regionId = ((RegionSpinnerController) spinner_district.getSelectedItem()).getDatabase_id();

                        if(district.isEmpty()){
                            Toast.makeText(context.getApplicationContext(),"Invalid Input!All fields required.",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            districtModel.setDistrictname(district);
                            districtModel.setRegionId(regionId);

                            dbHandler.createDistrict(districtModel);
                            loadDistricts();//reload
                        }


                    }
                }).setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


        //load  Region handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<RegionSpinnerController> data = dbHandler.getAllRegions();
        //create adapter for spiner
        ArrayAdapter<RegionSpinnerController> adapter= new
                ArrayAdapter<RegionSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_district.setAdapter(adapter);
    }

    //create new Region
    private void createVillageDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View districtview = layoutInflater.inflate(R.layout.add_village_layout,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(districtview);

        final EditText villagename = (EditText) districtview.findViewById(R.id.txt_village);
        final Spinner spinner_village = (Spinner) districtview.findViewById(R.id.spinner_villageid);

        //set dialog message
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context.getApplicationContext(),
//                                "Villa",Toast.LENGTH_LONG).show();
                        VillageModel villageModel = new VillageModel();
                        dbHandler =new DBHandler(context);

                        String village = villagename.getText().toString().trim().toUpperCase();
                        String ward = ((WardSpinnerController) spinner_village.getSelectedItem()).getDatabase_id();

                        if(village.isEmpty() || village.length()<3){
                            Toast.makeText(context.getApplicationContext(),"Invalid Input!All fields required.",
                                    Toast.LENGTH_LONG).show();

                        }else{
                            villageModel.setVillagename(village);
                            villageModel.setWardId(ward);

                            dbHandler.createVillage(villageModel);
                            loadVillages();
                        }

                    }
                }).setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<WardSpinnerController> data = dbHandler.getWard();
        //create adapter for spiner
        ArrayAdapter<WardSpinnerController> adapter= new
                ArrayAdapter<WardSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_village.setAdapter(adapter);
    }


    //create new Region
    private void createWardDialog(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View districtview = layoutInflater.inflate(R.layout.add_ward_layout,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(districtview);

        final EditText wardname = (EditText) districtview.findViewById(R.id.txt_ward);
        final Spinner spinner_ward = (Spinner) districtview.findViewById(R.id.spinner_wardid);

        //set dialog message
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context.getApplicationContext(),
//                                "Waoo",Toast.LENGTH_LONG).show();
                        WardModel wardModel = new WardModel();
                        dbHandler = new DBHandler(context);

                        String ward = wardname.getText().toString().trim().toUpperCase();
                        String districtId = ((DistrictSpinnerController) spinner_ward.getSelectedItem()).getDatabase_id();

                        if(ward.isEmpty()){
                            Toast.makeText(context.getApplicationContext(),"Invalid Input!All fields required.",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            wardModel.setWardname(ward);
                            wardModel.setDistrictId(districtId);
                            dbHandler.createWard(wardModel);
                        }

                    }
                }).setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        //database handle
        dbHandler = new DBHandler(getActivity());
        //spinner drop down
        List<DistrictSpinnerController> data = dbHandler.getDistricts();
        //create adapter for spiner
        ArrayAdapter<DistrictSpinnerController> adapter= new
                ArrayAdapter<DistrictSpinnerController>(getActivity(),
                android.R.layout.simple_spinner_item, data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ward.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
