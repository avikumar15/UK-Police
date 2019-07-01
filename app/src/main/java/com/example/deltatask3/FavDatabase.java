package com.example.deltatask3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class FavDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="FAVOURITE.db";
    public static final String TABLE_NAME="FAVOURITECRIME";
    public static final String PERSISTENCE_ID="PERSISTENCE_ID";
    public static final String CATEGORY="CATEGORY";
    public static final String LATITUDE="LATITUDE";
    public static final String LONGITUDE="LONGITUDE";

    Context c;

    public FavDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table "+TABLE_NAME +"(PERSISTENCE_ID STRING, CATEGORY STRING, LATITUDE STRING PRIMARY KEY, LONGITUDE STRING UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void InsertData(String PERSIS,String CATEG,String LAT,String LONG)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSISTENCE_ID,PERSIS);
        contentValues.put(CATEGORY,CATEG);
        contentValues.put(LATITUDE,LAT);
        contentValues.put(LONGITUDE,LONG);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
        {
            Toast.makeText(c, "Already a favourite crime", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(c,"Added to favourite crimes",Toast.LENGTH_SHORT).show();;
        }
    }

    public Cursor getFavCrimes()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public void DeleteByLat(String LAT)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Toast.makeText(c,"Removed from favourites",Toast.LENGTH_SHORT).show();
        long result = db.delete(TABLE_NAME,"LATITUDE = ?",new String[] {LAT});

        if(result>0)
            Toast.makeText(c,"Deleted from favourites",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(c,"Not found in favourites",Toast.LENGTH_SHORT).show();

    }

}
