package com.ricardo.recursos;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Windows extends Canvas{

    private static final int LARGURA = 600, ALTURA = 850;
    private JFrame windows;

    public Windows(String titulo){
        windows = new JFrame();
        windows.setTitle(titulo);
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add canvas before packing so sizes compute correctly
        windows.add(this);
        windows.setPreferredSize(new Dimension(LARGURA, ALTURA));
        windows.pack();
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);
        // create BufferStrategy once when window is visible
        try {
            this.createBufferStrategy(3);
        } catch (IllegalStateException e) {
            // ignore: if not displayable yet, the game loop will create it
        }
       
    }
    public static int getLargura(){return LARGURA;}
    public static int getAltura(){return ALTURA;}

   
}
