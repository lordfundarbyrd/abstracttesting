import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/*
**NOTE** I did not thoroughly test every method in the way that it is expected, but I wrote out
         various scenarios that may be helpful when abstracting tests. Also there are a lot of
         comments in here, but hopefully they are helpful.

Steps (will be reiterated throughout this file)
1) Make your main testing class abstract (in this case, ShapeTests)
2) Somewhere in your abstract class, make an abstract method to be implemented by later subclasses.
   The purpose of this method will be to construct the different implementations of your abstract
   class (this will make more sense further down). In this case, the method will be createQuad.

   There are a couple of options -- you can either have it not take in any arguments and just have
   it create new shapes all with the same default parameters, or you can have it pass in arguments
   when it is called. I have included examples of both.

   In this case, we made static classes for Rectangle, Rhombus, and Square. We do not need one for
   Circle, as that implements Shape but does not extend Quadrilateral, so that can be tested as
   normal (since Circle is not abstracted).
3) Write the tests in your abstract class as normal that should be abstracted.
4) Make static classes inside of your abstract class (one for each different subclass you have).
5) In your abstract test class, put the tests where the result will be the same for all of your
   subclasses. In each test, call your abstract method that creates the abstracted objects. When you
   call this, Junit will know that you have multiple static subclasses implementing that method, and
   will call the method for all subclasses.
6) If there are tests that are specific to a specific class (for instance, a test will only pass
   if it is a Square), put that test in the relevant subclass.

To run:
1) Click the green arrows on the left side and run tests
2) Either select a specific class to run (if you know one class is failing), or select to run all
   the test classes.
*/

public abstract class ShapeTests { // make abstract
  Shape s;

  /*
  @Before means this method will be called before every test is run-- good practice if
  you are mutating things in your tests. It's one less line you have to put in every test
  in order to make sure everything is starting off fresh before you run tests on it.
  */
  @Before
  public void init() {
    /* call the abstract method -- Junit will know to call it for all of your static
    testing classes. You can either initialize s in the init method or in each test method
    (depending on what you need, i.e. if you want to vary parameters). */
    s = createQuad(10);
  }

  @Test
  public void testPerimeter() {
    /*
    This test will be the same for all the abstracted classes, so we can just call getPerimeter()
    on s. It will run the test for each static test class.
    */
    assertEquals(40, s.getPerimeter(), 0.01); // call test as normal

    // testing on class that does not extend an abstract class
    assertEquals(20 * Math.PI, new Circle(10).getPerimeter(), 0.01);
  }

  // You can also abstract exception tests, as it will run separate tests for all 3 classes.
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalQuad() {
    s = createQuad(-10);
  }

  // Test on circle, as normal
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCircle() {
    s = new Circle(0);
  }

  @Test
  public void testArea() {
    assertEquals(100, s.getArea(), 0.01);
  }

  /* This is the abstract method that will be implemented by the subclasses. This is essentially
  a constructor that will create our shapes for testing. */
  public abstract Shape createQuad(int side);

  // Static test class for Rhombuses.
  public static class RhombusTests extends ShapeTests {

    // This will return a new Rhombus with the given side length.
    public Shape createQuad(int side) {
      return new Rhombus(side);
    }

    /* The isRegular test is going to differ for each implementation, so this can be tested in each
    static class separately. Theoretically, you could also put individual tests inside the abstract
    test class, but it's also nice to keep tests separated by Shape like this. */
    @Test
    public void testIsRegular() {
      assertFalse(this.createQuad(10).isRegular());
    }

  }

  // Static test class for Squares.
  public static class SquareTests extends ShapeTests {

    // This will return a new Square with the given side length.
    public Shape createQuad(int side) {
      return new Square(side);
    }

    @Test
    public void testIsRegular() {
      assertTrue(this.createQuad(10).isRegular());
    }
  }

  // Static test class for Rectangles.
  public static class RectangleTests extends ShapeTests {

    // This will return a new Rectangle with the given side length.
    public Shape createQuad(int side) {
      return new Rectangle(side, side);
    }

    @Test
    public void testIsRegular() {
      assertTrue(this.createQuad(10).isRegular());
      assertFalse(new Rectangle(10, 20).isRegular());
    }

    // Testing constructing an illegal rectangle, but the sides are different
    // (as createQuad only takes one argument)
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRectangle() {
      new Rectangle(-10, 0);
    }
  }

}
