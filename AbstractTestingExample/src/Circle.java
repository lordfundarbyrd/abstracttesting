public class Circle implements Shape {

  private int radius;

  public Circle(int radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException("Radius must be greater than 0");
    }

    this.radius = radius;
  }

  @Override
  public double getPerimeter() {
    return 2 * Math.PI * radius;
  }

  @Override
  public double getArea() {
    return Math.PI * Math.pow(radius, 2);
  }

  @Override
  public boolean isRegular() {
    return true;
  }
}
