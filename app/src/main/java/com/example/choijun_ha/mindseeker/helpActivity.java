package com.example.choijun_ha.mindseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class helpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupBackButton();

    }

    private void setupBackButton() {
        Button backbtn = (Button) findViewById(R.id.gobackbtn);
        backbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // TODO: produce helpActivity
                Intent intent = new Intent();
                finish();
            }
        });

    }

    public static Intent makeIntent(Context context){
        Intent intent = new Intent (context,helpActivity.class);
        return intent;
    }

}
