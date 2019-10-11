package pong.event;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

/*
     Service to send events **from model** to GUI
     GUI must know if there has been a collision in the model etc.

     NOTE: Events **from GUI** to model handled by JavaFX events and keyboard listeners etc.

     *** Nothing to do here ****

 */
public enum EventBus {

    INSTANCE;

    private final List<IEventHandler> handlers
            = Collections.synchronizedList(new ArrayList<>());
    private boolean trace = false;

    public void register(IEventHandler handler) {
        handlers.add(handler);
    }

    public void unRegister(IEventHandler handler) {
        handlers.remove(handler);
    }

    public void publish(ModelEvent evt) {
        // Tracking all events
        if (trace) {
            System.out.println(evt);
        }
        synchronized (handlers) {
            handlers.stream().forEach((evh) -> {
                evh.onModelEvent(evt);
            });
        }
    }

    public void publish(ModelEvent.Type tag) {
        publish(new ModelEvent(tag, null));
    }

}
