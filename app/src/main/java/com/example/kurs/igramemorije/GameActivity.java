package com.example.kurs.igramemorije;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent myintent=getIntent();
       int h=myintent.getIntExtra("height",3);
       int  v=myintent.getIntExtra("width",3);
        GridLayout glMreza= (GridLayout) findViewById(R.id.glMreza);
        glMreza.setColumnCount(v);


        for(int i=0;i<h*v;i++)
        {
            Button b=new Button(this);
            //b.setWidth(btnwidth);
            //b.setHeight(btnHeight);



            // GridLayout.LayoutParams params=new GridLayout.LayoutParams(b.getLayoutParams());
            // params.setMargins(0,0,0,0);
            // b.setLayoutParams(params);
            b.setText("Dugme "+i);
            glMreza.addView(b);
            GridLayout.LayoutParams param=new GridLayout.LayoutParams();
            param.height=GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.FILL);
            b.setLayoutParams(param);
         }


    }
}
