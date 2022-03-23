import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.attributes.Color;
import model.attributes.Posn;
import model.shapes.CompoundShape;
import model.shapes.IShape;
import model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * JUnit test cases for the implemented commands.
 */
public class ShapeTest {

  //---------------------------- Constructor Illegal Arg Exception ---------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testInitRectFail1() {
    new Rectangle("R", 0, 0, -1, 12, new Color(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInitRectFail2() {
    new Rectangle("R", 0, 0, 12, -1, new Color(0, 0, 0));
  }

  //---------------------------- Test Functionality ------------------------------------------------

  @Test
  public void testRectMove() {
    Color black = new Color(0, 0, 0);
    Rectangle r1 = new Rectangle("R1", 0, 0, 12, 12, black);
    Rectangle r2 = new Rectangle("R2", -4254, 36632, 19320, 65227, black);

    assertEquals(new Posn(0, 0), r1.getPos());
    assertEquals(new Posn(-4254, 36632), r2.getPos());

    r1.place(new Posn(-353, -641));
    r1.move(24.425, 0);
    r2.place(573253.2378, 13525.056);
    r2.move(-333553.834, -653.23);

    assertEquals(new Posn(-328.575, -641), r1.getPos());
    assertEquals(new Posn(239699.4038, 12871.826), r2.getPos());
  }


  @Test
  public void testRectColor() {
    Color black = new Color(0, 0, 0);
    Rectangle r1 = new Rectangle("R1", 0, 0, 12, 12, black);
    Rectangle r2 = new Rectangle("R2", 0, 0, 12, 12, black);

    r1.setColor(new Color(0, 85, 16));
    r2.setColor(13, 224, 54);
    assertEquals(new Color(13, 224, 54), r2.getColor());
    assertNotEquals(new Color(13, 224, 54), r1.getColor());

    r1.setColor(13, 224, 54);
    assertEquals(r2.getColor(), r1.getColor());
  }

  @Test
  public void testRectScalable() {
    Color black = new Color(0, 0, 0);
    Rectangle r = new Rectangle("R1", 0, 0, 12, 12, black);

    r.scaleDown(12);
    assertEquals(1, r.getWidth());
    assertEquals(1, r.getHeight());

    r.scaleUp(55);
    assertEquals(55, r.getWidth());
    assertEquals(55, r.getHeight());

    r.scaleUp(0.5);
    assertEquals(27, r.getWidth());
    assertEquals(27, r.getHeight());

    r.stretchHorizontal(1.5);
    r.stretchVertical(10);
    assertEquals(40, r.getWidth());
    assertEquals(270, r.getHeight());

    Rectangle copy = (Rectangle)r.getCopy();
    assertEquals(r.getName(), copy.getName());
    assertEquals(r.getPos(), copy.getPos());
    assertEquals(r.getWidth(), copy.getWidth());
    assertEquals(r.getHeight(), copy.getHeight());
    assertEquals(r.getColor(), copy.getColor());
  }

  @Test
  public void testCompShape() {
    List<IShape> list = new ArrayList<IShape>();
    list.add(new Rectangle("R1", 0, 0, 12, 12, new Color(0, 0, 0)));
    list.add(new Rectangle("R2", 2, 2, 4, 4, new Color(8, 8, 8)));
    CompoundShape comp = new CompoundShape("C1", 0, 0, list);

    comp.scaleDown(4);
    assertEquals(3, ((Rectangle)list.get(0)).getWidth());
    assertEquals(3, ((Rectangle)list.get(0)).getHeight());
    assertEquals(1, ((Rectangle)list.get(1)).getWidth());
    assertEquals(1, ((Rectangle)list.get(1)).getHeight());

    comp.scaleUp(2);
    assertEquals(6, ((Rectangle)list.get(0)).getWidth());
    assertEquals(6, ((Rectangle)list.get(0)).getHeight());
    assertEquals(2, ((Rectangle)list.get(1)).getWidth());
    assertEquals(2, ((Rectangle)list.get(1)).getHeight());

    comp.stretchHorizontal(7.07);
    comp.stretchVertical(2.5);
    assertEquals(42, ((Rectangle)list.get(0)).getWidth());
    assertEquals(15, ((Rectangle)list.get(0)).getHeight());
    assertEquals(14, ((Rectangle)list.get(1)).getWidth());
    assertEquals(5, ((Rectangle)list.get(1)).getHeight());

    CompoundShape copy = (CompoundShape)comp.getCopy();
    assertEquals(comp.getName(), copy.getName());
    assertEquals(comp.getPos(), copy.getPos());
    assertEquals(comp.getShapes(), copy.getShapes());
  }

  //---------------------------- Function Exceptions -----------------------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testScaleFail1() {
    Rectangle r = new Rectangle("R", 0, 0, 12, 12, new Color(0, 0, 0));
    r.scaleError(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScaleFail2() {
    CompoundShape c = new CompoundShape("C", 0, 0, new ArrayList<IShape>());
    c.scaleError(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectColorFail() {
    Rectangle r = new Rectangle("R", 0, 0, 12, 12, new Color(0, 0, 0));
    r.setColor(null);
  }

  @Test(expected = IllegalStateException.class)
  public void testCompColorFail1() {
    CompoundShape c = new CompoundShape("C", 0, 0, new ArrayList<IShape>());
    c.setColor(0, 0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testCompColorFail2() {
    CompoundShape c = new CompoundShape("C", 0, 0, new ArrayList<IShape>());
    c.setColor(new Color(0, 0, 0));
  }
}