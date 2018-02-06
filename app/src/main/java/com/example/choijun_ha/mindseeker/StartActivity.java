package com.example.choijun_ha.mindseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setButtons();
    }

    private void setButtons() {
        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button settingBtn = (Button) findViewById(R.id.configBtn);
        Button helpBtn = (Button) findViewById(R.id.helpBtn);

        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // TO DO: produce playActivity
                Intent intent = PlayActivity.makeIntent(StartActivity.this);
                startActivity(intent);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // TO DO: produce configActivity
                Intent intent = configActivity.makeIntent(StartActivity.this);
                startActivity(intent);
            }
        });
        helpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = helpActivity.makeIntent(StartActivity.this);
                startActivity(intent);
            }
        });
    }

}
