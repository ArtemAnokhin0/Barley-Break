package com.barley_break;

public class Game {
    private static boolean isStopped;
    private static boolean isWin;

    private static void restart(){
        GameField.createGameField();
        isStopped = false;
        isWin = false;
    }

    static String getMsg(){
        if(isWin)
            return "CONGRATS !        Left button  >>  New Game";
        else
            return "";
    }

    static void leftButtonPressed(int x, int y){
        if(isStopped)
            return;
        move(x,y);
        checkPrimacy();
    }

    static void rightButtonPressed(){
        restart();
    }

    static void move(int x,int y){
        if(GameField.get(x,y).isEmpty)
            return;

        GameObject freePlace = checkFreedom(x,y);

        if(!(freePlace.x == GameField.get(x,y).x && freePlace.y == GameField.get(x,y).y)){
            freePlace.isEmpty = false;
            freePlace.num = GameField.get(x,y).num;
            freePlace.img = Numbers.values()[GameField.get(x,y).num].img;

            GameField.get(x,y).isEmpty = true;
            GameField.get(x,y).num = 0;
            GameField.get(x,y).img = Numbers.N0.img;
        }

    }

    private static GameObject checkFreedom(int x, int y){
        if(x != 0) {
            if(GameField.get(x-1, y).isEmpty)
                return GameField.get(x - 1, y);
        }
        if(x != BarleyBreak.getCols()-1) {
            if(GameField.get(x+1, y).isEmpty)
                return GameField.get(x+1, y);
        }
        if(y != 0) {
            if(GameField.get(x, y-1).isEmpty)
                return GameField.get(x, y - 1);
        }
        if(y != BarleyBreak.getRows()-1) {
            if(GameField.get(x, y+1).isEmpty)
                return GameField.get(x, y + 1);
        }
        return GameField.get(x, y);
    }

    private static void checkPrimacy(){
        for(int y = 0; y< BarleyBreak.getRows(); y++) {
            for (int x = 0; x < BarleyBreak.getCols(); x++) {
                if (GameField.get(x, y).num != (x+4*y+1==16?0:x+4*y+1))
                    return;
            }
        }
        win();
    }

    private static void win() {
        isStopped = true;
        isWin = true;
    }
}
