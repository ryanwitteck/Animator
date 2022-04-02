import org.junit.Test;

import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * JUnit test cases for the implemented attributes.
 */
public class AttributeTest {

  //---------------------------- Constructor Illegal Arg Exception ---------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testRedOoB1() {
    new Color(-1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedOoB2() {
    new Color(256, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenOoB1() {
    new Color(0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenOoB2() {
    new Color(256, 256, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueOoB1() {
    new Color(0, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueOoB2() {
    new Color(0, 0, 256);
  }

  //---------------------------- Test Functionality ------------------------------------------------

  @Test
  public void testInBounds() {
    Color white = new Color(255, 255, 255);
    assertFalse(white.inBounds(-1));
    assertFalse(white.inBounds(256));
    assertTrue(white.inBounds(0));
    assertTrue(white.inBounds(255));
  }

  @Test
  public void testColor() {
    Color black = new Color(0, 0, 0);
    Color white = new Color(255, 255, 255);
    Color cRand = new Color(90, 55, 58);

    assertNotEquals(black, white);
    assertNotEquals(black, cRand);
    assertNotEquals(white, cRand);

    white.setColor(0, 0, 0);
    assertEquals(white, black);
    white.setColor(90, 55, 58);
    assertEquals(white, cRand);

    black.setR(90);
    black.setG(55);
    black.setB(58);
    assertEquals(white, black);
    assertEquals(cRand, black);
  }

  @Test
  public void testPosn() {
    Posn p1 = new Posn(0, 0);
    Posn p2 = new Posn(-123.329, -751.12);
    Posn p3 = new Posn(446535, 658834);

    assertEquals("( 0.0, 0.0 )", p1.toString());
    assertEquals("( -123.329, -751.12 )", p2.toString());
    assertEquals("( 446535.0, 658834.0 )", p3.toString());

    assertEquals(0, p1.getX(), 0.001);
    assertEquals(0, p1.getY(), 0.001);
    assertEquals(-123.329, p2.getX(), 0.001);
    assertEquals(-751.12, p2.getY(), 0.001);
    assertEquals(446535, p3.getX(), 0.001);
    assertEquals(658834, p3.getY(), 0.001);

    assertEquals(p1, new Posn(p1));
    assertEquals(p2, new Posn(p2));
    assertEquals(p3, new Posn(p3));

    p1.move(0.1423, -231.34534);
    assertEquals(p1, new Posn(0.1423, -231.34534));
    p1.move(0, 0);
    assertEquals(p1, new Posn(0.1423, -231.34534));

    p1.set(-123.329, -751.12);
    assertEquals(p2, p1);

    p2.set(446535, 658834);
    assertEquals(p3, p2);
  }
}