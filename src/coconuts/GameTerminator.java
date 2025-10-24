/*
 * Course:     SWE 2410-111
 * Term:       Fall 2025
 * Assignment: Lab 6 - Coconuts
 * Author:     Quentin Robbins and Evan Hartline
 * Date:       10/23/25
 */
package coconuts;

/**
 * observer class to end the game when the crab dies
 */
public class GameTerminator implements Observers {
    private OhCoconutsGameManager game;

    /**
     * constructor to pass the game to the observer for the update functionality
     * @param game the game being observed through the subject
     */
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
