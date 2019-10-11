package pong.event;

/*
       Events passed from model to GUI

       *** Nothing to do here ***
 */
public class ModelEvent {

    // Enumeration of events
    public enum Type {
        BALL_HIT_PADDLE,
        BALL_HIT_WALL_CEILING,
        NEW_BALL
    }

    public final ModelEvent.Type type;
    private final Object data;  // Possible send some data to GUI

    public ModelEvent(ModelEvent.Type type, Object data) {
        this.type = type;
        this.data = data;
    }

    public ModelEvent(ModelEvent.Type type) {
        this.type = type;
        this.data = null;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}