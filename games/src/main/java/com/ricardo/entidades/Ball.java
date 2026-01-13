package com.ricardo.entidades;

import java.awt.Color;
import java.awt.Graphics;
import com.ricardo.recursos.Windows;

public class Ball extends GameObjectos{

    private double initialSpeed = 10.0; // base speed (user requested)
    private double speed = initialSpeed; // pixels per tick
    private double angle = -Math.PI / 4; // direction in radians
    private int raio = 20; // used as diameter when drawing

    // visual rotation / spin (not used for rendering rotation here,
    // but affects horizontal velocity over time if desired)
    private double spin = 0;
    // when set to a future timestamp (ms), the ball is paused and will not move
    private long pauseUntil = 0;

    public Ball(){    
        // initialize with randomized reset so initial direction is varied
        reset();
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.yellow);
        g.fillOval(posX, posY, raio, raio);
        
    }

    @Override
    public void tick() {
        // if paused (after reset), do not update position or collisions
        if(System.currentTimeMillis() < pauseUntil){
            updateBounds();
            return;
        }
        // update position using angle and speed
        posX += (int)Math.round(Math.cos(angle) * speed);
        posY += (int)Math.round(Math.sin(angle) * speed);

        // screen bounds for horizontal collisions
        int left = 20;
        int right = Windows.getLargura() - 40;
        if(posX <= left){
            // reflect horizontally
            angle = Math.PI - angle;
            posX = left;
        }
        if(posX + raio >= right){
            angle = Math.PI - angle;
            posX = right - raio;
        }
        // vertical bounds are handled by the game logic (scoring) instead of
        // reflecting here. Keep the ball inside vertical bounds only to avoid
        // extreme off-screen positions.
        int topBound = 0;
        int bottomBound = Windows.getAltura();
        if(posY < topBound - 100) posY = topBound - 100;
        if(posY > bottomBound + 100) posY = bottomBound + 100;

       /*  // apply simple spin influence (small continuous lateral change)
        if(Math.abs(spin) > 0.01){
            angle += spin * 0.002; // tune influence
        } */

        updateBounds();
    }

    public void reset(){
        largura = raio;
        altura = raio;
        posX = (Windows.getLargura() / 2) - (raio / 2);
        posY = (Windows.getAltura() / 2) - (raio / 2);
        this.speed = initialSpeed;
        // randomize initial angle slightly left/right and up/down
        double base = Math.toRadians(40);
        double variance = Math.toRadians(10) * (Math.random() - 0.5);
        double angleCandidate = base + variance;
        if(Math.random() < 0.5) angleCandidate = -angleCandidate;
        // randomize horizontal direction
        if(Math.random() < 0.5) angleCandidate = Math.PI - angleCandidate;
        this.angle = angleCandidate;
        this.spin = 0;
        // pause movement for 500 ms to avoid instant re-collision issues
        this.pauseUntil = System.currentTimeMillis() + 500;
        updateBounds();
    }

    public int getRaio(){
        return raio;
    }

    public double getSpeed(){ return speed; }
    public void setSpeed(double s){ speed = s; }

    public double getAngle(){ return angle; }

    /**
     * Called when the ball is hit by a paddle (player or enemy).
     * We compute a new outgoing angle based on the hit position along the paddle,
     * add some spin proportional to the hit offset and optionally increase speed.
     */
    public void bounceFromPaddle(GameObjectos paddle){
        double paddleCenterX = paddle.getPosX() + paddle.getLargura() / 2.0;
        double ballCenterX = this.getPosX() + raio / 2.0;
        double relativeIntersectX = (ballCenterX - paddleCenterX);
        double normalizedRelativeIntersectionX = relativeIntersectX / (paddle.getLargura() / 2.0);
        if(normalizedRelativeIntersectionX < -1) normalizedRelativeIntersectionX = -1;
        if(normalizedRelativeIntersectionX > 1) normalizedRelativeIntersectionX = 1;

        // max bounce angle from vertical (in radians)
        double maxBounce = Math.toRadians(60);

        // If paddle is near bottom (player), send ball upward; else downward
        double newAngle;
        double paddleMidY = paddle.getPosY() + paddle.getAltura() / 2.0;
        boolean paddleIsBottom = paddleMidY > 300; // heuristic: bottom half

        if(paddleIsBottom){
            // angle measured from right (0), down pi/2, left pi, up -pi/2
            newAngle = -Math.PI / 2 + normalizedRelativeIntersectionX * maxBounce;
            // ensure upward
            if(Math.sin(newAngle) > 0) newAngle = -Math.abs(newAngle);
        } else {
            // for top paddle, send downward
            newAngle = Math.PI / 2 + normalizedRelativeIntersectionX * maxBounce;
            if(Math.sin(newAngle) < 0) newAngle = Math.abs(newAngle);
        }

        // Avoid nearly-vertical outgoing angles which produce very small horizontal
        // velocity and can cause enemy jitter. Enforce a minimum angular deviation
        // from the vertical direction.
        double minVerticalDeg = 12; // degrees away from perfect vertical
        double minVerticalRad = Math.toRadians(minVerticalDeg);
        double verticalBaseline = paddleIsBottom ? -Math.PI / 2 : Math.PI / 2;
        if(Math.abs(newAngle - verticalBaseline) < minVerticalRad){
            if(newAngle > verticalBaseline) newAngle = verticalBaseline + minVerticalRad;
            else newAngle = verticalBaseline - minVerticalRad;
        }

        this.angle = newAngle;

        // set spin proportional to hit offset
        this.spin = normalizedRelativeIntersectionX * 2.0;

        // Adjust speed based on outgoing angle:
        // - If angle is non-obtuse (<= 90°), increase speed up to +4 over initial.
        // - If angle is obtuse (> 90°), decrease speed but not below initial.
        double angleDeg = Math.toDegrees(Math.abs(this.angle));
        double maxSpeed = initialSpeed + 6.0;
        double delta = 1.0; // speed change per collision
        if(angleDeg <= 90.0){
            this.speed = Math.min(this.speed + delta, maxSpeed);
        } else {
            this.speed = Math.max(this.speed - delta, initialSpeed);
        }

        // push ball out of paddle to avoid repeated collisions
        if(paddleIsBottom){
            this.posY = paddle.getPosY() - raio - 1;
        } else {
            this.posY = paddle.getPosY() + paddle.getAltura() + 1;
        }

        updateBounds();
    }
}
