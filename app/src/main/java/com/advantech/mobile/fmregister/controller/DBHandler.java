package com.advantech.mobile.fmregister.controller;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.advantech.mobile.fmregister.model.AssocView.ViewAssociationsModel;
import com.advantech.mobile.fmregister.model.AssociationRegistrationModel;
import com.advantech.mobile.fmregister.model.DistrictModel;
import com.advantech.mobile.fmregister.model.FarmersRegistrationModel;
import com.advantech.mobile.fmregister.model.FarmersView.FarmerUpdateModel;
import com.advantech.mobile.fmregister.model.RegionModel;
import com.advantech.mobile.fmregister.model.TraderRegistrationModel;
import com.advantech.mobile.fmregister.model.FarmersView.ViewFarmerModel;
import com.advantech.mobile.fmregister.model.TradersView.TraderUpdateModel;
import com.advantech.mobile.fmregister.model.TradersView.ViewTradersModel;
import com.advantech.mobile.fmregister.model.VillageModel;
import com.advantech.mobile.fmregister.model.WardModel;
import com.advantech.mobile.fmregister.provider.AssociationContentProvider;
import com.advantech.mobile.fmregister.provider.MyContentProvider;
import com.advantech.mobile.fmregister.provider.TraderContentProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ADVANTECH CONSULTING on 4/25/2018.
 */

public class DBHandler extends SQLiteOpenHelper {


    //content r
    private ContentResolver contentResolver;

    public String MyMessage = "";//returned during farmer registration
    public boolean MyError = false;
    Context context;
    private final String TAG = DBHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 7;
    private static final String DBNAME = "jukwaa";

    public static final String TABLE_FARMERS = "farmers";
    public static final String TABLE_REGIONS = "regions";
    public static final String TABLE_DISTRICTS = "districts";
    public static final String TABLE_WARDS = "wards";
    public static final String TABLE_VILLAGES = "villages";
    public static final String TABLE_ASSOCIATIONS = "associations";
    public static final String TABLE_BUSINESSES = "businesses";
    public static final String TABLE_POSITIONS = "positions";
    public static final String TABLE_PRODUCTS = "products";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_TRADERS = "traders";


    /*Define the table columns
    *------TABLE_FARMERS----------------------------------*/
    public static final String COLUMN_FARMER_ID = "id";
    public static final String COLUMN_FARMER_FIRSTNAME = "firstname";
    public static final String COLUMN_FARMER_SURNAME = "surname";
    public static final String COLUMN_FARMER_OTHERNAMES = "othernames";
    public static final String COLUMN_FARMER_PHONE = "phone";
    public static final String COLUMN_FARMER_GENDER = "gender";
    public static final String COLUMN_FARMER_REGION = "region";
    public static final String COLUMN_FARMER_DISTRICT ="district";
    public static final String COLUMN_FARMER_WARD = "ward";
    public static final String COLUMN_FARMER_VILLAGE = "village";
    public static final String COLUMN_FARMER_ASSOCIATION = "association";
    public static final String COLUMN_FARMER_COMMODITIES = "product";
    public static final String COLUMN_FARMER_TIMESTAMP= "modified_date";
    public static final String COLUMN_FARMER_UPDATE_STATUS = "status";
    public static final String COLUMN_FARMER_USER_ID = "user";
    public static final String COLUMN_FARMER_IS_DELETED = "isdeleted";//0-1


    /*REGIONs TABLEs*/
    public static final String COLUMN_REGION_ID = "region_id";
    public static final String COLUMN_REGION_NAME= "region_name";
    public static final String COLUMN_REGION_SYNC_STATUS= "syncsts";

    /*DISTRICTs TABLE*/
    public static final String COLUMN_DISTRICT_ID = "district_id";
    public static final String COLUMN_DISTRICT_NAME= "district_name";
    public static final String COLUMN_DISTRICT_REGION_ID= "region_id";

    /*WARDs TABLE*/
    public static final String COLUMN_WARD_ID = "ward_id";
    public static final String COLUMN_WARD_NAME= "ward_name";
    public static final String COLUMN_WARD_DISTRICT_ID= "district_id";

    /*Villages TABLE*/
    public static final String COLUMN_VILLAGE_ID = "village_id";
    public static final String COLUMN_VILLAGE_NAME= "village_name";
    public static final String COLUMN_VILLAGE_WARD_ID= "ward_id";

    //products TABLE
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_NAME= "product_name";


    // business_type
    public static final String COLUMN_BUSINESSTYPE_ID = "businesstype_id";
    public static final String COLUMN_BUSINESSTYPE_NAME= "businesstype_name";

    //ASSOCIATIO TABLE
    public static final String COLUMN_ASSOCIATION_ID = "association_id";
    public static final String COLUMN_ASSOCIATION_FIRSTNAME = "firstname";
    public static final String COLUMN_ASSOCIATION_SURNAME = "surname";
    public static final String COLUMN_ASSOCIATION_OTHERNAME = "othername";
    public static final String COLUMN_ASSOCIATION_NAME= "association_name";
    public static final String COLUMN_ASSOC_MOBILENO= "mobileno";
    public static final String COLUMN_ASSOC_COMMODITIES= "commodities";
    public static final String COLUMN_ASSOC_REGION= "region";
    public static final String COLUMN_ASSOC_DISTRICT= "district";
    public static final String COLUMN_ASSOC_WARD= "ward";

    public static final String COLUMN_ASSOC_CREATED_AT= "created";
    public static final String COLUMN_ASSOC_USER_ID= "user_id";
    public static final String COLUMN_ASSOC_SYNC_STATUS= "syncstatus";
    public static final String COLUMN_ASSOC_ISDELETED= "isdeleted";



    //POSITION TABLE
    public static final String COLUMN_POSITION_ID = "position_id";
    public static final String COLUMN_POSITION_NAME= "position_name";

    //Table user
    public static final String COLUMN_USER_ID= "user_id";
    public static final String COLUMN_USER_NAME= "user_name";
    public static final String COLUMN_USER_PASSWORD= "password";
    public static final String COLUMN_USER_EMAIL= "email";

    //Table Traders
    public static final String COLUMN_TRADER_ID= "trader_id";
    public static final String COLUMN_TRADER_FIRSTNAME= "firstname";
    public static final String COLUMN_TRADER_SURNAME= "surname";
    public static final String COLUMN_TRADER_OTHERNAME= "othername";
    public static final String COLUMN_TRADER_MOBILE_NO= "mobile";
    public static final String COLUMN_TRADER_GENDER= "gender";
    public static final String COLUMN_TRADER_REGION= "region";
    public static final String COLUMN_TRADER_DISTRICT= "district";
    public static final String COLUMN_TRADER_WARD= "ward";
    public static final String COLUMN_TRADER_VILLAGE= "village";
    public static final String COLUMN_TRADER_BUSINESSNAME= "businessname";
    public static final String COLUMN_TRADER_ASSOCIATION= "association_name";

