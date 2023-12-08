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
    public final Cavern cavern;

    private DrawableSimulable[] entities ;
    private List<Projectile> projectiles;
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


}
public void simulate(double deltaT){
    for (DrawableSimulable entity : entities){
        entity.simulate(deltaT);

    }
    Iterator<Projectile> iterator = projectiles.iterator();
    while (iterator.hasNext()) {
        Projectile projectile = iterator.next();
        projectile.simulate(deltaT);
    }
}
    public void spawnProjectile() {
        Projectile projectile = new Projectile(new Point2D(this.ship.getPosition().getX(), this.ship.getPosition().getY()), new Point2D(0,-200));
        projectiles.add(projectile);
    }

}
