package com.example.choijun_ha.mindseeker;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choijun_ha.mindseeker.Model.Game;

public class PlayActivity extends AppCompatActivity {

    private Game g = Game.createGame();
    int NUM_ROWS;
    int NUM_COLS;
   // private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        g.startGame();
        chooseTableSize();
        populateButtons();
    }

    private void chooseTableSize() {
        NUM_ROWS=g.getRowNum();
        NUM_COLS=g.getColNum();
    }

    private void populateButtons() {
        TableLayout table=(TableLayout)findViewById(R.id.TableForButtons);
        for(int row=0;row<NUM_ROWS;row++)
        {
            TableRow tableRow= new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f

            ));
            table.addView(tableRow);
            for(int col=0;col<NUM_COLS;col++)
            {
                final int FINAL_ROW=row;
                final int FINAL_COL=col;
                Button button=new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                //make text not clip on small buttons
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(FINAL_ROW,FINAL_COL);
                    }
                });
                tableRow.addView(button);
            }
        }
    }

    private void gridButtonClicked(int x,int y) {
        //TODO:decide what button will do+uncomment game above
        Toast.makeText(getApplicationContext(),""+g.userClick(x,y),Toast.LENGTH_SHORT).show();
        updateUI();
    }

    private void updateUI(){
        TextView num_scan = (TextView) findViewById(R.id.num_scans_used);
        TextView num_mine_found = (TextView) findViewById(R.id.num_found_mines);

        num_scan.setText(getText(R.string.num_scans_used).toString()+ ": " + g.getAttemptNum());
        num_mine_found.setText(getText(R.string.numminesfound).toString()+": " +g.getFoundMineNum());
    }

    public static Intent makeIntent(Context context){
        Intent intent = new Intent (context,PlayActivity.class);
        return intent;
    }
}
