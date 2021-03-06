package com.jogjaide.ternakapp.pejantan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jogjaide.ternakapp.R;
import com.jogjaide.ternakapp.pejantan.kesehatan.MainActivityKesehatanPJT;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler.loadDatabase(this);
    }

    public void buttonPressed(View view) {
        String tag = view.getTag().toString().toLowerCase();
        switch (tag) {
            case "viewbutton": {
                Intent intent = new Intent(MainActivity.this, ViewPejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "addbutton": {
                Intent intent = new Intent(MainActivity.this, AddPejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "deletebutton": {
                Intent intent = new Intent(MainActivity.this, DeletePejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "updatebutton": {
                Intent intent = new Intent(MainActivity.this, UpdatePejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "kesPJT": {
                Intent intent = new Intent(MainActivity.this, MainActivityKesehatanPJT.class);
                startActivity(intent);
                break;
            }
        }
    }
}
