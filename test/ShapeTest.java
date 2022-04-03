import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.shapes.CompoundShape;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * JUnit test cases for the implemented shapes.
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

    r.addVertical(15);
    assertEquals(12, r.getWidth(), 0.001);
    assertEquals(27, r.getHeight(), 0.001);

    r.addHorizontal(88);
    assertEquals(100, r.getWidth(), 0.001);
    assertEquals(27, r.getHeight(), 0.001);

    r.addHorizontal(-73);
    assertEquals(27, r.getWidth(), 0.001);
    assertEquals(27, r.getHeight(), 0.001);

    r.stretchHorizontal(1.5);
    r.stretchVertical(10);
    assertEquals(40.5, r.getWidth(), 0.001);
    assertEquals(270, r.getHeight(), 0.001);

    Rectangle copy = (Rectangle) r.getCopy();
    assertEquals(r, copy);
    copy.place(9999, 9999);
    assertNotEquals(r, copy);
  }

  @Test
  public void testCompShape() {
    List<IShape> list = new ArrayList<>();
    list.add(new Rectangle("R1", 0, 0, 12, 12, new Color(0, 0, 0)));
    list.add(new Oval("O1", 2, 2, 4, 4, new Color(8, 8, 8)));
    CompoundShape comp = new CompoundShape("C1", 0, 0, list);

    comp.addHorizontal(4);
    assertEquals(16, ((Rectangle) list.get(0)).getWidth(), 0.001);
    assertEquals(12, ((Rectangle) list.get(0)).getHeight(), 0.001);
    assertEquals(8, ((Oval) list.get(1)).getXRadius(), 0.001);
    assertEquals(4, ((Oval) list.get(1)).getYRadius(), 0.001);

    comp.addVertical(2.55);
    assertEquals(16, ((Rectangle) list.get(0)).getWidth(), 0.001);
    assertEquals(14.55, ((Rectangle) list.get(0)).getHeight(), 0.001);
    assertEquals(8, ((Oval) list.get(1)).getXRadius(), 0.001);
    assertEquals(6.55, ((Oval) list.get(1)).getYRadius(), 0.001);

    comp.stretchHorizontal(10.5);
    comp.stretchVertical(2.5);
    assertEquals(168, ((Rectangle) list.get(0)).getWidth(), 0.001);
    assertEquals(36.375, ((Rectangle) list.get(0)).getHeight(), 0.001);
    assertEquals(84, ((Oval) list.get(1)).getXRadius(), 0.001);
    assertEquals(16.375, ((Oval) list.get(1)).getYRadius(), 0.001);

    CompoundShape copy = (CompoundShape) comp.getCopy();
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
    CompoundShape c = new CompoundShape("C", 0, 0, new ArrayList<>());
    c.scaleError(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectColorFail() {
    Rectangle r = new Rectangle("R", 0, 0, 12, 12, new Color(0, 0, 0));
    r.setColor(null);
  }

  @Test(expected = IllegalStateException.class)
  public void testCompColorFail1() {
    CompoundShape c = new CompoundShape("C", 0, 0, new ArrayList<>());
    c.setColor(0, 0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testCompColorFail2() {
    CompoundShape c = new CompoundShape("C", 0, 0, new ArrayList<>());
    c.setColor(new Color(0, 0, 0));
  }
}