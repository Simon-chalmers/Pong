
package pong.event;

/*
    Contract for all classes acting as targets
    for events from model.
    See BreakoutGUI

     *** Nothing to do here ****
 */
public interface IEventHandler {
    void onModelEvent(ModelEvent evt);
}
