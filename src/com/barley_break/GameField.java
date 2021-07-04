package com.barley_break;

public class GameField {
    private static GameObject[][] gameField = new GameObject[BarleyBreak.getRows()][BarleyBreak.getCols()];
/*
    static void createGameField(){
        int[] numbers = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int num;
        for(int y=0; y<Fifteen.getRows(); y++)
            for(int x=0; x<Fifteen.getCols(); x++) {
                do {
                    num = (int)(Math.random()*16);
                }while(numbers[num]==-1);
                gameField[y][x] = new GameObject(x * Fifteen.getImageSize(), y * Fifteen.getImageSize(), num);
                if(numbers[num]==0) {
                    gameField[y][x].isEmpty=true;
                }
                numbers[num]=-1;
            }
    }*/

    static void createGameField(){
        for(int y = 0; y< BarleyBreak.getRows(); y++)
            for(int x = 0; x< BarleyBreak.getCols(); x++)
                gameField[y][x] = new GameObject(x * BarleyBreak.getImageSize(), y * BarleyBreak.getImageSize(), 4*y+x+1==16?0:4*y+x+1);
        gameField[3][3].isEmpty=true;

        for(int i=0; i<5000; i++) {
            Game.move((int) (Math.random() * 4), (int) (Math.random() * 4));
        }
    }

    static GameObject get(int x, int y){
        return gameField[y][x];

    }

}

