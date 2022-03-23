import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.attributes.Color;
import model.attributes.Posn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * JUnit test cases for the implemented commands.
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

    assertFalse(black.equals(white));
    assertFalse(black.equals(cRand));
    assertFalse(white.equals(cRand));

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
    
  }
}