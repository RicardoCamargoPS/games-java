package com.ricardo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObjectos extends Rectangle {

    protected int posX, posY;
    protected int largura, altura;

    /**
     * Note: position and size are set by subclasses. Use {@link #updateBounds()} to
     * synchronize the Rectangle bounds with the `posX/posY/largura/altura` fields.
     */
    public GameObjectos(){

    }

    protected void updateBounds(){
        setBounds(posX, posY, largura, altura);
    }

    abstract public void render(Graphics g);
    abstract public void tick();

    public int getAltura(){return altura;}    
    public int getLargura(){return largura;}   
    public int getPosX(){return posX;}   
    public int getPosY(){return posY;}
    
    public int getEsquerda(){return posX;}
    public int getDireita(){return posX + largura;}
    public int getCima(){return posY;}
    public int getBaixo(){return posY + altura;}
}
