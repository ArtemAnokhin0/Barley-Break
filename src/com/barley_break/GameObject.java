package com.barley_break;

import java.awt.*;

public class GameObject {
    public int x;
    public int y;
    public boolean isEmpty;
    public Image img;
    public int num;

    GameObject(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
        this.img = Numbers.values()[num].img;
        this.isEmpty = false;
    }
}
