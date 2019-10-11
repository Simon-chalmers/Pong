package pong.model;

public class Floor {

    private double yPos;

    public Floor(double yPos){
        this.yPos = yPos;
    }

    public boolean collision(Ball ball){
        if(ball.getY()+ball.getHeight() >= yPos){
            return true;
        }
        return false;
    }
}
