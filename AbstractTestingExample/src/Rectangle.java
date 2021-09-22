public class Rectangle extends Quadrilateral {

  public Rectangle(int width, int height) {
    super(width, height);
  }

  @Override
  public boolean isRegular() {
    return (width == height);
  }

}
