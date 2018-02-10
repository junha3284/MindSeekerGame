package com.example.choijun_ha.mindseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.choijun_ha.mindseeker.Model.Game;

public class configActivity extends AppCompatActivity {
    private Game g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        g= Game.createGame();

        PopulateRadioGroup();
        setBtn();
    }

    private void PopulateRadioGroup(){
        RadioGroup boardSizeGroup = (RadioGroup) findViewById(R.id.boardSizeRadioGroup);
        final String[] sizes = getResources().getStringArray(R.array.boardSizes);
        for(int i =0; i < sizes.length; i++) {
            RadioButton radioBtn = new RadioButton(this);
            radioBtn.setText(sizes[i]);
            final String size=sizes[i];
            radioBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // TODO: change Game Model's board size config ( NUM_COL, NUM_ROW)
                    int a=0;
                    for(int j=0;j<size.length();j++)
                    {
                        if(size.charAt(j)=='x')
                        {
                            a=j;
                            break;
                        }
                    }
                    String srow=size.substring(0,a-1);
                    String scol=size.substring(a+1,size.length());
                    int irow=Integer.parseInt(srow);
                    int icol=Integer.parseInt(scol);
                    g.setSize(irow,icol);
                }
            });
            boardSizeGroup.addView(radioBtn);
        }
        RadioGroup mineNumGroup = (RadioGroup) findViewById(R.id.mineNumRadioGroup);
        final int[] nums = getResources().getIntArray(R.array.mineNums);
        for(int i=0; i < nums.length; i++){
            RadioButton radioBtn = new RadioButton(this);
            radioBtn.setText(""+nums[i]);
            final int p=i;
            radioBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    // TODO: change Game Model's mine number config (mineNum)
                    int minenum=nums[p];
                    g.setMineNum(minenum);

                }
            });
            mineNumGroup.addView(radioBtn);
        }
    }

    private void setBtn(){
        Button btn = (Button) findViewById(R.id.playHistoryResetBtn);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // TODO: RESET Game Model's Game History
            }
        });
    }

    public static Intent makeIntent(Context context){
        Intent intent = new Intent (context,configActivity.class);
        return intent;
    }
}
