package java1.tur0183;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Vector;

public class Projectile implements DrawableSimulable {
    private Point2D position;
    private Point2D velocity;
    public Projectile(Point2D position,Point2D velocity){
        this.position = position;
        this.velocity = velocity;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.PURPLE);
        gc.fillRect(position.getX()-40,position.getY()+15,10,10);
        gc.fillRect(position.getX()+30,position.getY()+15,10,10);
    }

    @Override
    public void simulate(double deltaT) {
        this.position = this.position.subtract(velocity.multiply(deltaT));
    }
}