    public static final String COLUMN_TRADER_COMMODITIES= "commodities";
    public static final String COLUMN_TRADER_CREATED_AT= "createdate";
    public static final String COLUMN_TRADER_SYNC_STATUS= "syncstatus";
    public static final String COLUMN_TRADER_ISDELETED= "isDeleted";
    public static final String COLUMN_TRADER_USERID= "user_id";




    private static DBHandler instance;



    public void open() throws SQLException {
        close();
        this.getWritableDatabase();
        //  SQLiteDatabase database = this.getWritableDatabase();
        // database.isOpen();
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    //Actiate the foreign key handler
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public DBHandler(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
        contentResolver = context.getContentResolver();
    }

   /*------TABLE_TRADERS----------------------------------*/
   /*------TABLE_ASSOCIATION----------------------------------*/






    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FARMERS_TABLE = "CREATE TABLE " + TABLE_FARMERS + "("
                + COLUMN_FARMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FARMER_FIRSTNAME + " TEXT NOT NULL, "
                + COLUMN_FARMER_SURNAME + " TEXT, " + COLUMN_FARMER_OTHERNAMES + " TEXT, "
                + COLUMN_FARMER_PHONE + " TEXT NOT NULL, "
                + COLUMN_FARMER_GENDER + " TEXT NOT NULL, " + COLUMN_FARMER_REGION + " INTEGER NOT NULL, "
                + COLUMN_FARMER_DISTRICT + " INTEGER NOT NULL, " + COLUMN_FARMER_WARD + " INTEGER NOT NULL, "
                + COLUMN_FARMER_VILLAGE+ " INTEGER NOT NULL, " + COLUMN_FARMER_ASSOCIATION + " INTEGER NOT NULL, "
                + COLUMN_FARMER_COMMODITIES + " TEXT NOT NULL, " + COLUMN_FARMER_TIMESTAMP + " TEXT NOT NULL, "
                + COLUMN_FARMER_UPDATE_STATUS + " INTEGER NOT NULL, "
                + COLUMN_FARMER_USER_ID + " INTEGER NOT NULL, " + COLUMN_FARMER_IS_DELETED + " INTEGER NOT NULL, " +


                " FOREIGN KEY(user) REFERENCES users(user_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(region) REFERENCES regions(region_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(district) REFERENCES districts(district_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(ward) REFERENCES wards(ward_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(village) REFERENCES villages(village_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(association) REFERENCES associations(association_id) ON DELETE RESTRICT ON UPDATE CASCADE " +

                ")";


        //Table Region
        String CREATE_TRADERS_TABLE = " CREATE TABLE " + TABLE_TRADERS + "("
                + COLUMN_TRADER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TRADER_FIRSTNAME + " TEXT, " + COLUMN_TRADER_SURNAME + " TEXT, "
                + COLUMN_TRADER_OTHERNAME + " TEXT, "
                + COLUMN_TRADER_MOBILE_NO + " TEXT, " + COLUMN_TRADER_GENDER + " TEXT, "
                + COLUMN_TRADER_REGION + " INTEGER, " + COLUMN_TRADER_DISTRICT + " INTEGER, "
                + COLUMN_TRADER_WARD + " INTEGER, " + COLUMN_TRADER_VILLAGE + " INTEGER, "
                + COLUMN_TRADER_BUSINESSNAME + " TEXT, "
                + COLUMN_TRADER_ASSOCIATION + " INTEGER, "
               + COLUMN_TRADER_COMMODITIES + " TEXT, "
                + COLUMN_TRADER_CREATED_AT + " TEXT, " + COLUMN_TRADER_ISDELETED + " INTEGER, "
                + COLUMN_TRADER_SYNC_STATUS + " INTEGER, " + COLUMN_TRADER_USERID + " INTEGER, " +



                " FOREIGN KEY(region) REFERENCES regions(region_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(district) REFERENCES districts(district_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(ward) REFERENCES wards(ward_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(village) REFERENCES villages(village_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                ")";


