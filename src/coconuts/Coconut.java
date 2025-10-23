package coconuts;

import javafx.scene.image.Image;

// Represents the falling object that can kill crabs. If hit by a laser, the coconut disappears
// This is a domain class; other than Image, do not introduce JavaFX or other GUI components here
public class Coconut extends HittableIslandObject {
    private static final int WIDTH = 50;
    private static final Image coconutImage = new Image("file:images/coco-1.png");

    public Coconut(OhCoconutsGameManager game, int x) {
        super(game, x, 0, WIDTH, coconutImage);
    }

    @Override
    public void step() {
        y += 5;
    }

    @Override
    public boolean isFalling() {
        return true;
    }

    @Override
    public boolean canHit(IslandObject other) {
        return other.isGroundObject() || other.isHittable();
    }

    @Override
    public boolean isTouching(IslandObject other) {
        // Coconut position
        int coconutBottom = (y + width) - 70;
        int coconutCenterX = x + width / 2;

        // Other object position
        int otherTop = other.getY();
        int otherLeft = other.getX();
        int otherRight = other.getX() + other.getWidth();

        boolean verticalTouch = Math.abs(coconutBottom - otherTop) <= 10;

        boolean horizontalTouch = coconutCenterX >= otherLeft && coconutCenterX <= otherRight;

        return verticalTouch && horizontalTouch;
    }
}
