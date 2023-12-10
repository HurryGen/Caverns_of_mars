package java1_2023_tur0183;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Projectile implements DrawableSimulable,Collisionable {
    private Point2D position;
    private Point2D velocity;
    private Rectangle2D boundingBox;
    public Projectile(Point2D position,Point2D velocity){
        this.position = position;
        this.velocity = velocity;
        this.boundingBox = new Rectangle2D(position.getX(), position.getY(), 10, 10);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.PURPLE);
        gc.fillRect(position.getX(),position.getY(),10,10);
        //gc.setStroke(Color.BLUE); // Set the color for the boundingBox
        //gc.strokeRect(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getWidth(), boundingBox.getHeight());
    }

    @Override
    public void simulate(double deltaT) {
        this.position = this.position.subtract(velocity.multiply(deltaT));
        this.boundingBox = new Rectangle2D(position.getX(), position.getY(), 10, 10);
    }
    @Override
    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public Point2D getPosition() {
        return position;
    }
}
