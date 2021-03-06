package com.jogjaide.ternakapp.anak;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;
import com.jogjaide.ternakapp.R;

public class DatabaseAnakHandler {
    private static SQLiteDatabase dbanak;
    private static Cursor cranak;
    private static int countinduk = 0; //primary key of database

    //Read database schema from raw resources and create the database
    public static void loadDatabase(Context context) {
        dbanak = context.openOrCreateDatabase("database.sqlite", MODE_PRIVATE, null);
        Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.database));
        String query = "";
        while (scan.hasNextLine()) {
            query += scan.nextLine() + "\n";
            if (query.trim().endsWith(";")) {
                dbanak.execSQL(query);
                query = "";
            }
        }

        countinduk = (int) DatabaseUtils.queryNumEntries(dbanak, "ternak_anak");
    }

    //read everything there is in the database
    public static Cursor readDatabase() {

        String query1 = "SELECT * FROM ternak_anak";
        cranak = dbanak.rawQuery(query1, null);
        return cranak;
    }

    // add a new student's record to the database
    public static void addToDatabase(String name, String mobile) {
        countinduk++;
        String sql = "INSERT INTO ternak_anak(id, nomor_ternak, tanggal_sapih) VALUES (" + countinduk + ",'" + name + "','" + mobile + "');";
        dbanak.execSQL(sql);
    }

    // Search a student's record by name and delete it
    public static int deleteUsingName(String name) {
        String table = "ternak_pejantan";
        String whereClause = "nomor_ternak=?";
        String[] whereArgs = new String[]{name};
        int deleted = dbanak.delete(table, whereClause, whereArgs);
        return deleted;
    }

    // Search a student's record by name and updateit
    public static void updateUsingName(String oldName, String newName, String newMobile){
        String sql = "UPDATE ternak_pejantan SET nomor_ternak = '"+newName+"', nama_ternak = '"+newMobile+"' WHERE nomor_ternak = '"+oldName+"';";
        dbanak.execSQL(sql);
    }
}
