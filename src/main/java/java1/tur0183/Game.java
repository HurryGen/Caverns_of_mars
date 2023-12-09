package java1.tur0183;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {

    private final double width;
    private final double height;
    public final Ship ship;
    private final Cavern cavern;



    private DrawableSimulable[] entities ;
    private List<Projectile> projectiles;
    private List<Fuel> fuels;
    private List<Rocket> rockets;
    private final GameController controller;
public Game(GameController controller,double width, double height){
    this.controller = controller;
    this.width = width;
    this.height = height;
    this.entities = new DrawableSimulable[]{
            ship = new Ship(new Point2D(300,100),new Point2D(0,0)),
            cavern = new Cavern(),


    };
    this.projectiles = new ArrayList<>();
    this.fuels = this.cavern.getFuels();
    this.rockets = this.cavern.getRockets();

}


void draw(GraphicsContext gc){
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, width, height);
    for (DrawableSimulable entity : entities){
        entity.draw(gc);
    }
    for (Projectile projectile : projectiles) {
        projectile.draw(gc);
    }
    for (Fuel fuel : fuels) {
        fuel.draw(gc);
    }
    for (Rocket rocket : rockets) {
        rocket.draw(gc);
    }


}
public void simulate(double deltaT){
    for (DrawableSimulable entity : entities){
        entity.simulate(deltaT);
    }
    if(this.cavern.checkColision(this.ship)){
        controller.endGame();
    }
    Iterator<Projectile> projectileIterator = projectiles.iterator();
    while (projectileIterator.hasNext()) {
        Projectile projectile = projectileIterator.next();
        projectile.simulate(deltaT);
        if(this.cavern.checkColision(projectile)){
            projectileIterator.remove();
        }
    }

    Iterator<Fuel> fuelIterator = fuels.iterator();
    while(fuelIterator.hasNext()){
        Fuel fuel = fuelIterator.next();
        fuel.simulate(deltaT);
        for (int i = 0; i < projectiles.size(); i++) {
            if(projectiles.get(i).intersects(fuel)){
                fuelIterator.remove();
                projectiles.remove(i);
            }
        }
    }
    Iterator<Rocket> rocketIterator = rockets.iterator();
    while(rocketIterator.hasNext()){
        Rocket rocket = rocketIterator.next();
        rocket.simulate(deltaT);
        if((rocket.getPosition().getY() + this.cavern.getYOffset()) < App.CANVAS_HEIGHT){
            rocket.fly();
        }
    }


}
    public void spawnProjectile() {
        Projectile projectile1 = new Projectile(new Point2D(this.ship.getPosition().getX()-40, this.ship.getPosition().getY()+15), new Point2D(0,-200));
        Projectile projectile2 = new Projectile(new Point2D(this.ship.getPosition().getX()+30, this.ship.getPosition().getY()+15), new Point2D(0,-200));
        projectiles.add(projectile1);
        projectiles.add(projectile2);
    }

}
