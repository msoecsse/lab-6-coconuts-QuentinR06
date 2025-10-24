/*
 * Course:     SWE 2410-111
 * Term:       Fall 2025
 * Assignment: Lab 6 - Coconuts
 * Author:     Quentin Robbins
 * Date:       10/23/25
 */
package coconuts;

/**
 * Score board observer class to update the score board whenever a coconut is destroyed
 */
public class ScoreBoard implements Observers {
    private int coconutsDestroyed = 0;
    private int coconutsReachedBeach = 0;

    @Override
    public void update(ObserverSubject subject) {
        if (subject instanceof HitEvent) {
            HitEvent event = (HitEvent) subject;
            IslandObject hitter = event.getHitter();
            HittableIslandObject target = event.getTarget();

            if (hitter instanceof LaserBeam && target instanceof Coconut) {
                coconutsDestroyed++;
            } else if (hitter instanceof Coconut && target.isGroundObject() && !(target instanceof Crab)) {
                coconutsReachedBeach++;
            }
        }
    }

    public int getCoconutsDestroyed() {
        return coconutsDestroyed;
    }
    public int getCoconutsReachedBeach() {
        return coconutsReachedBeach;
    }
}
