package com.ricardo.recursos;

import com.ricardo.entidades.Ball;
import com.ricardo.entidades.GameObjectos;

public class Colisao {

    /**
     * Verifica colisão entre uma bola (círculo) e um objeto retangular.
     * Usa a técnica de "closest point" (ponto mais próximo) entre o centro
     * do círculo e o retângulo.
     *
     * @param bola a instância de Ball
     * @param obj objeto que estende GameObjectos (Player, Enemy, etc.)
     * @return true se houver colisão
     */
    public static boolean colisaoCirculoRetangulo(Ball bola, GameObjectos obj){
        if(bola == null || obj == null) return false;

        double raio = bola.getRaio();
        double cx = bola.getPosX() + raio / 2.0; // centro X da bola
        double cy = bola.getPosY() + raio / 2.0; // centro Y da bola

        double rx = obj.getPosX();
        double ry = obj.getPosY();
        double rw = obj.getLargura();
        double rh = obj.getAltura();

        // ponto mais próximo do centro do círculo dentro do retângulo
        double closestX = clamp(cx, rx, rx + rw);
        double closestY = clamp(cy, ry, ry + rh);

        double dx = cx - closestX;
        double dy = cy - closestY;

        return (dx * dx + dy * dy) <= (raio * raio / 4.0);
        // note: raio é a largura/altura do oval usado no desenho; como usamos
        // centro e raio como dimensão total, comparamos com (raio/2)^2.
    }

    private static double clamp(double val, double min, double max){
        if(val < min) return min;
        if(val > max) return max;
        return val;
    }
}
