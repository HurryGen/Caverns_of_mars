package java1.tur0183;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
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


   /* static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }*/

   /* private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }*/

    public static void main(String[] args) {
        launch();
    }

}