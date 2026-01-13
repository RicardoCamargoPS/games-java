package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;

import com.ricardo.recursos.Controle;
import com.ricardo.recursos.Windows;

public class Player extends GameObjectos{

    private Color cor = Color.blue;
    private int velocidade = 6;
    
    public Player(){
        largura = 120;
        altura = 20;
        posX = (Windows.getLargura() / 2) - (largura / 2);
        int bottom = Windows.getAltura() - 50; // field bottom
        posY = bottom - 30; // place player slightly above bottom line
        updateBounds();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(cor);
        g.fillRect(posX,posY,largura,altura);
    }

    @Override
    public void tick() {
        if(Controle.isLeftPressed()){
            posX -= velocidade;
        }
        if(Controle.isRightPressed()){
            posX += velocidade;
        }

        // Keep inside horizontal bounds
        int left = 20;
        int right = Windows.getLargura() - 40;
        if(posX < left){
            posX = left;            
        }

        if(posX + largura > right){
            posX = right - largura;
        }
        updateBounds();
    }

}
