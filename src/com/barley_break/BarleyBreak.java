package com.barley_break;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarleyBreak extends JFrame {
    private JPanel panel;
    private JLabel label;
    private static final int COLS = 4;
    private static final int ROWS = 4;
    private static final int IMAGE_SIZE = 119;

    public static void main(String[] args) {
        new BarleyBreak();
    }

    private BarleyBreak(){
        setImages();
        initLabel();
        initPanel();
        initFrame();
        GameField.createGameField();
    }

    private void initLabel(){
        label = new JLabel("Welcome");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for(int y = 0; y< BarleyBreak.getRows(); y++)
                    for(int x = 0; x< BarleyBreak.getCols(); x++)
                        g.drawImage(GameField.get(x,y).img, GameField.get(x,y).x, GameField.get(x,y).y, this);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/getImageSize();
                int y = e.getY()/getImageSize();
                if(e.getButton() == MouseEvent.BUTTON1)
                    Game.leftButtonPressed(x, y);
                else if(e.getButton() == MouseEvent.BUTTON3)
                    Game.rightButtonPressed();
                label.setText(Game.getMsg());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(COLS*IMAGE_SIZE,ROWS*IMAGE_SIZE));
        add(panel);
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Barley-Break");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(Numbers.N15.img);
        pack();
    }

    static int getCols(){
        return COLS;
    }

    static int getRows(){
        return ROWS;
    }

    static int getImageSize(){
        return IMAGE_SIZE;
    }

    private void setImages(){
        for(Numbers element: Numbers.values()){
            element.img = getImage(element.ordinal()+"");
        }
    }

    private Image getImage(String name){
        return new ImageIcon(getClass().getResource("/"+name+".png")).getImage();
    }
}

