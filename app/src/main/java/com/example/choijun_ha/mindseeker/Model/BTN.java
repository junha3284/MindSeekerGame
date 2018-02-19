package com.example.choijun_ha.mindseeker.Model;

/**
 * Created by choijun-ha on 2018-02-06.
 */

public class BTN {
    private int HintNum;

    enum STATUS_COVER{
        UNCOVERED,
        COVERED,
    }
    enum STATUS_MINE{
        YES,
        NO
    }
    private STATUS_COVER status_cover;
    private STATUS_MINE status_mine;

    BTN(){
        status_cover = STATUS_COVER.COVERED;
        status_mine = STATUS_MINE.NO;
        HintNum = 0;
    }

    public void implantMine(){
        status_mine = STATUS_MINE.YES;
    }

    public boolean checkMined(){
        if(status_mine == STATUS_MINE.YES)
            return true;
        else
            return false;
    }

    public void uncover(){
        status_cover = STATUS_COVER.UNCOVERED;
    }

    public boolean checkCovered(){
        if(status_cover == STATUS_COVER.UNCOVERED)
            return false;
        else
            return true;
    }

    public void incHintNum(){
        HintNum++;
    }

    public void decHintNum(){
        HintNum--;
    }

    public int getHintNum(){
        return HintNum;
    }


}
