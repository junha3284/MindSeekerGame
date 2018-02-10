package com.example.choijun_ha.mindseeker.Model;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by choijun-ha on 2018-02-06.
 */

public class Game {
    private int COL_NUM=4;
    private int ROW_NUM=6;
    private int MINE_NUM=6;
    private int attemptNum = 0;
    private int coveredMineNum = MINE_NUM;

    BTN[][] btns;

    private static Game game;

    private Game(){
        btns = new BTN[this.ROW_NUM][this.COL_NUM];
    }

    public static Game createGame(){
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
        this.ROW_NUM = row;
        this.COL_NUM = col;
        this.btns = new BTN[this.ROW_NUM][this.COL_NUM];
        this.generateMines();
    }

    public void setMineNum(int mineNum){
        this.MINE_NUM = mineNum;
        this.generateMines();
    }

    private void generateMines(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i < COL_NUM*ROW_NUM; i++)
            list.add(i);
        Collections.shuffle(list);
        for(int i=0; i < MINE_NUM; i++){
            int temp = list.get(i);
            int row = temp/COL_NUM;
            int col = temp%COL_NUM;
            btns[row][col].implantMine();
            increaseColHintNum(col);
            increaseRowHintNum(row);
            btns[row][col].decHintNum(); // because hint num increase twice with above two functions
        }
    }

    private void increaseRowHintNum(int row){
        for(int i=0; i < COL_NUM; i++)
            btns[row][i].incHintNum();
    }
    private void increaseColHintNum(int col){
        for(int i=0; i < ROW_NUM; i++)
            btns[i][col].incHintNum();
    }

}
