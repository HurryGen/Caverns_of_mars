package java1.tur0183;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class Cavern implements DrawableSimulable {

    private double yOffset; // Vertical offset

    public Cavern(){
        this.yOffset = 0;
    }




    @Override
    public void draw(GraphicsContext gc) {
        Image mapImage = new Image(getClass().getResourceAsStream("map.png"));
        double aspectRatio = mapImage.getWidth() / mapImage.getHeight();
        double destinationHeight = 800 / aspectRatio;

        gc.drawImage(mapImage,
                0, 0, // source coordinates
                336, 6792, // width and height of the source
                0, yOffset, // destination coordinates
                800, destinationHeight // destination width and height
        );
        DropShadow ds = new DropShadow();
        ds.setColor(Color.RED);
        ds.setSpread(10);

    }

    @Override
    public void simulate(double deltaT) {
        double speed = 80.0; // Adjust this value to control the speed of movement
        yOffset -= speed * deltaT;
    }



}
