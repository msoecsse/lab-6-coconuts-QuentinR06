package coconuts;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

// An abstraction of all objects that can be hit by another object
// This captures the Subject side of the Observer pattern; observers of the hit event will take action
//   to process that event
// This is a domain class; do not introduce JavaFX or other GUI components here
public class HitEvent implements ObserverSubject{
    private List<IslandObject> objects = new ArrayList<>();

    @Override
    public void attach(IslandObject obj) {
        objects.add(obj);
    }

    @Override
    public void detacth(IslandObject obj) {
        objects.remove(obj);
    }

    @Override
    public void notifyAllObjects() {
        for (IslandObject o : objects){
            o.update();
        }
    }
}
