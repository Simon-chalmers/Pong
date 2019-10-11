package pong.view.theme;

import javafx.scene.image.Image;
import pong.model.Ball;
import pong.view.Assets;


/*
   Specific theme

   *** Nothing to do here ***

*/

public class Cool extends Assets {

    // ------------ Handling Images ------------------------

    private final Image background = getImage("coolBg.png");

    {
        bind(Ball.class, "coolBall.png");
    }

    @Override
    public Image getBackground() {
        return background;
    }

    // -------------- Audio handling -----------------------------





}
