package com.jogjaide.ternakapp.pejantan;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.jogjaide.ternakapp.R;

import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHandler {
    private static SQLiteDatabase db;
    private static Cursor cr,crinduk;
    private static int count = 0; //primary key of database

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

        count = (int) DatabaseUtils.queryNumEntries(db, "ternak_pejantan");
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
