package com.ricardo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.ricardo.entidades.gameIneterfaceUI.Field;
import com.ricardo.entidades.gameIneterfaceUI.Placar;
import com.ricardo.gameControle.MenuControle;
import com.ricardo.entidades.Ball;
import com.ricardo.entidades.Enemy;
import com.ricardo.entidades.Player;
import com.ricardo.recursos.Controle;
import com.ricardo.recursos.Colisao;
import com.ricardo.recursos.AudioPlayer;
import com.ricardo.recursos.Windows;

public class Pong implements Runnable{

    private Windows windows;
    public static Player player;
    public static Enemy enemy;
    public static Ball ball;
    public static Field field;
    private static String gameStatus = "menu";
    public  static MenuControle menu;
    private Controle controle;

    public static Placar placarPlayer, placarEnemy;

    public Pong(){
     
        windows = new Windows("Pong");
        controle = new Controle();
        windows.addKeyListener(controle);
        windows.requestFocusInWindow();
        player = new Player();
        enemy = new Enemy();
        ball = new Ball();
        field = new Field();
        menu = new MenuControle();
        placarPlayer = new Placar("Player ", 20,20, Color.blue);
        placarEnemy = new Placar("Enemy ",20,50, Color.red);
        // preload common sounds to avoid first-play latency
        AudioPlayer.preload("bola");
        AudioPlayer.preload("ponto");
        
        start();
    }

    public static void restartGame(){
        player = new Player();
        enemy = new Enemy();
        ball = new Ball();
        if(placarPlayer != null) placarPlayer.reset();
        if(placarEnemy != null) placarEnemy.reset();
        // return to gameplay
        setGameStatus("normal");
        if(menu != null) menu.setMenu(0);
    }

    private void tick(){

        /***********************************************/
        /***** todo metodo logico dos objetos aqui *****/

        if("menu".equals(gameStatus)){
            menu.tick();

        }else if("normal".equals(gameStatus)){            
            player.tick();
            enemy.tick();
            ball.tick();

            // colisões: bola com player/enemy
            if(Colisao.colisaoCirculoRetangulo(ball, player)){
                ball.bounceFromPaddle(player);
                AudioPlayer.play("bola");
            }
            if(Colisao.colisaoCirculoRetangulo(ball, enemy)){
                ball.bounceFromPaddle(enemy);
                AudioPlayer.play("bola");
            }

            // Verifica se houve ponto (bola saiu pelo topo ou pela base)
            int topLine = 60;
            int bottomLine = Windows.getAltura() - 50;
            if(ball.getPosY() <= topLine){
                placarPlayer.somaPonto();
                AudioPlayer.play("ponto");
                ball.reset();
            }
            if(ball.getPosY() + ball.getRaio() >= bottomLine){
                placarEnemy.somaPonto();
                AudioPlayer.play("ponto");
                ball.reset();
            }            
        }


        /*************************************************/ 
    }

    private void render(){

        BufferStrategy bs = windows.getBufferStrategy();

        if(bs == null){
            windows.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,Windows.getLargura(),Windows.getAltura());

        /***********************************************/
        /***** todo metodo render dos objetos aqui *****/
        

        if("menu".equals(gameStatus)){
            menu.render(g);
            
        }else if("normal".equals(gameStatus)){
           
            player.render(g);
            enemy.render(g);
            ball.render(g);  
            field.render(g);
            placarPlayer.render(g);
            placarEnemy.render(g);
        }

        /*************************************************/        
        bs.show();
    }

    public void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(true){            
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {               
                e.printStackTrace();
            }

            render();
            tick();
            
        }
    }

    public static void setGameStatus(String status){
        gameStatus = status;
    }

    public static String getGameStatus(){return gameStatus;}

    

}
