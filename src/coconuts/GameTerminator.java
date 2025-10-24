package coconuts;

public class GameTerminator implements Observers {
    private OhCoconutsGameManager game;

    public GameTerminator(OhCoconutsGameManager game) {
        this.game = game;
    }

    @Override
    public void update(ObserverSubject subject) {
        if (subject instanceof HitEvent) {
            HitEvent event = (HitEvent) subject;
            HittableIslandObject target = event.getTarget();

            if (target instanceof Crab) {
                game.killCrab();
            }
        }
    }
}
