package java1.tur0183;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public interface Collisionable {
    Point2D[] getBoundingPoints();

    default boolean intersects(Collisionable other) {
        Point2D[] points1 = getBoundingPoints();
        Point2D[] points2 = other.getBoundingPoints();

        // Check for intersection logic between the two arrays of points
        // You need to implement the actual logic based on your requirements

        // For example, a simple check if any point from one array is equal to any point in the other array
        for (Point2D point1 : points1) {
            for (Point2D point2 : points2) {
                if (point1.equals(point2)) {
                    return true;
                }
            }
        }

        return false;
    }
}
