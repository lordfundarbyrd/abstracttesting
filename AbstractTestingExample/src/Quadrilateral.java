public abstract class Quadrilateral implements Shape {

  protected int width;
  protected int height;

  public Quadrilateral(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Sides must be greater than 0.");
    }

    this.width = width;
    this.height = height;
  }

  @Override
  public double getPerimeter() {
    return 2*width + 2*height;
  }

  @Override
  public double getArea() {
    return width * height;
  }

  @Override
  public abstract boolean isRegular();

}