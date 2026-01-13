package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import com.ricardo.game.Pong;
import com.ricardo.recursos.Windows;

public class Enemy extends GameObjectos{

    private Color cor = Color.red;

    public Enemy(){
       
        largura = 120;
        altura = 20;
        posX = (Windows.getLargura() / 2) - (largura / 2);
        int top = 60; // field top
        posY = top + 10;
        updateBounds();
    }

    @Override
    public void render(Graphics g) {

        g.setColor(cor);
        g.fillRect(posX,posY,largura,altura);
    }

    @Override
    public void tick() {

        // If the ball exists, move the enemy toward the ball's X position
        if(Pong.ball != null){
            int ballX = Pong.ball.getPosX();
            int targetX = ballX - (largura / 2);
            int speed = 8; // adjust to tune difficulty
            int dist = targetX - posX;
            if(Math.abs(dist) > speed){
                posX += (dist > 0) ? speed : -speed;
            } else {
                // close enough, snap to avoid oscillation
                posX = targetX;
            }
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
