/*
 * Course:     SWE 2410-111
 * Term:       Fall 2025
 * Assignment: Lab 6 - Coconuts
 * Author:     Quentin Robbins
 * Date:       10/23/25
 */
package coconuts;

import javafx.scene.layout.Pane;

/**
 * Observer class to remove a coconut or laser whenever either has a valid interaction.
 */
public class ObjectRemover implements Observers {
    private OhCoconutsGameManager game;
    private Pane gamePane;
    private Pane theBeach;

    /**
     * constructor to pass the necessary information to update and remove the objects
     * @param game     the game being observed through the subject
     * @param gamePane pane to get the coconuts
     * @param theBeach pane to get the laser
     */
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

            if (hitter instanceof LaserBeam && target instanceof Coconut) {
                game.scheduleForDeletion(hitter);
                game.scheduleForDeletion(target);
                theBeach.getChildren().remove(hitter.getImageView());
                gamePane.getChildren().remove(target.getImageView());
                game.coconutDestroyed();
            } else if (hitter instanceof Coconut && target.isGroundObject() && !(target instanceof Crab)) {
                game.scheduleForDeletion(hitter);
                gamePane.getChildren().remove(hitter.getImageView());
                game.coconutDestroyed();
            } else if (hitter instanceof Coconut && target instanceof Crab) {
                game.scheduleForDeletion(hitter);
                game.scheduleForDeletion(target);
                gamePane.getChildren().remove(hitter.getImageView());
                gamePane.getChildren().remove(target.getImageView());
                game.coconutDestroyed();
            }
        }
    }
}