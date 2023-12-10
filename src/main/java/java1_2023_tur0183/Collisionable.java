package java1_2023_tur0183;

import javafx.geometry.Rectangle2D;

public interface Collisionable {
    Rectangle2D getBoundingBox();

    default boolean intersects(Collisionable other) {
        return getBoundingBox().intersects(other.getBoundingBox());
    }
}
