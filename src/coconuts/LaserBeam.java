package coconuts;

import javafx.scene.image.Image;

// Represents the beam of light moving from the crab to a coconut; can hit only falling objects
// This is a domain class; do not introduce JavaFX or other GUI components here
public class LaserBeam extends HittableIslandObject {
    private static final int WIDTH = 2; // must be updated with image
    private static final Image laserImage = new Image("file:images/laser-1.png");

    public LaserBeam(OhCoconutsGameManager game, int eyeHeight, int crabCenterX) {
        super(game, crabCenterX, eyeHeight, WIDTH, laserImage);
        super.getImageView().setFitWidth(10);
    }

    public int hittable_height() {
        return y + WIDTH;
    }

    @Override
    public void step() {
        y -= 8;
        display();
    }

    public Image getImage(){
        return laserImage;
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
        //top of laser
        int thisTop = getY();
        //bottom of falling coconut
        int otherBottom = other.getY();

        //check if laser has reached the coconut


        //check if laser center is between left and right of coconut
        int thisCenterX = getX();
        int otherLeft = other.getX();
        int otherRight = other.getX() + other.width;

        return thisCenterX >= otherLeft && thisCenterX <= otherRight && thisTop <= otherBottom;


    }


}
