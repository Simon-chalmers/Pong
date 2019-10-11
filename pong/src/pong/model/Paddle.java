package pong.model;

import static pong.model.Pong.GAME_HEIGHT;

/*
 * A Paddle for the Pong game
 * A model class
 *
 */
public class Paddle implements IPositionable {

    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 60;
    public static final double PADDLE_SPEED = 5;

    private double xPos = 0;
    private double yPos = 0;

    private double dY = 0;

    public Paddle(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public boolean collision(Ball ball){

        if(ball.getX() < xPos + PADDLE_WIDTH){

        }


        return false;
    }


    public void setdY(double dY) {
        this.dY = dY;
    }

    @Override
    public double getX() {
        return xPos;
    }

    @Override
    public double getY() {
        return yPos;
    }

    @Override
    public double getWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public double getHeight() {
        return PADDLE_HEIGHT;
    }

    @Override
    public void move() {

        yPos += dY * PADDLE_SPEED;

    }
}
