public class Rhombus extends Quadrilateral {

  public Rhombus(int side) {
    super(side, side);
  }

  @Override
  public boolean isRegular() {
    return false;
  }
}