package java1_2023_tur0183;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rocket implements DrawableSimulable, Collisionable {
    private Point2D position;
    private Point2D velocity;
    private Rectangle2D boundingBox;
    private Image rocketImage = new Image(getClass().getResourceAsStream("rocket.png"));
    public Rocket(Point2D position,Point2D velocity) {
        this.position = position;
        this.velocity = velocity;
        this.boundingBox = new Rectangle2D(position.getX()-15, position.getY()-20, rocketImage.getWidth()+20, rocketImage.getHeight()+20);
    }



    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(rocketImage,
                position.getX()-15, position.getY()-20, // source coordinates
                rocketImage.getWidth()+20, rocketImage.getHeight()+20
        );
        //gc.setStroke(Color.RED); // Set the color for the boundingBox
        //gc.strokeRect(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getWidth(), boundingBox.getHeight());
    }

    @Override
    public void simulate(double deltaT) {
        this.position = this.position.subtract(velocity.multiply(deltaT));
        this.boundingBox = new Rectangle2D(position.getX()-15, position.getY()-20, rocketImage.getWidth()+20, rocketImage.getHeight()+20);
    }
    @Override
    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public Point2D getPosition() {
        return position;
    }

    public void fly(){
        velocity = new Point2D(0, 200);
    }

}
