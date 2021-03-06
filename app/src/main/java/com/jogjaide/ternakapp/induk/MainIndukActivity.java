package com.jogjaide.ternakapp.induk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jogjaide.ternakapp.pejantan.AddPejantanActivity;
import com.jogjaide.ternakapp.pejantan.DeletePejantanActivity;
import com.jogjaide.ternakapp.R;
import com.jogjaide.ternakapp.pejantan.UpdatePejantanActivity;

public class MainIndukActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_induk);
        DatabaseIndukHandler.loadDatabase(this);
    }


    public void buttonPressed(View view) {
        String tag = view.getTag().toString().toLowerCase();
        switch (tag) {
            case "viewbutton": {
                Intent intent = new Intent(MainIndukActivity.this, ViewIndukActivity.class);
                startActivity(intent);
                break;
            }
            case "addbutton": {
                Intent intent = new Intent(MainIndukActivity.this, AddIndukActivity.class);
                startActivity(intent);
                break;
            }
            case "deletebutton": {
                Intent intent = new Intent(MainIndukActivity.this, DeletePejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "updatebutton": {
                Intent intent = new Intent(MainIndukActivity.this, UpdatePejantanActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}