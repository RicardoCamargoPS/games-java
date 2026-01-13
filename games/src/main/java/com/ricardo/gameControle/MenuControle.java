package com.ricardo.gameControle;

import java.awt.Graphics;

import com.ricardo.entidades.gameIneterfaceUI.MenuGameOver;
import com.ricardo.entidades.gameIneterfaceUI.MenuPause;
import com.ricardo.entidades.gameIneterfaceUI.MenuPrincipal;

public class MenuControle {

    public static MenuPrincipal menuPrincipal;
    public static MenuPause menuPause;
    public static MenuGameOver menuGameOver;
    private int menu = 0;

    public MenuControle(){      

        menuGameOver = new MenuGameOver();
        menuPause = new MenuPause();
        menuPrincipal = new MenuPrincipal();

    }

    public void render(Graphics g){

        if(menu == 0){
            menuPrincipal.render(g);
 
        }else if (menu == 1){
            menuPause.render(g);

        }else if (menu == 2){
            menuGameOver.render(g);

        }
    }
    public void tick(){

        if(menu == 0){
            menuPrincipal.tick();
 
        }else if (menu == 1){
            menuPause.tick();

        }else if (menu == 2){
            menuGameOver.tick();

        }
    }

    public void setMenu(int menu){
        this.menu = menu;
    }
    public int getMenu(){
        return menu;
    }

}
