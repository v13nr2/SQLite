package com.jogjaide.ternakapp.pejantan;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.jogjaide.ternakapp.R;

import java.io.File;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHandler {
    private static SQLiteDatabase db;
    private static Cursor cr,crinduk;
    private static int count = 0; //primary key of database

    private static String DB_PATH = "/data/data/com.jogjaide.ternakapp/databases/";

    public static final String KEY_STUID = "id";
    public static final String KEY_SUB1 = "nomor_ternak";
    public static final String KEY_SUB2 = "nama_ternak";
    public static final String KEY_SUB3= "asal";
    public static final String KEY_SUB4= "foto";
    public static final String KEY_SUB5 = "silsilah_pejantan";
    public static final String KEY_SUB6 = "silsilah_induk";
    public static final String KEY_SUB7 = "hari_dikawinkan";
    public static final String KEY_SUB8 = "tanggal_dikawinkan";
    public static final String KEY_SUB9 = "catatan";

    private static final String DATABASE_NAME = "database.sqlite";
    private static final String DATABASE_MARKSTABLE = "ternak_pejantan";
    private static final int DATABASE_VERSION = 1;


    private boolean checkDatabase(){
        File dbFile = new File(DB_PATH + "database.sqlite");
        if(dbFile.exists()){
            return true;
        }
        else{
            //This'll create the directories you wanna write to, so you
            //can put the DB in the right spot.
            dbFile.getParentFile().mkdirs();
            return false;
        }
    }


    //Read database schema from raw resources and create the database
    public static void loadDatabase(Context context) {
        db = context.openOrCreateDatabase("database.sqlite", MODE_PRIVATE, null);
        Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.database));
        String query = "";
        while (scan.hasNextLine()) {
            query += scan.nextLine() + "\n";
            if (query.trim().endsWith(";")) {
                db.execSQL(query);
                query = "";
            }
        }

        db.execSQL(" CREATE TABLE  IF NOT EXISTS " + DATABASE_MARKSTABLE + " (" +
                KEY_STUID + " TEXT PRIMARY KEY, " +
                KEY_SUB1 + " TEXT NOT NULL, " +
                KEY_SUB2 + " TEXT NOT NULL, " +
                KEY_SUB3 + " TEXT NOT NULL, " +
                KEY_SUB4 + " TEXT NOT NULL, " +
                KEY_SUB5 + " TEXT NOT NULL, " +
                KEY_SUB6 + " TEXT NOT NULL, " +
                KEY_SUB7 + " TEXT NOT NULL, " +
                KEY_SUB8 + " TEXT NOT NULL, " +
                KEY_SUB9 + " TEXT NOT NULL " +
                ");"
        );

        count = (int) DatabaseUtils.queryNumEntries(db, "ternak_pejantan");
    }


    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(" CREATE TABLE  IF NOT EXISTS " + DATABASE_MARKSTABLE + " (" +
                KEY_STUID + " TEXT PRIMARY KEY, " +
                KEY_SUB1 + " TEXT NOT NULL, " +
                KEY_SUB2 + " TEXT NOT NULL, " +
                KEY_SUB3 + " TEXT NOT NULL, " +
                KEY_SUB4 + " TEXT NOT NULL, " +
                KEY_SUB5 + " TEXT NOT NULL, " +
                KEY_SUB6 + " TEXT NOT NULL, " +
                KEY_SUB7 + " TEXT NOT NULL, " +
                KEY_SUB8 + " TEXT NOT NULL, " +
                KEY_SUB9 + " TEXT NOT NULL " +
              ");"
        );
    }

    //read everything there is in the database
    public static Cursor readDatabase() {
        String query1 = "SELECT * FROM ternak_pejantan";
        cr = db.rawQuery(query1, null);
        return cr;
    }


    //read everything there is in the database
    public static Cursor readDatabaseInduk() {
        String query1 = "SELECT * FROM ternak_induk";
        crinduk = db.rawQuery(query1, null);
        return crinduk;
    }

    // add a new student's record to the database
    public static void addToDatabase(String name, String mobile) {
        count++;
        String sql = "INSERT INTO ternak_pejantan(id, nomor_ternak, nama_ternak) VALUES (" + count + ",'" + name + "','" + mobile + "');";
        db.execSQL(sql);
    }

    // Search a student's record by name and delete it
    public static int deleteUsingName(String name) {
        String table = "ternak_pejantan";
        String whereClause = "nomor_ternak=?";
        String[] whereArgs = new String[]{name};
        int deleted = db.delete(table, whereClause, whereArgs);
        return deleted;
    }

    // Search a student's record by name and updateit
    public static void updateUsingName(String oldName, String newName, String newMobile){
        String sql = "UPDATE ternak_pejantan SET nomor_ternak = '"+newName+"', nama_ternak = '"+newMobile+"' WHERE nomor_ternak = '"+oldName+"';";
        db.execSQL(sql);
    }
}
