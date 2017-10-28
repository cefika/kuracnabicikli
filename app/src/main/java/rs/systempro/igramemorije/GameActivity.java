package rs.systempro.igramemorije;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import static rs.systempro.igramemorije.R.id.glMreza;
import static rs.systempro.igramemorije.R.id.image;

public class GameActivity extends AppCompatActivity {

    int solved;
    ImageButton firstOpened;
    ImageButton secondOpened;
    int numofOpened;
    int time;
    Timer timer;
    ArrayList<Drawable> images;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent myIntent = getIntent();
        final int h= myIntent.getIntExtra("height", 4);
        final int w = myIntent.getIntExtra("width",3);
        //;

        time=0;
        timer=new Timer();
        final TextView tv= (TextView)findViewById(R.id.twTime);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(time+"s");
                    }
                });
            }
        },1000,1000);

        images=new ArrayList<>();
        solved=0;
        numofOpened=0;
        for(int i=0;i<(h*w)/2;i++)
        {
            int resId= getResources().getIdentifier("slicica"+(i+1),"drawable",getPackageName());
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            {
            images.add(getDrawable(resId));
                images.add(getDrawable(resId));
            }
            else {

                images.add(getResources().getDrawable(resId));
                images.add(getResources().getDrawable(resId));
            }
        }
        Collections.shuffle(images);
        final RelativeLayout rlRoot=(RelativeLayout)findViewById(R.id.rlRoot);
        final GridLayout glmreza=(GridLayout) findViewById(glMreza);
        glmreza.setColumnCount(w);
        rlRoot.post(new Runnable() {
            @Override
            public void run() {
                int rootH=rlRoot.getHeight();
                int rootW=rlRoot.getWidth();

                Toast.makeText(getApplicationContext(),rootH+" - "+rootW,Toast.LENGTH_SHORT).show();
                for(int i=0;i<h*w;i++){
                   final ImageButton b = new ImageButton(getApplicationContext());
                    final Drawable image=images.get(i);
                   // b.setText("Dugme "+ i);
                    b.setBackgroundColor(Color.RED);
                    //b.setImageDrawable(images.get(i));
                    b.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    b.setPadding(0,0,0,0);

                    glmreza.addView(b);


                    GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                    if(rootH/h<rootW/w) {
                        param.height = rootH / h - 20;
                        param.width = rootH / h - 20;
                    }
                    else
                        {

                            param.width=rootW/w-20;
                            param.height=rootW/w-20;
                        }
                    param.setMargins(10,10,10,10);
                    b.setLayoutParams(param);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           // b.setImageDrawable(image);
                            if(numofOpened==2)
                            {
                                numofOpened=1;
                                firstOpened.setImageDrawable(null);
                                secondOpened.setImageDrawable(null);
                                firstOpened=b;
                                b.setImageDrawable(image);
                                solved++;
                                if(++solved ==h*w/2)
                                {
                                    timer.cancel();
                                    Intent intent=new Intent(getApplicationContext(), Gameover.class);
                                    intent.putExtra("t",time);
                                    startActivity(intent);
                                }
                            }
                            else if(numofOpened==1)
                                {
                                    b.setImageDrawable(image);
                                    numofOpened++;
                                    secondOpened=b;
                                    if(firstOpened.getDrawable().getConstantState().equals(secondOpened.getDrawable().getConstantState())) {
                                        numofOpened=0;
                                        firstOpened.setClickable(false);
                                        secondOpened.setClickable(false);
                                    }
                                }
                            else
                                {
                                    b.setImageDrawable(image);
                                    numofOpened=1;
                                    firstOpened=b;
                                }
                            //b.setImageDrawable(image);
                        }
                    });
                }
            }
        });
    }
}
