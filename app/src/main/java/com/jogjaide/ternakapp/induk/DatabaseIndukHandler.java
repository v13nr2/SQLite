package com.jogjaide.ternakapp.induk;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;
import com.jogjaide.ternakapp.R;

public class DatabaseIndukHandler {
    private static SQLiteDatabase dbinduk;
    private static Cursor crinduk;
    private static int countinduk = 0; //primary key of database

    //Read database schema from raw resources and create the database
    public static void loadDatabase(Context context) {
        dbinduk = context.openOrCreateDatabase("database.sqlite", MODE_PRIVATE, null);
        Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.database));
        String query = "";
        while (scan.hasNextLine()) {
            query += scan.nextLine() + "\n";
            if (query.trim().endsWith(";")) {
                dbinduk.execSQL(query);
                query = "";
            }
        }

        countinduk = (int) DatabaseUtils.queryNumEntries(dbinduk, "ternak_induk");
    }

    //read everything there is in the database
    public static Cursor readDatabase() {

        String query1 = "SELECT * FROM ternak_induk";
        crinduk = dbinduk.rawQuery(query1, null);
        return crinduk;
    }

    // add a new student's record to the database
    public static void addToDatabase(String name, String mobile) {
        countinduk++;
        String sql = "INSERT INTO ternak_induk(id, nomor_ternak, asal) VALUES (" + countinduk + ",'" + name + "','" + mobile + "');";
        dbinduk.execSQL(sql);
    }

    // Search a student's record by name and delete it
    public static int deleteUsingName(String name) {
        String table = "ternak_pejantan";
        String whereClause = "nomor_ternak=?";
        String[] whereArgs = new String[]{name};
        int deleted = dbinduk.delete(table, whereClause, whereArgs);
        return deleted;
    }

    // Search a student's record by name and updateit
    public static void updateUsingName(String oldName, String newName, String newMobile){
        String sql = "UPDATE ternak_pejantan SET nomor_ternak = '"+newName+"', nama_ternak = '"+newMobile+"' WHERE nomor_ternak = '"+oldName+"';";
        dbinduk.execSQL(sql);
    }
}
