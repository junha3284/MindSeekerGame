package com.example.choijun_ha.mindseeker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.choijun_ha.mindseeker.Model.Game;
import com.google.gson.Gson;

public class StartActivity extends AppCompatActivity {
    public static final String GAME_INSTANCE_GSON_CODE = "GameInstace";
    private Game g;

    @Override
    protected void onPause() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(g);
        prefsEditor.putString(GAME_INSTANCE_GSON_CODE, json);
        prefsEditor.commit();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString(GAME_INSTANCE_GSON_CODE, "");
        if(json.isEmpty())
            g = Game.createGame();
        else
            g = gson.fromJson(json, Game.class);

        setButtons();
    }

    private void setButtons() {
        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button settingBtn = (Button) findViewById(R.id.configBtn);
        Button helpBtn = (Button) findViewById(R.id.helpBtn);

        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = PlayActivity.makeIntent(StartActivity.this);
                startActivity(intent);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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
