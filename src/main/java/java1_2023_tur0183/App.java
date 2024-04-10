package java1_2023_tur0183;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.h2.tools.Server;

import java.io.IOException;
import java.sql.SQLException;




/**
 * JavaFX App
 */
@Log4j2
public class App extends Application {

    private static Scene scene;
    private Canvas canvas;
    private AnimationTimer timer;

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 475;
    private GameController controller;
    public static EntityManager em;


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


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("java2");
        em = emf.createEntityManager();

        try {
            Server server = Server.createWebServer();
            log.info(("To inspect DB go to URL: " + server.getURL()));
            server.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        launch();
    }

}