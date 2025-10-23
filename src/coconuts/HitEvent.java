package coconuts;

import java.util.ArrayList;
import java.util.List;

// An abstraction of all objects that can be hit by another object
// This captures the Subject side of the Observer pattern; observers of the hit event will take action
//   to process that event
// This is a domain class; do not introduce JavaFX or other GUI components here
public class HitEvent implements ObserverSubject {
    private List<Observers> observers = new ArrayList<>();
    private IslandObject hitter;
    private HittableIslandObject target;

    public HitEvent(IslandObject hitter, HittableIslandObject target) {
        this.hitter = hitter;
        this.target = target;
    }

    @Override
    public void attach(Observers obj) {
        observers.add(obj);
    }

    @Override
    public void detach(Observers obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyAllObservers() {
        for (Observers o : observers) {
            o.update(this);
        }
    }

    public IslandObject getHitter() {
        return hitter;
    }
    public HittableIslandObject getTarget() {
        return target;
    }
}