package com.ricardo.entidades.gameIneterfaceUI;

import java.awt.Color;
import java.awt.Graphics;


public class Field {

    private int posX, posY;

    public Field(){
        posX = 20;
        posY = 60;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.white);
        int left = 20;
        int top = 60;
        int right = com.ricardo.recursos.Windows.getLargura() - 40;
        int bottom = com.ricardo.recursos.Windows.getAltura() - 50;
        g.drawLine(left, top, left, bottom);
        g.drawLine(right, top, right, bottom);
        g.drawLine(left, top, right, top);
        g.drawLine(left, bottom, right, bottom);
    }

    public void tick() {

        
    }





}
