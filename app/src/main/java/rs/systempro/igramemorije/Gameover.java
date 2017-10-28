package rs.systempro.igramemorije;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static rs.systempro.igramemorije.R.id.etHeight;
import static rs.systempro.igramemorije.R.id.time;

public class Gameover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        int time= getIntent().getIntExtra("time",60);
        ((TextView)findViewById(R.id.tvtime)).setText(time+"s");
    }
}
