package com.ricardo.recursos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ricardo.game.Pong;
import com.ricardo.gameControle.MenuControle;

public class Controle implements KeyListener {
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;

    public static boolean isLeftPressed(){ return leftPressed; }
    public static boolean isRightPressed(){ return rightPressed; }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
    	if(e.getKeyCode() == KeyEvent.VK_A ) {
            leftPressed = true;
     	}    
        if(e.getKeyCode() == KeyEvent.VK_D ) {
            rightPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_B ) {
    		
    	}
        if(e.getKeyCode() == KeyEvent.VK_UP ) {
            if(Pong.menu.getMenu() == 0){
                MenuControle.menuPrincipal.upOption();
            }
    		if(Pong.menu.getMenu() == 1){
                MenuControle.menuPause.upOption();
            }
            if(Pong.menu.getMenu() == 2){
                MenuControle.menuGameOver.upOption();
            }
    	}     
        if(e.getKeyCode() == KeyEvent.VK_DOWN ) {
            if(Pong.menu.getMenu() == 0){
                MenuControle.menuPrincipal.downOption();
            }
    		if(Pong.menu.getMenu() == 1){
                MenuControle.menuPause.downOption();
            }
            if(Pong.menu.getMenu() == 2){
                MenuControle.menuGameOver.downOption();
            }
    		
    	}
        if(e.getKeyCode() == KeyEvent.VK_ENTER ) {
            if(Pong.menu.getMenu() == 0){
                if(MenuControle.menuPrincipal.getOpcao() == 0){
                    Pong.restartGame();
                }
                if(MenuControle.menuPrincipal.getOpcao() == 1){
                    JOptionPane.showMessageDialog(new JFrame(), "        Jogo Pong       " + "\n" + 
                                                                " desenvolvidor por Ricardo " + "\n" +
                                                                "     Dev Game - com Java     ", "Sobre",1);
                }
                if(MenuControle.menuPrincipal.getOpcao() == 2){
                    System.exit(0);
                }

            }
            if(Pong.menu.getMenu() == 1){
                if(MenuControle.menuPause.getOpcao() == 0){
                    Pong.setGameStatus("normal");
                    if(Pong.ball != null) Pong.ball.reset();
                }
                if(MenuControle.menuPause.getOpcao() == 1){
                    // reiniciar jogo
                    Pong.restartGame();

                }                    
                if(MenuControle.menuPause.getOpcao() == 2){
                    System.exit(0);
                }

            }
              if(Pong.menu.getMenu() == 2){
                if(MenuControle.menuGameOver.getOpcao() == 0){
                    /** TODO implementar metodo de new game */
                }                 
                if(MenuControle.menuGameOver.getOpcao() == 1){
                    System.exit(0);
                }

            }
    	}     
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
            if("normal".equals(Pong.getGameStatus())){
                Pong.setGameStatus("menu");
                Pong.menu.setMenu(1);
            }     
            
        }            
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_A ) {
    		leftPressed = false;
     	}     
        if(e.getKeyCode() == KeyEvent.VK_D ) {
    		rightPressed = false;
     	}
        if(e.getKeyCode() == KeyEvent.VK_B ) {
    		
     	}
        if(e.getKeyCode() == KeyEvent.VK_UP ) {
    		
    	}     
        if(e.getKeyCode() == KeyEvent.VK_DOWN ) {
    		
    	}
        if(e.getKeyCode() == KeyEvent.VK_ENTER ) {
    		
    	}     
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
    		
    	}         
        
    }

}
