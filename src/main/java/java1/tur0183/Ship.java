package java1.tur0183;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ship implements DrawableSimulable {
    private final Point2D velocity;
    private Point2D position;
    public Ship(Point2D position, Point2D velocity){
        this.position = position;
        this.velocity = velocity;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        //kabina
        gc.fillRect(position.getX(),position.getY()-10, 10,5);
        gc.fillRect(position.getX(),position.getY(), 10,10);
        gc.fillRect(position.getX()-10,position.getY()-10, 10,5);
        gc.fillRect(position.getX()-10,position.getY(), 10,10);
        //leve kridlo
        gc.fillRect(position.getX()-20,position.getY()-7, 10,13);
        gc.fillRect(position.getX()-30,position.getY()-3, 10,15);
        gc.fillRect(position.getX()-40,position.getY()+5, 10,15);
        //prave kridlo
        gc.fillRect(position.getX()+10,position.getY()-7, 10,13);
        gc.fillRect(position.getX()+20,position.getY()-3, 10,15);
        gc.fillRect(position.getX()+30,position.getY()+5, 10,15);
    }

    @Override
    public void simulate(double deltaT) {
        this.position = this.position.subtract(velocity.multiply(deltaT));
    }

    public void moveRight(){
        this.position = position.add(new Point2D(50,0));
    }
    public void moveLeft(){
        this.position = position.add(new Point2D(-50,0));
    }
    public void moveDown(){
        this.position = position.add(new Point2D(0,50));
    }
    public void moveUp(){
        this.position = position.add(new Point2D(0,-50));
    }

    public Point2D getPosition() {
        return position;
    }

}
