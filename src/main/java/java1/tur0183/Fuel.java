package java1.tur0183;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class Fuel implements DrawableSimulable, Collisionable {
    private Point2D position;
    private Point2D velocity;
    private Rectangle2D boundingBox;
    private Image fuelImage = new Image(getClass().getResourceAsStream("fuel.png"));
    public Fuel(Point2D position,Point2D velocity) {
        this.position = position;
        this.velocity = velocity;
        this.boundingBox = new Rectangle2D(position.getX()-20, position.getY()-25, fuelImage.getWidth()+20, fuelImage.getHeight()+10);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(fuelImage,
                position.getX()-20, position.getY()-25, // source coordinates
                fuelImage.getWidth()+20, fuelImage.getHeight()+10
        );
        //gc.setStroke(Color.RED); // Set the color for the boundingBox
        //gc.strokeRect(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getWidth(), boundingBox.getHeight());
    }

    @Override
    public void simulate(double deltaT) {
        this.position = this.position.subtract(velocity.multiply(deltaT));
        this.boundingBox = new Rectangle2D(position.getX()-20, position.getY()-25, fuelImage.getWidth()+20, fuelImage.getHeight()+10);
    }
    @Override
    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }
}
