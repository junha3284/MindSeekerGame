package com.example.choijun_ha.mindseeker;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
    private Button[][] buttons;

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
        updateUI();
    }

    private void chooseTableSize() {
        NUM_ROWS=g.getRowNum();
        NUM_COLS=g.getColNum();
    }

    private void populateButtons() {
        buttons = new Button[NUM_ROWS][NUM_COLS];
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
                        lockButtonSizes();
                        gridButtonClicked(FINAL_ROW,FINAL_COL);
                    }
                });
                button.setTextSize(0);
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int x,int y) {
        int result = g.userClick(x,y);
        switch(result){
            case 0:
                    buttons[x][y].setTextSize(12) ;
                    break;
            case 1:
                lockButtonSizes();
                int newWidth=buttons[x][y].getWidth();
                int newHeight=buttons[x][y].getHeight();
                Bitmap originalBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ryann);
                Bitmap scaledBitmap= Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeight,true);
                Resources resource=getResources();
                buttons[x][y].setBackground(new BitmapDrawable(resource,scaledBitmap));
                    break;
            case 2:
                    buttons[x][y].setTextSize(12);
                    break;
            case -1: {
                g.incNumPlayed();
                g.updateBestScore();
                Toast.makeText(getApplicationContext(),"Score: "+ g.getAttemptNum(),Toast.LENGTH_LONG).show();
                this.finish();
                break;
            }
        }
        for(int i=0; i < NUM_ROWS; i++)
            for(int j=0; j < NUM_COLS; j++)
                buttons[i][j].setText(g.getHintNum(i,j)+" ");
        updateUI();
    }

    private void lockButtonSizes() {
        for(int row=0;row<g.getRowNum();row++)
        {
            for(int col=0;col<g.getColNum();col++)
            {
                Button button=buttons[row][col];
                int width=button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);
                int height=button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void updateUI(){
        TextView num_scan = (TextView) findViewById(R.id.num_scans_used);
        TextView num_mine_found = (TextView) findViewById(R.id.num_found_mines);
        TextView num_played = (TextView) findViewById(R.id.total_play);
        TextView num_bestScore = (TextView) findViewById(R.id.bestScore);

        num_scan.setText(getText(R.string.num_scans_used).toString()+ ": " + g.getAttemptNum());
        num_mine_found.setText(getText(R.string.numminesfound).toString()+": " +g.getFoundMineNum());
        num_played.setText(getText(R.string.total_play).toString()+": " + g.getNumPlayed());
        if(g.getBestScore() > 0)
            num_bestScore.setText(getText(R.string.best_score).toString()+" " + g.getBestScore());
    }

    public static Intent makeIntent(Context context){
        Intent intent = new Intent (context,PlayActivity.class);
        return intent;
    }
}
