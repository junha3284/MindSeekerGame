package com.example.choijun_ha.mindseeker;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    ImageView imageview;
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

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited=0;
                    sleep(5000);
                    Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    finish();
                }
            }
        };
        myThread.start();
    }
}
