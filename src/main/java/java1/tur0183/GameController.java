package java1.tur0183;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class GameController {
    @FXML
    private Canvas canvas;

    private AnimationTimer timer;
    private Game game;
    private long lastFireTime; // To control firing rate
    private static final long FIRE_DELAY = 500_000_000;
    public GameController(){}

    public void startGame(){
        this.game = new Game(this,canvas.getWidth(), canvas.getHeight());

        timer = new DrawingThread(canvas, game);
        timer.start();
    }

    public void endGame(){
        timer.stop();
    }
    public void  initialize(){
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                this.game.ship.moveRight();
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                this.game.ship.moveLeft();
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                this.game.ship.moveDown();
            } else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                this.game.ship.moveUp();
            } else if (e.getCode() == KeyCode.SPACE) {
                // Fire projectile when SPACE key is pressed
                long currentTime = System.nanoTime();
                if (currentTime - lastFireTime >= FIRE_DELAY) {
                    this.game.spawnProjectile();
                    lastFireTime = currentTime;
                }
            }
        });
    }
}
