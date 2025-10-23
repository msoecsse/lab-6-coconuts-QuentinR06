package coconuts;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

// JavaFX Controller class for the game - generally, JavaFX elements (other than Image) should be here
public class GameController {

    /**
     * Time between calls to step() (ms)
     */
    private static final double MILLISECONDS_PER_STEP = 1000.0 / 30;
    private Timeline coconutTimeline;
    private boolean started = false;
    private final int laserCooldown = 500;

    @FXML
    private Pane gamePane;
    @FXML
    private Pane theBeach;
    private OhCoconutsGameManager theGame;
    @FXML
    private Label beached;
    @FXML
    private Label destroyed;

    @FXML
    public void initialize() {
        theGame = new OhCoconutsGameManager((int) (gamePane.getPrefHeight() - theBeach.getPrefHeight()),
                (int) (gamePane.getPrefWidth()), gamePane, theBeach);

        gamePane.setFocusTraversable(true);

        coconutTimeline = new Timeline(new KeyFrame(Duration.millis(MILLISECONDS_PER_STEP), (e) -> {
            theGame.tryDropCoconut();
            theGame.advanceOneTick();
            updateScoreDisplay();
            if (theGame.done())
                coconutTimeline.pause();
        }));
        coconutTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    private long lastFire = 0;
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT && !theGame.done()) {
            theGame.getCrab().crawl(10);
        } else if (keyEvent.getCode() == KeyCode.LEFT && !theGame.done()) {
            theGame.getCrab().crawl(-10);
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            if (!started) {
                coconutTimeline.play();
                started = true;
            } else {
                coconutTimeline.pause();
                started = false;
            }
        } else if (keyEvent.getCode() == KeyCode.UP && !theGame.done()){
            if(System.currentTimeMillis() - lastFire >= laserCooldown) {
                LaserBeam laser = new LaserBeam(theGame, -50, (int) (theGame.getCrab().getX() +
                        theGame.getCrab().getImageView().getFitWidth() / 2));
                lastFire = System.currentTimeMillis();
                theGame.registerObject(laser);
                theBeach.getChildren().add(laser.getImageView());
            }
        }
    }

    private void updateScoreDisplay() {
        Platform.runLater(() -> {
            beached.setText("Beached: " + theGame.getScoreBoard().getCoconutsReachedBeach());
            destroyed.setText("Destroyed: " + theGame.getScoreBoard().getCoconutsDestroyed());
        });
    }
}