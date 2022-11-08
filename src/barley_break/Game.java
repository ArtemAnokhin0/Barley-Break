package barley_break;

public class Game {
    private static boolean isStopped;
    private static boolean isWin;

    private static void isStopped(boolean state){ isStopped = state; }
    private static void isWin(boolean state){ isWin = state; }

    private static void restart(){
        GameField.createGameField();
        isStopped(false);
        isWin(false);
    }

    static String getMsg(){
        if(isWin)
            return "CONGRATS !        Right button  >>  New Game";
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
        if(GameField.get(x,y).getIsEmpty())
            return;

        GameObj freePlace = checkFreedom(x,y);

        if(!(freePlace.getCoor().x() == GameField.get(x,y).getCoor().x()) ||
                !(freePlace.getCoor().y() == GameField.get(x,y).getCoor().y()) ){
            freePlace.setIsEmpty(false);
            freePlace.setNum(GameField.get(x,y).getNum());
            freePlace.setImg(Numbers.values()[GameField.get(x,y).getNum()].img);

            GameField.get(x,y).setIsEmpty(true);
            GameField.get(x,y).setNum(0);
            GameField.get(x,y).setImg(Numbers.N0.img);
        }

    }

    private static GameObj checkFreedom(int x, int y){
        if(x != 0) {
            if(GameField.get(x-1, y).getIsEmpty())
                return GameField.get(x - 1, y);
        }
        if(x != BarleyBr.getCols()-1) {
            if(GameField.get(x+1, y).getIsEmpty())
                return GameField.get(x+1, y);
        }
        if(y != 0) {
            if(GameField.get(x, y-1).getIsEmpty())
                return GameField.get(x, y - 1);
        }
        if(y != BarleyBr.getRows()-1) {
            if(GameField.get(x, y+1).getIsEmpty())
                return GameField.get(x, y + 1);
        }
        return GameField.get(x, y);
    }

    private static void checkPrimacy(){
        for(int y = 0; y< BarleyBr.getRows(); y++) {
            for (int x = 0; x < BarleyBr.getCols(); x++) {
                if (GameField.get(x, y).getNum() != (x+4*y+1==16? 0 : x+4*y+1))
                    return;
            }
        }
        win();
    }

    private static void win() {
        isStopped(true);
        isWin(true);
    }
}
