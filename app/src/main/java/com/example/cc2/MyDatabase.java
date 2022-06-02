package com.example.cc2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    public static String DB_NAME="Entreprise.db";
    public static String TABLE_NAME="ENTREPRISE";
    public static String COL1="ID";
    public static String COL2="Raison_Sociale";
    public static String COL3="adresse";
    public static String COL4="Capitale";

    public MyDatabase( Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE_NAME + "("+COL1+" integer primary key autoincrement,"+COL2+" TEXT,"+COL3+" TEXT,"+COL4+" double)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql= "DROP TABLE " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
    public static long insert_entreprise (SQLiteDatabase sqLiteDatabase, Entreprise p){
        ContentValues ct = new ContentValues();
        ct.put(COL2,p.getRaisonSociale());
        ct.put(COL3,p.getAdresse());
        ct.put(COL4,p.getCapitale());

        return sqLiteDatabase.insert(TABLE_NAME,null,ct);
    }

    public static long update_entrprise(SQLiteDatabase sqLiteDatabase, Entreprise p){
        ContentValues ct = new ContentValues();
        ct.put(COL2,p.getRaisonSociale());
        ct.put(COL3,p.getAdresse());
        ct.put(COL4,p.getCapitale());
        return sqLiteDatabase.update(TABLE_NAME,ct,"id="+p.getID(),null);
    }

    public static long delete_entreprise(SQLiteDatabase sqLiteDatabase, int id){
        return sqLiteDatabase.delete(TABLE_NAME,"id="+id,null);
    }

    public static ArrayList<Entreprise> getAllEntreprise(SQLiteDatabase sqLiteDatabase){
        ArrayList<Entreprise> prds = new ArrayList<>();

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        while(cur.moveToNext()){
            Entreprise p = new Entreprise();
            p.setID(cur.getInt(0));
            p.setRaisonSociale(cur.getString(1));
            p.setAdresse(cur.getString(2));
            p.setCapitale(cur.getDouble(3));

            prds.add(p);
        }

        return prds;
    }

    public static Entreprise getOneEntreprise(SQLiteDatabase sqLiteDatabase, int id){
        Entreprise p = null;

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + id,null);

        if(cur.moveToNext()){
            p = new Entreprise();
            p.setID(cur.getInt(0));
            p.setRaisonSociale(cur.getString(1));
            p.setAdresse(cur.getString(2));
            p.setCapitale(cur.getDouble(3));

        }

        return p;
    }
}
