package com.example.choijun_ha.mindseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class helpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public static Intent makeIntent(Context context){
        Intent intent = new Intent (context,PlayActivity.class);
        return intent;
    }
}
