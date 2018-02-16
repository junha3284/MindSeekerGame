package com.example.choijun_ha.mindseeker;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    ImageView imageview;
    boolean clicked=false;
    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        Window window=getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        StartAnimation();
        Button skip = (Button) findViewById(R.id.Skipbtn);
        skip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                clicked=true;
                finish();


            }
        });
    }

    private void StartAnimation(){

        Animation anim=AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rel_lay);
        rl.clearAnimation();
        rl.startAnimation(anim);
        anim=AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView imageView=(ImageView) findViewById(R.id.Ryan1);
        imageView.clearAnimation();;
        imageView.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                // close this activity
                if(!clicked)
                {
                    Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);

    }
}
