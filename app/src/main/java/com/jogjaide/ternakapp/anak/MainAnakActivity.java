package com.jogjaide.ternakapp.anak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jogjaide.ternakapp.AddPejantanActivity;
import com.jogjaide.ternakapp.DeletePejantanActivity;
import com.jogjaide.ternakapp.R;
import com.jogjaide.ternakapp.UpdatePejantanActivity;
import com.jogjaide.ternakapp.induk.DatabaseIndukHandler;
import com.jogjaide.ternakapp.induk.MainIndukActivity;
import com.jogjaide.ternakapp.induk.ViewIndukActivity;

public class MainAnakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_anak);

        DatabaseAnakHandler.loadDatabase(this);
    }


    public void buttonPressed(View view) {
        String tag = view.getTag().toString().toLowerCase();
        switch (tag) {
            case "viewbutton": {
                Intent intent = new Intent(MainAnakActivity.this, ViewAnakActivity.class);
                startActivity(intent);
                break;
            }
            case "addbutton": {
                Intent intent = new Intent(MainAnakActivity.this, AddPejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "deletebutton": {
                Intent intent = new Intent(MainAnakActivity.this, DeletePejantanActivity.class);
                startActivity(intent);
                break;
            }
            case "updatebutton": {
                Intent intent = new Intent(MainAnakActivity.this, UpdatePejantanActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}