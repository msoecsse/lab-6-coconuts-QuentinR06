package coconuts;

import javafx.scene.layout.Pane;

public class ObjectRemover implements Observers {
    private OhCoconutsGameManager game;
    private Pane gamePane;
    private Pane theBeach;

    public ObjectRemover(OhCoconutsGameManager game, Pane gamePane, Pane theBeach) {
        this.game = game;
        this.gamePane = gamePane;
        this.theBeach = theBeach;
    }

    @Override
    public void update(ObserverSubject subject) {
        if (subject instanceof HitEvent) {
            HitEvent event = (HitEvent) subject;
            IslandObject hitter = event.getHitter();
            HittableIslandObject target = event.getTarget();

            //laser hits coconut - remove both
            if (hitter instanceof LaserBeam && target instanceof Coconut) {
                game.scheduleForDeletion(hitter);
                game.scheduleForDeletion(target);
                theBeach.getChildren().remove(hitter.getImageView());
                gamePane.getChildren().remove(target.getImageView());
                game.coconutDestroyed();
            }
            //coconut hits beach - remove coconut
            else if (hitter instanceof Coconut && target.isGroundObject() && !(target instanceof Crab)) {
                game.scheduleForDeletion(hitter);
                gamePane.getChildren().remove(hitter.getImageView());
                game.coconutDestroyed();
            }
            //coconut hits crab - remove both
            else if (hitter instanceof Coconut && target instanceof Crab) {
                game.scheduleForDeletion(hitter);
                game.scheduleForDeletion(target);
                gamePane.getChildren().remove(hitter.getImageView());
                gamePane.getChildren().remove(target.getImageView());
                game.coconutDestroyed();
            }
        }
    }
}