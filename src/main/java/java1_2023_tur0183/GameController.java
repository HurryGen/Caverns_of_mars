package java1_2023_tur0183;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.FileWriter;
import java.io.IOException;

public class GameController {
    @FXML
    private Canvas canvas;

    @FXML
    private Button startButton;

    @FXML
    private TextField textField;

    private AnimationTimer timer;
    private Game game;

    private String playerName;
    private long lastFireTime;
    private static final long FIRE_DELAY = 500_000_000;
    public GameController(){}

    public void startGame(){
        this.game = new Game(this,canvas.getWidth(), canvas.getHeight());

        timer = new DrawingThread(canvas, game);
        timer.start();
        startButton.setVisible(false);
        textField.setVisible(false);
        playerName = textField.getText();
    }

    public void endGame(){
        timer.stop();
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Konec hry");
            alert.setHeaderText("Konec hry");
            alert.setContentText("Tvoje skóre bylo zapsáno do souboru.");
            alert.showAndWait();
        });

        String fileName = "score.txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.append(playerName).append(", Skóre: ").append(Integer.toString(this.game.getPoints())).append('\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void  initialize(){
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                this.game.ship.moveRight();
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                this.game.ship.moveLeft();
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                if(this.game.ship.getBoundingBox().getMaxY() < App.CANVAS_HEIGHT-50)
                    this.game.ship.moveDown();
            } else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                if(this.game.ship.getBoundingBox().getMinY() > 0)
                    this.game.ship.moveUp();
            } else if (e.getCode() == KeyCode.SPACE) {
                long currentTime = System.nanoTime();
                if (currentTime - lastFireTime >= FIRE_DELAY) {
                    this.game.spawnProjectile();
                    lastFireTime = currentTime;
                }
            }
        });
    }

    public String getPlayerName() {
        return playerName;
    }
}
