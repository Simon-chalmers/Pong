package pong.model;

public class Ceiling {

    private double yPos;

    public Ceiling(double yPos){
        this.yPos = yPos;
    }

    public boolean collision(Ball ball){
        if(ball.getY() <= yPos){
            return true;
        }
        return false;
    }


}
