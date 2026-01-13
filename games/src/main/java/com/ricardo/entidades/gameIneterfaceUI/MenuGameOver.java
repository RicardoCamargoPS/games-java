package com.ricardo.entidades.gameIneterfaceUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ricardo.entidades.GameObjectos;

public class MenuGameOver extends GameObjectos{

    private String[] opicoes = { "novo", "sair"};
    private int opcao = 0;
    private int maxOption = opicoes.length - 1;
    private boolean up, down;

    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.BOLD, 52));
        g.drawString("PONG", 135, 180);

        g.setFont(new Font("arial", Font.BOLD, 36));
        g.drawString("Game Over", 110, 250);

        g.setFont(new Font("arial", Font.BOLD, 28));
        g.drawString("novo", 160, 330);
        g.drawString("sair", 160, 380);


        if(opicoes[opcao].equals("novo")){
            g.drawString(">", 135, 330);

        }else if (opicoes[opcao].equals("sair")) {
            g.drawString(">", 135, 380);
        }
    }

    @Override
    public void tick() {

        if(up){
            up = false;
            opcao--;
            if(opcao < 0){
                opcao = maxOption;
            }
        }
        if(down){
            down = false;
            opcao++;
            if(opcao > maxOption){
                opcao = 0;
            }
        }
        
    }

    public void upOption(){
        up = true;
    }
    public void downOption(){
        down = true;
    }
    public int getOpcao(){
        return opcao;
    }

}
