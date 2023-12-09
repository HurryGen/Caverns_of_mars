package java1.tur0183;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public interface Collisionable {
    Rectangle2D getBoundingBox();

    default boolean intersects(Collisionable other) {
        return getBoundingBox().intersects(other.getBoundingBox());
    }
}
