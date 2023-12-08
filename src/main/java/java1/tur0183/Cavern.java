package java1.tur0183;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.Random;

public class Cavern implements DrawableSimulable {

    private double yOffset; // Vertical offset
    private Image mapImage = new Image(getClass().getResourceAsStream("map.png"));

    public Cavern(){
        this.yOffset = 0;
    }




    @Override
    public void draw(GraphicsContext gc) {
        double aspectRatio = mapImage.getWidth() / mapImage.getHeight();
        double destinationHeight = App.CANVAS_WIDTH / aspectRatio;
        gc.drawImage(mapImage,
                0, 0, // source coordinates
                mapImage.getWidth(), mapImage.getHeight(), // width and height of the source
                0, yOffset, // destination coordinates
                App.CANVAS_WIDTH , destinationHeight // destination width and height
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

    public boolean checkColision(Ship ship){
        PixelReader pixelReader = mapImage.getPixelReader();
        double xRatio = mapImage.getWidth()/App.CANVAS_WIDTH;
        double yRatio = mapImage.getHeight()/(App.CANVAS_WIDTH/(mapImage.getWidth() / mapImage.getHeight()));

        for (double x = ship.getHitbox().getMinX(); x < ship.getHitbox().getMaxX(); x++) {
            for (double y = ship.getHitbox().getMinY(); y < ship.getHitbox().getMaxY(); y++) {
                int xx = (int) (x*xRatio);
                int yy = (int) ((y-yOffset)*yRatio);
                Color pixelColor = pixelReader.getColor(xx, yy);
                if (pixelColor.getOpacity() > 0) {
                    return true; // Collision detected
                }
            }
        }
        return false;
    }



}
