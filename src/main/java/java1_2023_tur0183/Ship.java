package java1_2023_tur0183;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ship implements DrawableSimulable,Collisionable {
    private final Point2D velocity;
    private Point2D position;

    private Rectangle2D boundingBox;
    private long lastTime; // To control firing rate
    private static final long DELAY = 500_000_000;// Added boundingBox
    private int fuel;

    public Ship(Point2D position, Point2D velocity){
        this.position = position;
        this.velocity = velocity;

        this.boundingBox = new Rectangle2D(position.getX()-40, position.getY()-10, 80, 30); // Adjust the dimensions accordingly
        this.fuel = 1000;
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


        //gc.setStroke(Color.RED); // Set the color for the boundingBox
        //gc.strokeRect(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getWidth(), boundingBox.getHeight());
    }

    @Override
    public void simulate(double deltaT) {
        this.position = this.position.subtract(velocity.multiply(deltaT));
        this.boundingBox = new Rectangle2D(position.getX()-40, position.getY()-10, 80, 30);
        long currentTime = System.nanoTime();
        if(currentTime - lastTime >= DELAY){
            this.fuel -= 20;
            lastTime = currentTime;
        }
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
    @Override
    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
