import org.junit.Before;
import org.junit.Test;

import cs3500.animator.io.OurModelBuilder;
import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for our ModelBuilder class.
 */
public class OurBuilderTest {

  private TweenModelBuilder<IAnimation> builder;

  @Before
  public void init() {
    builder = new OurModelBuilder();
  }

  // ------------------------ Set Bounds Method Tests ----------------------------------------------

  @Test
  public void testSetBounds() {
    IAnimation a = builder.setBounds(100, 100).build();

    assertEquals(100, a.getWindowWidth());
    assertEquals(100, a.getWindowHeight());

    builder.setBounds(15342, 34343);
    assertEquals(15342, a.getWindowWidth());
    assertEquals(34343, a.getWindowHeight());
  }

  // ------------------------ Set Bounds Error Tests -----------------------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsError1() {
    IAnimation a = builder.setBounds(-10, 100).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsError2() {
    IAnimation a = builder.setBounds(0, -1).build();
  }

  // ------------------------ Add Shape Method Tests -----------------------------------------------

  @Test
  public void testAddShapes() {
    IAnimation a = builder.addOval("O", 12, 50,
                    (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 1, 10)
            .addRectangle("R", 90, 50,
                    (float) 1235.8, (float) 4999.99, 1, 1, 0, 4, 10).build();

    Oval o = new Oval("O", 12, 50, 12.43, 42.4, new Color(255, 0, 102));
    Rectangle r = new Rectangle("R", 90, 50, 1235.8, 4999.99, new Color(255, 255, 0));
    assertEquals(o, a.getDrawable("O"));
    assertEquals(r, a.getDrawable("R"));
  }

  // ------------------------ Add Oval Error Tests -------------------------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError1() {
    // negative width
    builder.addOval("O", 32, 12, (float) -1, (float) 42.4, 1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError2() {
    // negative height
    builder.addOval("O", 32, 12, (float) 12.43, (float) -1, 1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError3() {
    // blank name
    builder.addOval("", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError4() {
    // red out of bounds
    builder.addOval("O", 12, 50, (float) 12.43, (float) 42.4, (float) 1.1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError5() {
    // green out of bounds
    builder.addOval("O", 12, 50, (float) 12.43, (float) 42.4, 1, -1, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError6() {
    // blue out of bounds
    builder.addOval("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 1.1, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError7() {
    // negative end time
    builder.addOval("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 1, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError8() {
    // negative start time
    builder.addOval("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, -1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOvalError9() {
    // start time > end time
    builder.addOval("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 2, 1);
  }

  // ------------------------ Add Rectangle Error Tests --------------------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError1() {
    // negative width
    builder.addRectangle("O", 32, 12, (float) -1, (float) 42.4, 1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError2() {
    // negative height
    builder.addRectangle("O", 32, 12, (float) 12.43, (float) -1, 1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError3() {
    // blank name
    builder.addRectangle("", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError4() {
    // red out of bounds
    builder.addRectangle("O", 12, 50, (float) 12.43, (float) 42.4, -1, 0, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError5() {
    // green out of bounds
    builder.addRectangle("O", 12, 50, (float) 12.43, (float) 42.4,
            1, (float) 1.001, (float) 0.4, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError6() {
    // blue out of bounds
    builder.addRectangle("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) -0.1, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError7() {
    // negative end time
    builder.addRectangle("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 1, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError8() {
    // negative start time
    builder.addRectangle("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, -1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleError9() {
    // start time > end time
    builder.addRectangle("O", 12, 50, (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 2, 1);
  }

  // ------------------------ Add Move Tests -------------------------------------------------------

  @Test
  public void testAddMove() {
    IAnimation a = builder.addOval("O", 12, 50,
                    (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 1, 10)
            .addRectangle("R", 90, 50,
                    (float) 1235.8, (float) 4999.99, 1, 1, 0, 4, 10)
            .addMove("O", 12, 50, 100, -123, 2, 4)
            .build();

    Oval o = new Oval("O", 100, -123, 12.43, 42.4, new Color(255, 0, 102));
    assertEquals(o, a.getDrawable("O"));

    builder.addMove("O", 100, -123, -123, -900, 4, 9).build();
    o.place(-123, -900);
    assertEquals(o, a.getDrawable("O"));
  }

  // ------------------------ Add Move Error Tests -------------------------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveError1() {
    // negative end time
    builder.addMove("O", 0, 12, (float) 12.43, (float) 42.4, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveError2() {
    // negative start time
    builder.addMove("O", 0, 12, (float) 12.43, (float) 42.4, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveError3() {
    // start time > end time
    builder.addMove("O", 0, 12, (float) 12.43, (float) 42.4, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveError4() {
    // blank name
    builder.addMove("", 0, 12, (float) 12.43, (float) 42.4, 1, 0);
  }

  // ------------------------ Add Color Change Tests -----------------------------------------------

  @Test
  public void testAddColorChange() {
    IAnimation a = builder.addOval("O", 12, 50,
                    (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 0, 10)
            .addColorChange("O", 1, 0, (float) 0.4, 0, 1, 1, 1, 1)
            .build();

    Oval o = new Oval("O", 12, 50, 12.43, 42.4, new Color(0, 255, 255));
    assertEquals(o, a.getDrawable("O"));

    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) 0.9, (float) 0.4, 2, 2).build();
    o.setColor(0.12 * 255, 0.9 * 255, 0.4 * 255);
    assertEquals(o, a.getDrawable("O"));
  }

  // ------------------------ Add Color Change Error Tests -----------------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError1() {
    // red out of bounds
    builder.addColorChange("O", 0, 1, 1,  (float) 1.12, (float) 0.9, (float) 0.4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError2() {
    // green out of bounds
    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) 1.9, (float) 0.4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError3() {
    // blue out of bounds
    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) 0.9, (float) 1.4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError4() {
    // red out of bounds
    builder.addColorChange("O", 0, 1, 1,  (float) -0.12, (float) 0.9, (float) 0.4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError5() {
    // green out of bounds
    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) -0.9, (float) 0.4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError6() {
    // blue out of bounds
    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) 0.9, (float) -0.4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError7() {
    // negative end time
    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) 0.9, (float) 0.4, 2, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError8() {
    // negative start time
    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) 0.9, (float) 0.4, -2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError9() {
    // start time > end time
    builder.addColorChange("O", 0, 1, 1,  (float) 0.12, (float) 0.9, (float) 0.4, 3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeError10() {
    // blank name
    builder.addColorChange("", 0, 1, 1,  (float) 0.12, (float) 0.9, (float) 0.4, 2, 2);
  }
}
