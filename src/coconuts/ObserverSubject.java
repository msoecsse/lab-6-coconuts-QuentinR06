package coconuts;

public interface ObserverSubject {
    void attach(IslandObject obj);
    void detacth(IslandObject obj);
    void notifyAllObjects();
}