        //create region
        String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_EMAIL + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT)";
        //create region
        String CREATE_REGION_TABLE = " CREATE TABLE " + TABLE_REGIONS + "("
                        + COLUMN_REGION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_REGION_NAME + " TEXT)";

        //create Districts
        String CREATE_DISTRICT_TABLE = " CREATE TABLE " + TABLE_DISTRICTS + "("
                + COLUMN_DISTRICT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DISTRICT_NAME + " TEXT, "
                + COLUMN_DISTRICT_REGION_ID + " INTEGER, " +
                " FOREIGN KEY(region_id) REFERENCES regions(region_id) ON DELETE RESTRICT ON UPDATE CASCADE " + ")";

        //create Wards
        String CREATE_WARD_TABLE = " CREATE TABLE " + TABLE_WARDS + "("
                + COLUMN_WARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_WARD_NAME + " TEXT, "
                + COLUMN_WARD_DISTRICT_ID + " INTEGER, " +
                " FOREIGN KEY(district_id) REFERENCES districts(district_id) ON DELETE RESTRICT ON UPDATE CASCADE " + ")";

        //create VILLAGE
        String CREATE_VILLAGE_TABLE = " CREATE TABLE " + TABLE_VILLAGES + "("
                + COLUMN_VILLAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_VILLAGE_NAME + " TEXT, "
                + COLUMN_VILLAGE_WARD_ID + " INTEGER, " +
                " FOREIGN KEY(ward_id) REFERENCES wards(ward_id) ON DELETE RESTRICT ON UPDATE CASCADE "+ ")";

        //create ASSOCIATION
        String CREATE_ASSOCIATION_TABLE = " CREATE TABLE " + TABLE_ASSOCIATIONS + "("
                + COLUMN_ASSOCIATION_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ASSOCIATION_NAME + " TEXT,"  + COLUMN_ASSOCIATION_FIRSTNAME + " TEXT,"
                + COLUMN_ASSOCIATION_SURNAME + " TEXT,"  + COLUMN_ASSOCIATION_OTHERNAME + " TEXT,"
                + COLUMN_ASSOC_MOBILENO + " TEXT, "
                + COLUMN_ASSOC_COMMODITIES + " TEXT, " + COLUMN_ASSOC_REGION + " INTEGER, "
                + COLUMN_ASSOC_DISTRICT + " INTEGER, "+ COLUMN_ASSOC_WARD + " INTEGER, "
                + COLUMN_ASSOC_CREATED_AT + " TEXT, " + COLUMN_ASSOC_USER_ID + " INTEGER, "
                + COLUMN_ASSOC_SYNC_STATUS + " INTEGER, " + COLUMN_ASSOC_ISDELETED + " INTEGER, " +


                " FOREIGN KEY(region) REFERENCES regions(region_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(district) REFERENCES districts(district_id) ON DELETE RESTRICT ON UPDATE CASCADE " +
                " FOREIGN KEY(ward) REFERENCES wards(ward_id) ON DELETE RESTRICT ON UPDATE CASCADE " +

                ")";



        //create ASSOCIATION
        String CREATE_BUSINESSTYPE_TABLE = " CREATE TABLE " + TABLE_BUSINESSES + "("
                + COLUMN_BUSINESSTYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BUSINESSTYPE_NAME + " TEXT)";


        //create POSITIONS
        String CREATE_POSITIONS_TABLE = " CREATE TABLE " + TABLE_POSITIONS + "("
                + COLUMN_POSITION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_POSITION_NAME + " TEXT)";

        //create POSITIONS
        String CREATE_PRODUCTS_TABLE = " CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRODUCT_NAME + " TEXT)";





        db.execSQL(CREATE_REGION_TABLE);
        Log.d("FDBCREATION", "Table REGION creation sucess");

        db.execSQL(CREATE_DISTRICT_TABLE);
        Log.d("FDBCREATION", "Table DISTRICT creation sucess");

        db.execSQL(CREATE_WARD_TABLE);
        Log.d("FDBCREATION", "Table WARD creation sucess");

        db.execSQL(CREATE_VILLAGE_TABLE);
        Log.d("FDBCREATION", "Table VILLAGE creation sucess");

        db.execSQL(CREATE_ASSOCIATION_TABLE);
        Log.d("FDBCREATION", "Table ASSOCIATION creation sucess");

        db.execSQL(CREATE_BUSINESSTYPE_TABLE);
        Log.d("FDBCREATION", "Table BusinessType creation sucess");

        db.execSQL(CREATE_POSITIONS_TABLE);
        Log.d("FDBCREATION", "Table POSITION creation sucess");

        db.execSQL(CREATE_PRODUCTS_TABLE);
        Log.d("FDBCREATION", "Table PRODUCTS creation sucess");

        db.execSQL(CREATE_USER_TABLE);
        Log.d("FDBCREATION", "Table USER creation sucess");

        db.execSQL(CREATE_FARMERS_TABLE);
        Log.d("FDBCREATION", "Table FARMER creation sucess");

        db.execSQL(CREATE_TRADERS_TABLE);
        Log.d("TFDBCREATION", "Table TRADER creation sucess");


        //turn on the foreign keys
        db.execSQL("PRAGMA foreign_keys=ON;");

        db.execSQL(" INSERT INTO " + TABLE_USERS + " VALUES(1,'admin','admin@mail.com','admin')");


        db.execSQL(" INSERT INTO " + TABLE_REGIONS + " VALUES(3,'Morogoro')");


        db.execSQL(" INSERT INTO " + TABLE_DISTRICTS + " VALUES(12,'Kilombero',3)");



        db.execSQL(" INSERT INTO " + TABLE_WARDS + " VALUES(3,'Baraa',12)");
        db.execSQL(" INSERT INTO " + TABLE_WARDS + " VALUES(4,'Barege',12)");
        db.execSQL(" INSERT INTO " + TABLE_WARDS + " VALUES(6,'Chisano ',12)");

        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(1,'Isikuti 1',3)");
        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(2,'Manyatta 2',3)");
        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(3,'Manyatta 3',3)");

        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(4,'Isikuti 1',4)");
        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(5,'Manyatta 2',4)");
        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(6,'Manyatta 3',4)");

        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(7,'Isikuti 1',6)");
        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(8,'Manyatta 2',6)");
        db.execSQL(" INSERT INTO " + TABLE_VILLAGES + " VALUES(9,'Manyatta 3',6)");






        db.execSQL(" INSERT INTO " + TABLE_BUSINESSES + " VALUES(1,'Personal')");
        db.execSQL(" INSERT INTO " + TABLE_BUSINESSES + " VALUES(2,'Family Business')");
        db.execSQL(" INSERT INTO " + TABLE_BUSINESSES + " VALUES(3,'Sacco')");

        db.execSQL(" INSERT INTO " + TABLE_POSITIONS + " VALUES(1,'Farmer')");
        db.execSQL(" INSERT INTO " + TABLE_POSITIONS + " VALUES(2,'Board Member')");
        db.execSQL(" INSERT INTO " + TABLE_POSITIONS + " VALUES(3,'Accountant Member')");

        db.execSQL(" INSERT INTO " + TABLE_PRODUCTS + " VALUES(1,'Mpunga')");
        db.execSQL(" INSERT INTO " + TABLE_PRODUCTS + " VALUES(2,'Maize')");

    }//end oncreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTRICTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WARDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VILLAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSOCIATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUSINESSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSITIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FARMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRADERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);


    }


    //Fetch Regions
    //method to fetch all the Regions from db
    public List<RegionSpinnerController> getAllRegions(){
        List<RegionSpinnerController> item = new ArrayList<RegionSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_REGIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new RegionSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }

    //method to fetch all the Districts from db
    public List<DistrictSpinnerController> getAllDisstricts(int id){
        List<DistrictSpinnerController> item = new ArrayList<DistrictSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_DISTRICTS  + " WHERE " + COLUMN_DISTRICT_REGION_ID + "=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new DistrictSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }//end method

    //method to fetch all the Wards from db
    public List<WardSpinnerController> getAllWards(int id){
        List<WardSpinnerController> item = new ArrayList<WardSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_WARDS  + " WHERE " + COLUMN_WARD_DISTRICT_ID + "=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new WardSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }//end Method

    //method to fetch all the Wards from db
    public List<VillageSpinnerController> getAllVillages(int id){
        List<VillageSpinnerController> item = new ArrayList<VillageSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_VILLAGES + " WHERE " + COLUMN_VILLAGE_WARD_ID + "=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new VillageSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }//end Method

    //method to fetch all the BusinessType from db
    public List<VillageSpinnerController> getVillages(){
        List<VillageSpinnerController> item = new ArrayList<VillageSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_VILLAGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new VillageSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }

    //method to fetch all the BusinessType from db
    public List<DistrictSpinnerController> getDistricts(){
        List<DistrictSpinnerController> item = new ArrayList<DistrictSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_DISTRICTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new DistrictSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }

    //method to fetch all the BusinessType from db
    public List<WardSpinnerController> getWard(){
        List<WardSpinnerController> item = new ArrayList<WardSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_WARDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new WardSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }


    //method to fetch all the BusinessType from db
    public List<BusinessTypeSpinnerController> getAllBusinessType(){
        List<BusinessTypeSpinnerController> item = new ArrayList<BusinessTypeSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_BUSINESSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new BusinessTypeSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }

    //method to fetch all the Association from db
    public List<AssociationSpinnerController> getAllAssociations(){
        List<AssociationSpinnerController> item = new ArrayList<AssociationSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_ASSOCIATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new AssociationSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }

    //method to fetch all the Association from db
    public List<PositionSpinnerController> getAllPositions(){
        List<PositionSpinnerController> item = new ArrayList<PositionSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_POSITIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new PositionSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }

    //method to fetch all the products from db
    public List<ProductSpinnerController> getAllProducts(){
        List<ProductSpinnerController> item = new ArrayList<ProductSpinnerController>();
        //query
        String Query = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst()) {
            do {
                // item.add(cursor.getString(0));
                item.add(new ProductSpinnerController(cursor.getString(0),
                        cursor.getString(1)));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        Log.d(TAG,"Retrieving: "+ item.toString());
        return item;

    }

    //insert FARMER REGISTRATIO DATA TO THE DATABASE
    public void RegisterFarmer(FarmersRegistrationModel model) {
        //SQLiteDatabase db = this.getWritableDatabase();
        // db.beginTransaction();

        try {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_FARMER_FIRSTNAME, model.getFirstname());
            cv.put(COLUMN_FARMER_SURNAME, model.getSurname());
            cv.put(COLUMN_FARMER_OTHERNAMES, model.getOthernames());
            cv.put(COLUMN_FARMER_PHONE, model.getPhoneno());
            cv.put(COLUMN_FARMER_GENDER, model.getGender());

            cv.put(COLUMN_FARMER_REGION, model.getRegion());
            cv.put(COLUMN_FARMER_DISTRICT, model.getDistrict());
            cv.put(COLUMN_FARMER_WARD, 3);
            cv.put(COLUMN_FARMER_VILLAGE, 1);


            cv.put(COLUMN_FARMER_ASSOCIATION, model.getAssoc_name());
            cv.put(COLUMN_FARMER_COMMODITIES, model.getCommodity());

            cv.put(COLUMN_FARMER_TIMESTAMP, model.getmTimestamp());
            cv.put(COLUMN_FARMER_UPDATE_STATUS, model.getUpdatestatus());
            cv.put(COLUMN_FARMER_USER_ID, model.getUser_id());
            cv.put(COLUMN_FARMER_IS_DELETED,model.getIsdeleted());


            contentResolver.insert(MyContentProvider.CONTENT_URI, cv);


            //db.setTransactionSuccessful();
            Log.e(TAG, "Farmer Data Inserted Sucessfully" + cv.toString());

            //MyMessage = "Record AA Inserted Sucessfully";

            //  return MyMessage ;


        } catch (android.database.SQLException e) {

            e.printStackTrace();
            Log.e("FARMER INSERT", e.getMessage().toString());


            //check for Primary Key Constraint
            if (e instanceof SQLiteConstraintException) {
                MyMessage = " Error Registering Farmer: \n Provided National ID is in use by another Farmer";
                String aaa =e.getMessage();
                Log.e("CONSTRAINT ERROR",aaa);
                MyError = true;
                //return MyMessage;
            } else {

                MyMessage = e.getMessage().toString();
                MyError = true;


                //  return MyMessage;
            }


            //}


        }


    }


    //insert Traderss REGISTRATIO DATA TO THE DATABASE
    public void RegisterTrader(TraderRegistrationModel model) {
        //SQLiteDatabase db = this.getWritableDatabase();
        // db.beginTransaction();

        try {
            ContentValues cv = new ContentValues();
           // cv.put(COLUMN_TRADER_ID,model.getNationalid());
            cv.put(COLUMN_TRADER_ASSOCIATION,model.getAssociation_id());
            cv.put(COLUMN_TRADER_FIRSTNAME, model.getFirstname());
            cv.put(COLUMN_TRADER_SURNAME, model.getSurname());
            cv.put(COLUMN_TRADER_OTHERNAME, model.getOthernames());
            cv.put(COLUMN_TRADER_MOBILE_NO, model.getPhoneno());
            cv.put(COLUMN_TRADER_GENDER, model.getGender());

            cv.put(COLUMN_TRADER_REGION, model.getRegion());
            cv.put(COLUMN_TRADER_DISTRICT, model.getDistrict());
            cv.put(COLUMN_TRADER_WARD, model.getWard());
            cv.put(COLUMN_TRADER_VILLAGE, model.getVillage());
            //cv.put(COLUMN_TRADER_BUSINESSNAME,model.getBusinessname());
           // cv.put(COLUMN_TRADER_COMMODITIES,model.getCommodity());

            cv.put(COLUMN_TRADER_CREATED_AT, model.getmTimestamp());
            cv.put(COLUMN_TRADER_SYNC_STATUS, 0);
            cv.put(COLUMN_TRADER_USERID, model.getUser_id());
            cv.put(COLUMN_TRADER_ISDELETED,model.getIsdeleted());


            contentResolver.insert(TraderContentProvider.CONTENT_URI, cv);

            //db.setTransactionSuccessful();
            Log.e(TAG, "Trader Data Inserted Sucessfully" + cv.toString());

            //MyMessage = "Record AA Inserted Sucessfully";

            //  return MyMessage ;


        } catch (android.database.SQLException e) {

            e.printStackTrace();
            Log.e("Trader INSERT", e.getMessage().toString());


            //check for Primary Key Constraint
            if (e instanceof SQLiteConstraintException) {
                MyMessage = " Error Registering Trader: \n Provided National ID is in use by another Trader";
                Log.e("SQLERROR!!!!",e.getMessage().toString());
                MyError = true;
                //return MyMessage;
            } else {

                MyMessage = e.getMessage().toString();
                Log.e("SQLERROR!!!!",e.getMessage().toString());
                MyError = true;


                //  return MyMessage;
            }


            //}


        }


    }//end method

    //insert Association REGISTRATIO DATA TO THE DATABASE
    public void RegisterAssociation(AssociationRegistrationModel model) {
        //SQLiteDatabase db = this.getWritableDatabase();
        // db.beginTransaction();

        try {
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_ASSOCIATION_NAME,model.getAssoc_name());
            cv.put(COLUMN_ASSOCIATION_FIRSTNAME,model.getAssoc_firstname());
            cv.put(COLUMN_ASSOCIATION_SURNAME,model.getAssoc_surname());
            cv.put(COLUMN_ASSOCIATION_OTHERNAME,model.getAssoc_other_name());
            cv.put(COLUMN_ASSOC_MOBILENO,model.getAssoc_mobile());

            cv.put(COLUMN_ASSOC_COMMODITIES,model.getAssoc_commodities());
            cv.put(COLUMN_ASSOC_REGION,model.getRegion());
            cv.put(COLUMN_ASSOC_DISTRICT,model.getDistrict());
            cv.put(COLUMN_ASSOC_WARD,model.getWard());


            cv.put(COLUMN_ASSOC_CREATED_AT,model.getmTimestamp());
            cv.put(COLUMN_ASSOC_USER_ID,model.getUser_id());
            cv.put(COLUMN_ASSOC_SYNC_STATUS,model.getSyncStatus());
            cv.put(COLUMN_ASSOC_ISDELETED,model.getIsdeleted());



            contentResolver.insert(AssociationContentProvider.CONTENT_URI, cv);

            //db.setTransactionSuccessful();
            Log.e(TAG, "ASSOCIATION Data Inserted Sucessfully" + cv.toString());

            //MyMessage = "Record AA Inserted Sucessfully";

            //  return MyMessage ;


        } catch (android.database.SQLException e) {

            e.printStackTrace();
            Log.e("ASSOCIATION INSERT", e.getMessage().toString());


            //check for Primary Key Constraint
            if (e instanceof SQLiteConstraintException) {
                MyMessage = " Error Registering Trader: \n Provided ID is in use by another Trader";
                MyError = true;
                //return MyMessage;
            } else {

                MyMessage = e.getMessage().toString();
                MyError = true;


                //  return MyMessage;
            }

        }


    }//end Method


    //CREATE NEW DISTRICT
    public void createRegion(RegionModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.beginTransaction();

        try {
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_REGION_NAME,model.getName());
            db.insert(TABLE_REGIONS,null,cv);
            db.close();

            //db.setTransactionSuccessful();
            Log.e(TAG, "Region Created  Sucessfully" + cv.toString());

            //MyMessage = "Record AA Inserted Sucessfully";

            //  return MyMessage ;


        } catch (android.database.SQLException e) {

            e.printStackTrace();
            Log.e("Region Create INSERT", e.getMessage().toString());


            //check for Primary Key Constraint
            if (e instanceof SQLiteConstraintException) {
                MyMessage = " Error Creating Region: \n ID COntsraint Error";
                MyError = true;
                //return MyMessage;
            } else {

                MyMessage = e.getMessage().toString();
                MyError = true;


                //  return MyMessage;
            }

        }


    }//end Method

    //insert Association REGISTRATIO DATA TO THE DATABASE
    public void createDistrict(DistrictModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.beginTransaction();

        try {
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_DISTRICT_NAME,model.getDistrictname());
            cv.put(COLUMN_DISTRICT_REGION_ID,model.getRegionId());
            db.insert(TABLE_DISTRICTS,null,cv);
            db.close();

            //db.setTransactionSuccessful();
            Log.e(TAG, "District Created  Sucessfully" + cv.toString());

            //MyMessage = "Record AA Inserted Sucessfully";

            //  return MyMessage ;


        } catch (android.database.SQLException e) {

            e.printStackTrace();
            Log.e("District Create INSERT", e.getMessage().toString());


            //check for Primary Key Constraint
            if (e instanceof SQLiteConstraintException) {
                MyMessage = " Error Creating District: \n ID COntsraint Error";
                MyError = true;
                //return MyMessage;
            } else {

                MyMessage = e.getMessage().toString();
                MyError = true;


                //  return MyMessage;
            }

        }


    }//end Method

    //insert Association REGISTRATIO DATA TO THE DATABASE
    public void createWard(WardModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.beginTransaction();

        try {
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_WARD_NAME,model.getWardname());
            cv.put(COLUMN_WARD_DISTRICT_ID,model.getDistrictId());
            db.insert(TABLE_WARDS,null,cv);
            db.close();

            //db.setTransactionSuccessful();
            Log.e(TAG, "District Created  Sucessfully" + cv.toString());

            //MyMessage = "Record AA Inserted Sucessfully";

            //  return MyMessage ;


        } catch (android.database.SQLException e) {

            e.printStackTrace();
            Log.e("District Create INSERT", e.getMessage().toString());


            //check for Primary Key Constraint
            if (e instanceof SQLiteConstraintException) {
                MyMessage = " Error Creating District: \n ID COntsraint Error";
                MyError = true;
                //return MyMessage;
            } else {

                MyMessage = e.getMessage().toString();
                MyError = true;


                //  return MyMessage;
            }

        }


    }//end Method

    //insert Association REGISTRATIO DATA TO THE DATABASE
    public void createVillage(VillageModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.beginTransaction();

        try {
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_VILLAGE_NAME,model.getVillagename());
            cv.put(COLUMN_VILLAGE_WARD_ID,model.getWardId());
            db.insert(TABLE_VILLAGES,null,cv);
            db.close();

            //db.setTransactionSuccessful();
            Log.e(TAG, "District Created  Sucessfully" + cv.toString());

            //MyMessage = "Record AA Inserted Sucessfully";

            //  return MyMessage ;


        } catch (android.database.SQLException e) {

            e.printStackTrace();
            Log.e("District Create INSERT", e.getMessage().toString());


            //check for Primary Key Constraint
            if (e instanceof SQLiteConstraintException) {
                MyMessage = " Error Creating District: \n ID COntsraint Error";
                MyError = true;
                //return MyMessage;
            } else {

                MyMessage = e.getMessage().toString();
                MyError = true;


                //  return MyMessage;
            }

        }


    }//end Method

    /**
     * This method to check user exist or not
     *
     * @param
     * @param password
     * @return true/false
     */
    public boolean checkUser(String username, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /*Methyod to get farmer list
     * see implementation*/
    public List<ViewFarmerModel> getAllFarmersModel(){
        List<ViewFarmerModel> farmerdetails = new ArrayList<>();
//        String select_query = "SELECT " + COLUMN_FARMER_ID + "," + COLUMN_FARMER_FIRSTNAME + "," + COLUMN_FARMER_SURNAME
//                + "," + COLUMN_FARMER_OTHERNAMES + "," + COLUMN_FARMER_PHONE + "," + COLUMN_FARMER_GENDER + ","
//                + "farmers." + COLUMN_FARMER_REGION + "," + "farmers." + COLUMN_FARMER_DISTRICT + ","
//                + "farmers." + COLUMN_FARMER_WARD + "," + "farmers." + COLUMN_FARMER_VILLAGE + "," +
//                 "farmers." +  COLUMN_FARMER_ASSOCIATION + ","
//                 + COLUMN_FARMER_COMMODITIES + "," + COLUMN_FARMER_UPDATE_STATUS + ","
//                + "regions." + COLUMN_REGION_NAME + "," + "districts." + COLUMN_DISTRICT_NAME + ","
//                + "wards." + COLUMN_WARD_NAME + "," + "villages." + COLUMN_VILLAGE_NAME + ","
//                + "associations." + COLUMN_ASSOCIATION_NAME +
//                " FROM " + TABLE_FARMERS +
//                " INNER JOIN regions ON farmers.region = regions.region_id " +
//                " INNER JOIN districts ON farmers.district = districts.district_id " +
//                " INNER JOIN wards ON farmers.ward = wards.ward_id " +
//                " INNER JOIN villages ON farmers.village = villages.village_id " +
//                " INNER JOIN associations ON farmers.association = associations.association_id ";
        String select_query = "SELECT " +  "farmers." + COLUMN_FARMER_ID + "," +  "farmers." + COLUMN_FARMER_FIRSTNAME + "," +  "farmers." + COLUMN_FARMER_SURNAME
                + "," +  "farmers." + COLUMN_FARMER_OTHERNAMES + "," +  "farmers." + COLUMN_FARMER_PHONE + "," +  "farmers." + COLUMN_FARMER_GENDER + ","
                + "farmers." + COLUMN_FARMER_REGION + "," + "farmers." + COLUMN_FARMER_DISTRICT + ","
                + "farmers." + COLUMN_FARMER_WARD + "," + "farmers." + COLUMN_FARMER_VILLAGE + "," +
                "farmers." +  COLUMN_FARMER_ASSOCIATION + ","
                + COLUMN_FARMER_COMMODITIES + "," + COLUMN_FARMER_UPDATE_STATUS + ","
                + "regions." + COLUMN_REGION_NAME + "," + "districts." + COLUMN_DISTRICT_NAME + ","
                + "wards." + COLUMN_WARD_NAME + "," + "villages." + COLUMN_VILLAGE_NAME + ","
                + "associations." + COLUMN_ASSOCIATION_NAME +
                " FROM " + TABLE_FARMERS +
                " INNER JOIN regions ON farmers.region = regions.region_id " +
                " INNER JOIN districts ON farmers.district = districts.district_id " +
                " INNER JOIN wards ON farmers.ward = wards.ward_id " +
                " INNER JOIN villages ON farmers.village = villages.village_id " +
                " INNER JOIN associations ON farmers.association = associations.association_id ";



        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);
        if (cursor != null && cursor.getCount() > 0){
            Log.d(TAG, "CURSOR NOT EMPTY!! " + select_query.toString());

        }else{
            Log.d(TAG, "CURSOR IS EMPTY!! " + select_query.toString());
        }


        try{
            if (cursor.moveToFirst()){
                do{
                    ViewFarmerModel model = new ViewFarmerModel();

                    model.setNationalId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FARMER_ID)));
                    model.setFirstname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FARMER_FIRSTNAME)));
                    model.setSurname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FARMER_SURNAME)));
                    model.setOthernames(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FARMER_OTHERNAMES)));
                    model.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FARMER_PHONE)));
                    model.setRegionname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REGION_NAME)));
                    model.setRegionId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FARMER_REGION)));
                    model.setDistrictname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DISTRICT_NAME)));
                    model.setDistrictId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FARMER_DISTRICT)));
                    model.setWardname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WARD_NAME)));
                    model.setWardId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FARMER_WARD)));
                    model.setVillagename(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VILLAGE_NAME)));
                    model.setVillageId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FARMER_VILLAGE)));

                    model.setAssociation_name(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ASSOCIATION_NAME)));
                    model.setAssociation_id(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FARMER_ASSOCIATION)));




                    //model.setProducts(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FARMER_COMMODITIES)));
                    model.setSyncStatus(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FARMER_UPDATE_STATUS)));



                    // viewFarmersModel.setUpdatestatus(cursor.getString(cursor.getColumnIndexOrThrow(FARMERS_UPDATE_STATUS)));

                    farmerdetails.add(model);
                    Log.d("FARMER MODEL", "FETCHING" + farmerdetails.toString());

                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("FARMER MODEL", "ERROR RETRIEVING FARMERS DATA FROM DATABASE"+ e.toString());
        }

        finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }

        }

        return farmerdetails;

    }


    /*Methyod to get farmer list
    * see implementation*/
    public List<ViewTradersModel> getAllTraderModel(){
        List<ViewTradersModel> tradersList = new ArrayList<>();
        String select_query = "SELECT " + COLUMN_TRADER_ID + "," + COLUMN_TRADER_FIRSTNAME + "," +
                COLUMN_TRADER_SURNAME + "," + COLUMN_TRADER_OTHERNAME + ","  +
                COLUMN_TRADER_MOBILE_NO + "," + COLUMN_TRADER_GENDER + "," + "traders." + COLUMN_TRADER_REGION + "," +
                "traders." + COLUMN_TRADER_DISTRICT + "," + "traders." + COLUMN_TRADER_WARD + ","
                + "traders." + COLUMN_TRADER_VILLAGE + "," + COLUMN_TRADER_BUSINESSNAME + "," +
                COLUMN_TRADER_COMMODITIES + "," + COLUMN_TRADER_SYNC_STATUS + ","
                + "regions." + COLUMN_REGION_NAME + "," + "districts." + COLUMN_DISTRICT_NAME + ","
                + "wards." + COLUMN_WARD_NAME + "," + "villages." + COLUMN_VILLAGE_NAME +

                " FROM " + TABLE_TRADERS +
                " INNER JOIN regions ON traders.region = regions.region_id " +
                " INNER JOIN districts ON traders.district = districts.district_id " +
                " INNER JOIN wards ON traders.ward = wards.ward_id " +
                " INNER JOIN villages ON traders.village = villages.village_id ";



        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);
        if (cursor != null && cursor.getCount() > 0){
            Log.d(TAG, "CURSOR NOT EMPTY!! " + select_query.toString());

        }else{
            Log.d(TAG, "CURSOR IS EMPTY!! " + select_query.toString());
        }


        try{
            if (cursor.moveToFirst()){
                do{
                    ViewTradersModel model = new ViewTradersModel();
                     model.setTraderId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_ID)));
                    model.setFirstname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_FIRSTNAME)));
                    model.setSurname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_SURNAME)));
                    model.setOthername(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_OTHERNAME)));

                    model.setMobile(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_MOBILE_NO)));
                    model.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_GENDER)));
                    model.setRegion(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REGION_NAME)));
                    model.setRegionID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRADER_REGION)));
                    model.setDistrict(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DISTRICT_NAME)));
                    model.setDistrictID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRADER_DISTRICT)));
                    model.setWardID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRADER_WARD)));
                    model.setWard(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WARD_NAME)));
                    model.setVillage(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VILLAGE_NAME)));
                    model.setVillageID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRADER_VILLAGE)));
                    model.setBusinessname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_BUSINESSNAME)));
                    //TODO fetch the busim=ness name - currently its set to ID ftrom traderss table
                  //  model.setBusinessTypename(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_BUSINESSNAME)));
                   // model.setCommodity(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRADER_COMMODITIES)));
                    model.setSyncstatus(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRADER_SYNC_STATUS)));


                    // viewFarmersModel.setUpdatestatus(cursor.getString(cursor.getColumnIndexOrThrow(FARMERS_UPDATE_STATUS)));

                    tradersList.add(model);
                    Log.d("TRADER MODEL", "FETCHING" + model.toString());

                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("TRADER MODEL", "ERROR RETRIEVING FARMERS DATA FROM DATABASE"+ e.toString());
        }

        finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }

        }

        return tradersList;

    }


    /*Methyod to get farmer list
    * see implementation*/
    public List<ViewAssociationsModel> getAllAssocModel(){
        List<ViewAssociationsModel> assocList = new ArrayList<>();
        String select_query = "SELECT " + COLUMN_ASSOCIATION_ID + "," + COLUMN_ASSOCIATION_NAME + ","
            + COLUMN_ASSOC_MOBILENO + ","  + COLUMN_ASSOC_COMMODITIES + ","
                + "associations." + COLUMN_ASSOC_REGION + "," + "associations." + COLUMN_ASSOC_DISTRICT + ","
                + "associations." + COLUMN_ASSOC_WARD + ","
                + COLUMN_ASSOC_CREATED_AT + "," + COLUMN_ASSOC_USER_ID + ","
                + COLUMN_ASSOC_SYNC_STATUS + "," + COLUMN_ASSOC_ISDELETED + ","
                + "regions." + COLUMN_REGION_NAME + "," + "districts." + COLUMN_DISTRICT_NAME + ","
                + "wards." + COLUMN_WARD_NAME +

                " FROM " + TABLE_ASSOCIATIONS +
                " INNER JOIN regions ON associations.region = regions.region_id " +
                " INNER JOIN districts ON associations.district = districts.district_id " +
                " INNER JOIN wards ON associations.ward = wards.ward_id";



        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);
        if (cursor != null && cursor.getCount() > 0){
            Log.d(TAG, "CURSOR NOT EMPTY!! " + select_query.toString());

        }else{
            Log.d(TAG, "CURSOR IS EMPTY!! " + select_query.toString());
        }


        try{
            if (cursor.moveToFirst()){
                do{
                    ViewAssociationsModel model = new ViewAssociationsModel();
                    model.setAssoc_ID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ASSOCIATION_ID)));
                    model.setAssoc_name(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ASSOCIATION_NAME)));

                    model.setAssoc_mobile(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_MOBILENO)));


