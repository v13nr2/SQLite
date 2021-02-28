package com.jogjaide.ternakapp.induk;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.jogjaide.ternakapp.induk.DatabaseIndukHandler;
import com.jogjaide.ternakapp.R;

public class ViewIndukActivity extends AppCompatActivity {
    private Cursor crinduk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_induk);

        displayInduk();

    }


    public void displayInduk(){

        crinduk = DatabaseIndukHandler.readDatabase();
        LinearLayout parent = (LinearLayout) findViewById(R.id.myLinearLayoutInduk);


        int count =0;
        if(!crinduk.moveToFirst()){
            Toast.makeText(this, "Induk not found. Database empty", Toast.LENGTH_SHORT).show();
            return;
        }
        do{
            count++;
            //display each student here
            String nameDB = crinduk.getString(1); //column no 2
            String numberDB = crinduk.getString(2); //column no 3

            LinearLayout child = new LinearLayout(this);
            child.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            child.setOrientation(LinearLayout.HORIZONTAL);

            TextView name = new TextView(this);
            TextView number = new TextView(this);

            name.setTextColor(Color.BLACK);
            number.setTextColor(Color.BLACK);

            //set textview weight = 1
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
            name.setLayoutParams(params);
            number.setLayoutParams(params);


            name.setText(nameDB);
            number.setText("\t\t\t"+numberDB);

            parent.addView(child);
            child.addView(name);
            child.addView(number);



        }while(crinduk.moveToNext());

        Log.d("DEBUG", "COUNT: "+count);

    }
}