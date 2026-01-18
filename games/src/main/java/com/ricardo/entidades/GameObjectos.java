package com.ricardo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObjectos extends Rectangle {

    protected int posX, posY;
    protected int largura, altura;

     abstract public void tick();
    abstract public void render(Graphics g);
   

    public int getAltura(){return altura;}    
    public int getLargura(){return largura;}   
    public int getPosX(){return posX;}   
    public int getPosY(){return posY;}
    
    public int getEsquerda(){return posX;}
    public int getDireita(){return posX + largura;}
    public int getCima(){return posY;}
    public int getBaixo(){return posY + altura;}
}
