/*
 * Course:     SWE 2410-111
 * Term:       Fall 2025
 * Assignment: Lab 6 - Coconuts
 * Author:     Evan Hartline
 * Date:       10/23/25
 */
package coconuts;

import javafx.scene.image.Image;

// Represents the beam of light moving from the crab to a coconut; can hit only falling objects
// This is a domain class; do not introduce JavaFX or other GUI components here

/**
 * class to create a new island object laser beam
 */
public class LaserBeam extends HittableIslandObject {
    private static final int WIDTH = 2; // must be updated with image
    private static final Image LASER_IMAGE = new Image("file:images/laser-1.png");
    private static final int STEP = 8;
    private static final int IMAGE_WIDTH = 8;

    /**
     * constructor to create a new beam when fired by the user
     * @param game game that its being displayed in
     * @param eyeHeight of the crab
     * @param crabCenterX of the crab
     */
    public LaserBeam(OhCoconutsGameManager game, int eyeHeight, int crabCenterX) {
        super(game, crabCenterX, eyeHeight, WIDTH, LASER_IMAGE);
        super.getImageView().setFitWidth(IMAGE_WIDTH);
    }

    @Override
    public int hittable_height() {
        return y + WIDTH;
    }

    @Override
    public void step() {
        y -= STEP;
        display();
    }

    public Image getImage(){
        return LASER_IMAGE;
    }

    @Override
    public boolean canHit(IslandObject other) {
        return other.isFalling();
    }

    public int getX(){
        return super.getX();
    }

    public int getY(){
        return super.getY();
    }

    @Override
    public boolean isTouching(IslandObject other) {
        int thisTop = getY();
        int otherBottom = other.getY();

        int thisCenterX = getX();
        int otherLeft = other.getX();
        int otherRight = other.getX() + other.width;

        return thisCenterX >= otherLeft && thisCenterX <= otherRight && thisTop <= otherBottom;
    }
}
