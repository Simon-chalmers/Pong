package pong.view;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

//import java.applet.AudioClip;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/*
   Defining all assets used by application
   Common assets and asset handling (for all themes) here
   For specific handling see classes in theme/

   *** Nothing to do here ***

*/

public abstract class Assets {

    private final String IMAGE_DIR = "file:assets/img/";
    private final String SOUND_DIR = "file:assets/sound/";

    // A Map to store which image belongs to which object
    private final Map<Object, Image> objectImage = new HashMap<>();

    // ------------ Handling Colors and Images ------------------------
    public final Color colorFgText = Color.WHITE;
    public final Image splash = getImage("pong.png");

    public final String leftPaddle = "coolbluepaddle.png";
    public final String rightPaddle = "coolredpaddle.png";


    {
        // bind common, for all themes, objects/classes  (none right now)
    }

    public abstract Image getBackground();  // Implemented by subclasses


    // -------------- Audio handling -----------------------------

    public AudioClip ballHitPaddle = getSound("ballhitpaddle.wav");

    // -------------- Methods binding objects/classes to assets -----------------

    public void bind(Object object, String imageFileName) {
        Image i = getImage(imageFileName);
        if (i != null) {
            objectImage.put(object, getImage(imageFileName));
        } else {
            throw new IllegalArgumentException("Missing image: " + IMAGE_DIR + imageFileName);
        }
    }

    // Get image to render
    public Image get(Object object) {
        Image i = objectImage.get(object);  // Try to find bound object
        if (i == null) {
            return get(object.getClass());  // .. if fail, check if class bound
        }
        return i;   // possible null, will throw exception, OK!
    }

    private Image get(Class<?> clazz) {
        return objectImage.get(clazz);
    }


    // ---------- Helpers ------------------------
    // protected means sub classes can access
    protected Image getImage(String fileName) {
        return new Image(IMAGE_DIR + fileName);
    }

    private AudioClip getSound(String fileName) {
        return new AudioClip(SOUND_DIR + fileName);
    }
}
