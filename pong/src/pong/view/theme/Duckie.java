package pong.view.theme;


/*
  Specific theme

   *** Nothing to do here ***

*/

import javafx.scene.image.Image;
import pong.model.Ball;
import pong.view.Assets;

import java.awt.*;


public class Duckie extends Assets {

    // ------------ Handling Images ------------------------

    private final Image background = getImage("duckieBg.jpg");

    {
        bind(Ball.class, "duckieBall.png");
    }

     @Override
    public Image getBackground() {
        return background;
    }

    // -------------- Audio handling -----------------------------

}
