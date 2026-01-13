package com.ricardo.entidades.gameIneterfaceUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ricardo.entidades.GameObjectos;

public class Placar extends GameObjectos {

    private int pontos = 0;
    private Color cor;
    private String nome;
    private static final Font FONT = new Font("arial", Font.BOLD, 20);

    public Placar(String nome,int px, int py, Color cor){
        this.posX = px;
        this.posY = py;
        this.cor = cor;
        this.nome = nome;
        updateBounds();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(cor);
        g.setFont(FONT);
        g.drawString(nome, posX, posY);
        g.setColor(Color.white);
        g.drawString(Integer.toString(pontos), posX + 80, posY);
    }

    @Override
    public void tick() {
        
    }

    public void somaPonto(){pontos++;}
    public int getPontos(){return pontos;}
    public void reset(){pontos = 0;}

}
