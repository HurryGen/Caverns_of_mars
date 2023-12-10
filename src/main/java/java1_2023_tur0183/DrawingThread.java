package java1_2023_tur0183;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawingThread extends AnimationTimer {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final static double FPS = 60;

    private long previous_time;
    private Game game;

    public DrawingThread(Canvas canvas, Game game) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.game = game;
    }

    @Override
    public void handle(long now) {
        if(previous_time > 0){
            double deltaT = (now - previous_time) / 1e9;
            game.draw(gc);
            game.simulate(deltaT);
            canvas.requestFocus();
            if(deltaT < 1/FPS){
                return;
            }
        }
        previous_time = now;
    }
}
