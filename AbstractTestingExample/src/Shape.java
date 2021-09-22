/**
 * An interface to represent a shape. Contains methods for calculating perimeter and area, as well
 * as determining if it is a regular shape (all angles and sides are the same).
 * Note: this is not a very substantial project and also not a very practical one, but it's just a
 * random example so you can see how to abstract tests.
 */
public interface Shape {

  /**
   * Calculates the perimeter of the shape.
   * @return the perimeter.
   */
  double getPerimeter();

  /**
   * Calculates the area of the shape.
   * @return the area.
   */
  double getArea();

  /**
   * Determines if a shape is regular (all sides and angles are the same).
   * @return if the shape is regular.
   */
  boolean isRegular();

}
