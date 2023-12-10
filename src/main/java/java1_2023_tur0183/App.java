package java1_2023_tur0183;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Canvas canvas;
    private AnimationTimer timer;

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 475;
    private GameController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 475);
        stage.setScene(scene);
        stage.setTitle("Caverns of mars");
        stage.setResizable(false);
        stage.show();
        controller = fxmlLoader.getController();
    }


    public static void main(String[] args) {
        launch();
    }

}