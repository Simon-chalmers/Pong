package pong.model;


import pong.event.ModelEvent;
import pong.event.EventBus;

import java.util.ArrayList;
import java.util.List;

/*
 * Logic for the Pong Game
 * Model class representing the "whole" game
 * Nothing visual here
 *
 */
public class Pong {

    public static final double GAME_WIDTH = 600;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.02;
    public static final long HALF_SEC = 500_000_000;


    // TODO More attributes
    private int pointsLeft;
    private int pointsRight;

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ceiling ceiling;
    private Floor floor;

    // TODO Constructor

    public Pong(Ball ball, Paddle leftPaddle, Paddle rightPaddle, Ceiling ceiling, Floor floor) {
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.ceiling = ceiling;
        this.floor = floor;
    }


    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {

        ball.move();
        leftPaddle.move();
        rightPaddle.move();

        if(ceiling.collision(ball) || floor.collision(ball)) {
            ball.setdY(ball.getdY() * -1);
        }
        if (leftPaddle.collision(ball)){
            ball.setdX(ball.getdX() * - 1);
        }




       // tODO Gamelogic here
    }


    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> drawables = new ArrayList<>();
        drawables.add(ball);
        drawables.add(leftPaddle);
        drawables.add(rightPaddle);
        return drawables;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getPointsRight() {
        return pointsRight;
    }

    public void setSpeedRightPaddle(double dy) {
        rightPaddle.setdY(dy);
    }

    public void setSpeedLeftPaddle(double dy) {
        leftPaddle.setdY(dy);
    }
}
