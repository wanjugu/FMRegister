package com.advantech.mobile.fmregister.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import com.advantech.mobile.fmregister.controller.DBHandler;
import com.advantech.mobile.fmregister.view.ReviewTraderData;

public class TraderContentProvider extends ContentProvider {
    //instantiate the db handler
    private DBHandler dbHandler;
    private ReviewTraderData reviewTraderData;
    private String MyMessage;

    private static final String AUTHORITY = "com.advantech.mobile.fmregister.provider.TraderContentProvider";

    public static final String TABLE_TRADERS = "traders";
    public static final Uri CONTENT_URI =Uri.parse ("content://" + AUTHORITY + "/" + TABLE_TRADERS);


    public static final int TRADER = 1;
    public static final int TRADER_ID = 2;

    //URI matcher variables
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    //create a URL matcher
    static {
        sUriMatcher.addURI(AUTHORITY,TABLE_TRADERS,TRADER);
        sUriMatcher.addURI(AUTHORITY,TABLE_TRADERS+"/#",TRADER_ID);


    }


    public TraderContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sUriMatcher.match(uri);


        SQLiteDatabase db = dbHandler.getWritableDatabase();

        long id =0;
        String SUCCESS_MESSAGE = "Traders Registration Sucess";
        String UNKNOWN_ERROR_MESSAGE = "Error: Unknown Error";
        String ERROR_CONSTRAINT = "Error Registering Traders: \n Provided National ID is in use by another Trader";
        switch(uriType){
            case TRADER:

                try{
                    id = db.insertOrThrow(DBHandler.TABLE_TRADERS, null, values);
                    if (id > 0) {
                        Toast.makeText(getContext(), SUCCESS_MESSAGE, Toast.LENGTH_LONG).show();
                        break;
                    }

                }catch (SQLiteConstraintException e){
                    Toast.makeText(getContext(), ERROR_CONSTRAINT, Toast.LENGTH_LONG).show();
                }catch (UnsupportedOperationException uoe){
                    Toast.makeText(getContext(), UNKNOWN_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                }

                break;


            default:
                throw new IllegalArgumentException("Unknown URI: " + uri );

        }//end switch

        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.parse(TABLE_TRADERS + "" + id);
    }

    @Override
    public boolean onCreate() {
        // DO: Implement this to initialize your content provider on startup.
        dbHandler = new DBHandler(getContext());
        reviewTraderData = new ReviewTraderData();
        MyMessage = reviewTraderData.MyMessage;
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
