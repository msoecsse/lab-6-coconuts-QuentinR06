package coconuts;

public interface ObserverSubject {
    void attach(Observers o);
    void detach(Observers o);
    void notifyAllObservers();
}

