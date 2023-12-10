package java1.tur0183;


import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Cavern implements DrawableSimulable {

    private double yOffset;
    private Image mapImage = new Image(getClass().getResourceAsStream("map_v2.png"));

    private List<Fuel> fuels;
    private List<Rocket> rockets;

    public Cavern(){
        this.yOffset = 0;
        this.fuels = new ArrayList<>();
        this.rockets = new ArrayList<>();
        generateFuels();
        generateRockets();

    }




    @Override
    public void draw(GraphicsContext gc) {
        double aspectRatio = mapImage.getWidth() / mapImage.getHeight();
        double destinationHeight = App.CANVAS_WIDTH / aspectRatio;
        gc.drawImage(mapImage,
                0, 0,
                mapImage.getWidth(), mapImage.getHeight(),
                0, yOffset,
                App.CANVAS_WIDTH , destinationHeight
        );



    }

    @Override
    public void simulate(double deltaT) {
        double speed = 80.0;


        yOffset -= speed * deltaT;

    }

    public boolean checkColision(Collisionable collisionable){
        PixelReader pixelReader = mapImage.getPixelReader();
        double xRatio = mapImage.getWidth()/App.CANVAS_WIDTH;
        double yRatio = mapImage.getHeight()/(App.CANVAS_WIDTH/(mapImage.getWidth() / mapImage.getHeight()));

        for (double x = collisionable.getBoundingBox().getMinX(); x < collisionable.getBoundingBox().getMaxX(); x++) {
            for (double y = collisionable.getBoundingBox().getMinY(); y < collisionable.getBoundingBox().getMaxY(); y++) {
                int xx = (int) (x*xRatio);
                int yy = (int) ((y-yOffset)*yRatio);
                Color pixelColor = pixelReader.getColor(xx, yy);
                if (pixelColor.equals(Color.BLACK)){
                    return false;
                } else if (pixelColor.getOpacity() > 0) {

                    return true;
                }
            }
        }
        return false;
    }

    private void generateFuels() {
        PixelReader pixelReader = mapImage.getPixelReader();
        double aspectRatio = mapImage.getWidth() / mapImage.getHeight();
        double destinationHeight = App.CANVAS_WIDTH / aspectRatio;

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {
                int xx = (int) (x*(App.CANVAS_WIDTH/ mapImage.getWidth()));
                int yy = (int) (y*(destinationHeight/ mapImage.getHeight()));
                Color pixelColor = pixelReader.getColor(x, y);


                if (pixelColor.equals(Color.RED)) {
                    Fuel fuel = new Fuel(new Point2D(xx, yy+2), new Point2D(0, 80));
                    fuels.add(fuel);
                }
            }
        }
    }
    private void generateRockets() {
        PixelReader pixelReader = mapImage.getPixelReader();
        double aspectRatio = mapImage.getWidth() / mapImage.getHeight();
        double destinationHeight = App.CANVAS_WIDTH / aspectRatio;

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {
                int xx = (int) (x*(App.CANVAS_WIDTH/ mapImage.getWidth()));
                int yy = (int) (y*(destinationHeight/ mapImage.getHeight()));
                Color pixelColor = pixelReader.getColor(x, y);


                if (pixelColor.equals(Color.BLACK)) {
                    Rocket rocket = new Rocket(new Point2D(xx, yy+2), new Point2D(0, 80));
                    rockets.add(rocket);
                }
            }
        }
    }

    public List<Fuel> getFuels() {
        return fuels;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public double getYOffset() {
        return yOffset;
    }
}
