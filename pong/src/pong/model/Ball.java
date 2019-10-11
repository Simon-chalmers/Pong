package pong.model;

import java.util.Random;

import static pong.model.Pong.GAME_HEIGHT;
import static pong.model.Pong.GAME_WIDTH;

/*
 * A Ball for the Pong game
 * A model class
 */
public class Ball implements IPositionable {

    public static final double WIDTH = 40;
    public static final double HEIGHT = 40;

    private double xPos;
    private double yPos;

    private double dX;
    private double dY;

    private double speed;

    public Ball(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Ball(double xPos, double yPos, double dX, double dY, double speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dX = dX;
        this.dY = dY;
        this.speed = speed;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    public double getdX() {
        return dX;
    }

    public double getdY() {
        return dY;
    }

    @Override
    public void move() {

        xPos += dX * speed;
        yPos += dY * speed;

    }
}
