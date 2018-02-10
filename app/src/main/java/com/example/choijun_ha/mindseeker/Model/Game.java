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
    private int attemptNum;
    private int coveredMineNum;

    BTN[][] btns;

    private static Game game;

    private Game(){
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
    }

    public void setMineNum(int mineNum){
        this.MINE_NUM = mineNum;
    }

    public void startGame(){
        this.btns = new BTN[ROW_NUM][COL_NUM];
        this.generateMines();
        this.attemptNum = 0;
        this.coveredMineNum = this.MINE_NUM;
    }

    // -1 => User found all mines
    // 0 => clicked covered-NoMine BTN => give Hint Num in GameActivity
    // 1 => clicked covered-Mine BTN
    // 2 => clicked uncovered-Mine-BTN => give Hint Num in GameActivity
    // 3 => clicked unconvered-noMine-BTN
    public int userClick(int row, int col){
        BTN temp = btns[row][col];
        if(temp.checkCovered()){
            this.attemptNum++;
            temp.uncover();
            if(temp.checkMined()){
                decRowHintNum(row);
                decColHintNum(col);
                temp.incHintNum();
                this.coveredMineNum--;
                if(this.coveredMineNum > 0 )
                    return 1;
                else
                    return -1;
            }
            return 0;
        }
        else{
            if(temp.checkMined()) {
                this.attemptNum++;
                return 2;
            }
            return 3;
        }
    }

    private int getHintNum(int row, int col){
        return btns[row][col].getHintNum();
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
            incColHintNum(col);
            incRowHintNum(row);
            btns[row][col].decHintNum(); // because hint num increase twice with above two functions
        }
    }

    private void incRowHintNum(int row){
        for(int i=0; i < COL_NUM; i++)
            btns[row][i].incHintNum();
    }

    private void decRowHintNum(int row){
        for(int i=0; i< COL_NUM; i++)
            btns[row][i].decHintNum();
    }

    private void incColHintNum(int col){
        for(int i=0; i < ROW_NUM; i++)
            btns[i][col].incHintNum();
    }

    private void decColHintNum(int col){
        for(int i=0; i < ROW_NUM; i++)
            btns[i][col].decHintNum();
    }

}
