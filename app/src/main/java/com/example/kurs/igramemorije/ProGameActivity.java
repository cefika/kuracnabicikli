package com.example.kurs.igramemorije;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_game);
    }


    public void letthegamebegin(View view) {
        EditText etHeight=(EditText)findViewById(R.id.etHeight);
        EditText etWidth=(EditText)findViewById(R.id.etWidth);
        if(etHeight.getText().length()!=0 && etWidth.getText().length()!=0) {
            int h = Integer.parseInt((etHeight).getText().toString());
            int w = Integer.parseInt((etWidth).getText().toString());

            Intent myIntent = new Intent(this, GameActivity.class);
            myIntent.putExtra("height", h);
            myIntent.putExtra("width", w);
            startActivity(myIntent);
        }
        else
            {
                Toast.makeText(this, "Unesi", Toast.LENGTH_SHORT).show();
            }
    }
}