//                    model.setBusinesstypename(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BUSINESSTYPE_NAME)));

                    model.setCommodities(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_COMMODITIES)));

                    model.setRegion(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REGION_NAME)));
                    model.setRegionID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_REGION)));

                    model.setDistrict(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DISTRICT_NAME)));
                    model.setDistrictID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_DISTRICT)));

                    model.setWard(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WARD_NAME)));
                    model.setWardID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_WARD)));


                    model.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_CREATED_AT)));

                    model.setUserID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_USER_ID)));
                    model.setSyncStatuc(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_SYNC_STATUS)));
                    model.setIsDeleted(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ASSOC_ISDELETED)));


                    assocList.add(model);
                    Log.d("ASSOCIATION MODEL", "FETCHING" + model.toString());

                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("ASSOCIATION MODEL", "ERROR RETRIEVING FARMERS DATA FROM DATABASE"+ e.toString());
        }

        finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }

        }

        return assocList;

    }

    public int updateFarmers(FarmerUpdateModel model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FARMER_ID,model.geteNationalId());
//todo set FARMER MY_ASSOC_ID
      //  cv.put(COLUMN_FARMER_ASSOCIATION,model.geteAssociationId());
        cv.put(COLUMN_FARMER_FIRSTNAME,model.geteFirstname());
        cv.put(COLUMN_FARMER_SURNAME,model.geteSurname());
        cv.put(COLUMN_FARMER_OTHERNAMES,model.geteOthernames());

        cv.put(COLUMN_FARMER_PHONE,model.getePhoneno());
        cv.put(COLUMN_FARMER_GENDER,model.geteGender());
        cv.put(COLUMN_FARMER_REGION,model.geteRegion());
        cv.put(COLUMN_FARMER_DISTRICT,model.geteDistrict());
        cv.put(COLUMN_FARMER_WARD,model.geteWard());
        cv.put(COLUMN_FARMER_VILLAGE,model.geteVillage());


        //todo Gender not updating -- update gender field
       cv.put(COLUMN_FARMER_GENDER,model.geteGender());
        //todo set FARMER ASSOC
        cv.put(COLUMN_FARMER_ASSOCIATION,model.geteAssociationId());

        Log.d("UPDATE FARMER: ",cv.toString());



        int action = db.update(TABLE_FARMERS,cv, COLUMN_FARMER_ID + " =?",
                new String[] {String.valueOf(model.geteNationalId())} );

        Log.d("UPDATE FARMER: ", String.valueOf(action));

        //update row
        return action;







    }


    public int updateTrader(TraderUpdateModel model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TRADER_ID,model.geteTraderId());
        cv.put(COLUMN_TRADER_MOBILE_NO,model.geteTrader_phoneno());
        cv.put(COLUMN_TRADER_FIRSTNAME,model.geteTrader_firstname());
        cv.put(COLUMN_TRADER_SURNAME,model.geteTrader_surname());
        cv.put(COLUMN_TRADER_OTHERNAME,model.geteTrader_othername());

        cv.put(COLUMN_TRADER_REGION,model.geteTrader_region());
        cv.put(COLUMN_TRADER_DISTRICT,model.geteTrader_district());
        cv.put(COLUMN_TRADER_WARD,model.geteTrader_ward());
        cv.put(COLUMN_TRADER_VILLAGE,model.geteTrader_village());

        cv.put(COLUMN_TRADER_GENDER,model.geteTrader_gender());


       // cv.put(COLUMN_TRADER_BUSINESSTYPE,model.geteTrader_bsntype());

        //todo get the correct commodity
        cv.put(COLUMN_TRADER_COMMODITIES,"Mpunga");
        cv.put(COLUMN_TRADER_BUSINESSNAME,model.geteTrade_bsnname());


        Log.d("UPDATE Trader: ",cv.toString());

        //update row
        return db.update(TABLE_TRADERS,cv, COLUMN_TRADER_ID + " =?",
                new String[] {String.valueOf(model.geteTraderId())} );
    }


    //get data from the the Farmer sqlitedatabase for JSON SYNC
    public String composeJSONfromSQLite(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM " + TABLE_FARMERS +" WHERE " + COLUMN_FARMER_UPDATE_STATUS + "= 0";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("nationalid", cursor.getString(0));
                map.put("firstname", cursor.getString(1));
                map.put("surname", cursor.getString(2));
                map.put("othernames", cursor.getString(3));
                map.put("dob", cursor.getString(4));
                map.put("phone", cursor.getString(5));
                map.put("gender", cursor.getString(6));
                map.put("region", cursor.getString(7));
                map.put("district", cursor.getString(8));
                map.put("ward", cursor.getString(9));
                map.put("village", cursor.getString(10));
                map.put("myassociation_id", cursor.getString(11));
                map.put("business_type", cursor.getString(12));
                map.put("association", cursor.getString(13));
                map.put("position", cursor.getString(14));
                map.put("product", cursor.getString(15));
                map.put("user", cursor.getString(18));
                map.put("ussd_code", "2145");

                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        //Use GSON to serialize Array List to JSON
        Log.d("GSON TO GSON",gson.toJson(wordList));
        return gson.toJson(wordList);
    }


    /**
     * Get SQLite records that are yet to be Synced
     * @return
     */
    public int dbSyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_FARMERS + " WHERE " + COLUMN_FARMER_UPDATE_STATUS +"=0";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    /**
     * Get Sync status of SQLite
     * @return
     */
    public String getSyncStatus(){
        String msg = null;
        if(this.dbSyncCount() == 0){
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        }else{
            msg = "DB Sync needed\n";
        }
        return msg;
    }

    /**an
     * Update Sync status against each User ID
     * @param id
     * @param status
     */
    public void updateSyncStatus(int id, int status){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "Update " + TABLE_FARMERS + " set status = '"+ status +"' WHERE id="+"'"+ id +"'";
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    /**
     * Update Sync status against each User ID
     * @param id
     * @param status
     */
    public void updatetraderSyncStatus(int id, int status){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "Update " + TABLE_TRADERS + " set syncstatus = '"+ status +"' WHERE trader_id="+"'"+ id +"'";
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


    /**
     * Update Sync status against each User ID
     * @param id
     * @param status
     */
    public void updateAssocSyncStatus(int id, int status,int newId){
        SQLiteDatabase database = this.getWritableDatabase();
        //String updateQuery = "Update " + TABLE_ASSOCIATIONS + " set syncstatus = '"+ status + ","    +"' WHERE association_id="+"'"+ id +"'";
        String updateQuery = "Update " + TABLE_ASSOCIATIONS + " set syncstatus = '"+ status + "'" +  "," + " association_id= '"+ newId + "' WHERE syncstatus=0 AND association_id="+"'"+ id +"'";
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public ArrayList<HashMap<String, String>> fetchFarmersForSync(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        String select_query = "SELECT  * FROM " + TABLE_FARMERS;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);
        if (cursor != null && cursor.getCount() > 0){
            Log.d(TAG, "CURSOR NOT EMPTY!! " + select_query.toString());

        }else{
            Log.d(TAG, "CURSOR IS EMPTY!! " + select_query.toString());
        }


        try{
            if (cursor.moveToFirst()){
                do{
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("nationalid", cursor.getString(0));
                    map.put("firstname", cursor.getString(1));
                    map.put("surname", cursor.getString(2));
                    map.put("othernames", cursor.getString(3));
                    map.put("dob", cursor.getString(4));
                    map.put("phone", cursor.getString(5));
                    map.put("gender", cursor.getString(6));
                    map.put("region", cursor.getString(7));
                    map.put("district", cursor.getString(8));
                    map.put("ward", cursor.getString(9));
                    map.put("village", cursor.getString(10));
                    map.put("myassociation_id", cursor.getString(11));
                    map.put("business_type", cursor.getString(12));
                    map.put("association", cursor.getString(13));
                    map.put("position", cursor.getString(14));
                    map.put("product", cursor.getString(15));
                    map.put("user", cursor.getString(18));
                    wordList.add(map);



                    Log.d("FARMER SYNCH DATA::", "FETCHING" + wordList.toString());
                }while (cursor.moveToNext());
            }


        }catch (Exception e){
            Log.d("FARMER MODEL", "ERROR RETRIEVING FARMERS DATA FROM DATABASE"+ e.toString());
        }

        finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }

        }

        return wordList;

    }

    //get unsynced farmer list
    public Cursor getUnsyncedNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_FARMERS + " WHERE " + COLUMN_FARMER_UPDATE_STATUS + " =0;";
        Cursor c = db.rawQuery(sql, null);
        Log.d("FETCHING FARMER SYNC!!!", sql.toString());

        return c;
    }


    //get unsynced farmer list
    public Cursor getUnsyncedTradersname(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_TRADERS + " WHERE " + COLUMN_TRADER_SYNC_STATUS + " =0;";
        Cursor c = db.rawQuery(sql, null);
        Log.d("FETCHING TRADERS SYNC!!", sql.toString());

        return c;
    }


    //get unsynced farmer list
    public Cursor getUnsyncedAssociationname(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ASSOCIATIONS + " WHERE " + COLUMN_ASSOC_SYNC_STATUS + " =0;";
        Cursor c = db.rawQuery(sql, null);
        Log.d("FETCHING ASSOC SYNC!!", sql.toString());

        return c;
    }


}//end class
