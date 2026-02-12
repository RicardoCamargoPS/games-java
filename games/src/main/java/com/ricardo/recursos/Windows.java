package com.ricardo.recursos;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Windows extends Canvas{

    private static final int LARGURA = 500, ALTURA = 750;
    private JFrame windows;

    public Windows(String titulo){
        windows = new JFrame();
        windows.setTitle(titulo);
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        windows.add(this);
        windows.setPreferredSize(new Dimension(LARGURA, ALTURA));
        windows.pack();
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);
        
        try {
            this.createBufferStrategy(3);
        } catch (IllegalStateException e) {
            
        }
       
    }
    public static int getLargura(){return LARGURA;}
    public static int getAltura(){return ALTURA;}

   
}
