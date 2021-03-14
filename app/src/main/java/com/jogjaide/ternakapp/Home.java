package com.jogjaide.ternakapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jogjaide.ternakapp.anak.MainAnakActivity;
import com.jogjaide.ternakapp.induk.MainIndukActivity;
import com.jogjaide.ternakapp.pejantan.DatabaseHandler;
import com.jogjaide.ternakapp.pejantan.MainActivity;
import com.jogjaide.ternakapp.pejantan.UpdatePejantanActivity;
import com.jogjaide.ternakapp.pejantan.DatabaseHandler;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //DatabaseHandler.checkDatabase();
        DatabaseHandler.loadDatabase(this);
    }

    public void buttonPressed(View view) {
        String tag = view.getTag().toString().toLowerCase();
        switch (tag) {
            case "viewbutton": {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case "addbutton": {
                Intent intent = new Intent(Home.this, MainIndukActivity.class);
                startActivity(intent);
                break;
            }
            case "deletebutton": {
                Intent intent = new Intent(Home.this, UpdatePejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "updatebutton": {
                Intent intent = new Intent(Home.this, MainAnakActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}