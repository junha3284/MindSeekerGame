package com.example.choijun_ha.mindseeker.Model;

import android.widget.Button;

/**
 * Created by choijun-ha on 2018-02-06.
 */

public class Game {
    private int COL_NUM=4;
    private int ROW_NUM=6;
    private int MINE_NUM;

    BTN[][] btns;

    static Game game;

    private Game(){
        btns = new BTN[ROW_NUM][COL_NUM];
    }

    static Game createGame(){
        if(game == null)
            return new Game();
        else
            return game;
    }

    public int getColNum(){
        return COL_NUM;
    }

    public int getRowNum(){
        return ROW_NUM;
    }

    public void setSize(int row, int col){
        this.COL_NUM = col;
        this.ROW_NUM = row;
    }

    public void setMineNum(int mineNum){
        this.MINE_NUM = mineNum;
        game = null;
    }


}
