package barley_break;

import java.awt.*;

public class GameObj {
    private final Coor coor;
    private boolean isEmpty;
    private Image img;
    private int num;

    GameObj(int x, int y, int num){
        this.coor = new Coor(x,y);
        this.num = num;
        this.img = Numbers.values()[num].img;
        this.isEmpty = false;
    }

    Coor getCoor() { return coor; }
    void setIsEmpty(boolean isEmpty){ this.isEmpty = isEmpty; }
    boolean getIsEmpty(){ return isEmpty; }

    void setImg(Image img){ this.img = img; }
    Image getImg(){ return img; }

    void setNum(int num){ this.num = num; }
    int getNum(){ return num; }
}

record Coor(int x, int y) {}

class GameField {
    private static final GameObj[][] gameField =
            new GameObj[BarleyBr.getRows()][BarleyBr.getCols()];

    static void createGameField(){
        for(int y = 0; y< BarleyBr.getRows(); y++)
            for(int x = 0; x< BarleyBr.getCols(); x++)
                gameField[y][x] = new GameObj(x * BarleyBr.getImageSize(),
                        y * BarleyBr.getImageSize(),
                        4*y+x+1==16?0:4*y+x+1 );
        gameField[3][3].setIsEmpty(true);

        for(int i=0; i<5000; i++) {
            Game.move((int) (Math.random() * 4), (int) (Math.random() * 4));
        }
    }

    static GameObj get(int x, int y){
        return gameField[y][x];

    }

}
