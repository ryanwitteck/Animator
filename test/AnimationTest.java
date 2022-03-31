import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.commands.AddShapeCmd;
import model.commands.ICommand;
import model.commands.MoveCmd;
import model.commands.PlaceCmd;
import model.IFrame;
import model.interfaces.Drawable;
import model.SimpleAnimation;
import model.attributes.Color;
import model.attributes.Posn;
import model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test cases for our Animation implementation.
 */
public class AnimationTest {
  private List<ICommand> cmds;
  private SimpleAnimation animation;

  //---------------------------- Test Functionality ------------------------------------------------

  @Before
  public void init() {
    List<Drawable> objects = new ArrayList<>();
    Rectangle r1 = new Rectangle("R1", 0, 0, 30, 40, new Color(0, 100, 100));
    Rectangle r2 = new Rectangle("R2", 50, 100, 20, 20, new Color(0, 200, 0));
    Rectangle r3 = new Rectangle("R3", 100, 100, 90, 90, new Color(100, 100, 100));

    cmds = new ArrayList<>();
    cmds.add(new AddShapeCmd(r1, 1, objects));
    cmds.add(new AddShapeCmd(r2, 1, objects));
    cmds.add(new AddShapeCmd(r3, 2, objects));
    cmds.add(new PlaceCmd(r1, 3, new Posn(900, 900)));
    cmds.add(new PlaceCmd(r2, 3, new Posn(-10, -50)));
    cmds.add(new MoveCmd(r1, 4, 10, new Posn(0, 0)));
    cmds.add(new MoveCmd(r2, 5, 10, new Posn(555, 123)));
    cmds.add(new MoveCmd(r3, 4, 15, new Posn(45, 45)));

    animation = new SimpleAnimation(objects, cmds);
  }

  @Test
  public void testHashMap() {
    List<ICommand> tick1 = new ArrayList<>();
    tick1.add(cmds.get(0));
    tick1.add(cmds.get(1));

    List<ICommand> tick2 = new ArrayList<>();
    tick2.add(cmds.get(2));

    List<ICommand> tick3 = new ArrayList<>();
    tick3.add(cmds.get(3));
    tick3.add(cmds.get(4));

    List<ICommand> tick4 = new ArrayList<>();
    tick4.add(cmds.get(5));
    tick4.add(cmds.get(7));

    List<ICommand> tick5 = new ArrayList<>();
    tick5.add(cmds.get(6));
    tick5.add(cmds.get(5));
    tick5.add(cmds.get(7));

    List<ICommand> tick9 = new ArrayList<>();
    tick9.add(cmds.get(6));
    tick9.add(cmds.get(5));
    tick9.add(cmds.get(7));

    List<ICommand> tick14 = new ArrayList<>();
    tick14.add(cmds.get(7));

    HashMap<Integer, List<ICommand>> map = animation.getCmdMap();

    assertEquals(tick1, map.get(1));
    assertEquals(tick2, map.get(2));
    assertEquals(tick3, map.get(3));
    assertEquals(tick4, map.get(4));
    assertEquals(tick5, map.get(5));
    assertEquals(tick9, map.get(9));
    assertEquals(tick14, map.get(14));
    assertNull(map.get(15));
  }

  @Test
  public void testLog() {
    for (ICommand cmd : cmds) {
      assertTrue(cmd.isComplete());
    }

    StringBuilder expected = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      expected.append(cmds.get(i).logCmd()).append("\n");
    }
    expected.append(cmds.get(7).logCmd()).append("\n");
    expected.append(cmds.get(6).logCmd()).append("\n");

    assertEquals(expected.toString(), animation.getCmdLog());
  }

  @Test
  public void testFrames() {
    List<IFrame> frames = animation.getFrames();

    for (int i = 0; i < frames.size(); i++) {
      assertEquals(frames.get(i).listObjects(), animation.getFrame(i).listObjects());
    }

    Rectangle r1 = new Rectangle("R1", 0, 0, 30, 40, new Color(0, 100, 100));
    Rectangle r2 = new Rectangle("R2", 50, 100, 20, 20, new Color(0, 200, 0));
    Rectangle r3 = new Rectangle("R3", 100, 100, 90, 90, new Color(100, 100, 100));
    assertTrue(frames.get(0).listObjects().isEmpty());
    assertEquals(r1, frames.get(1).listObjects().get(0));
    assertEquals(r2, frames.get(1).listObjects().get(1));

    assertEquals(r1, frames.get(2).listObjects().get(0));
    assertEquals(r2, frames.get(2).listObjects().get(1));
    assertEquals(r3, frames.get(2).listObjects().get(2));

    r1.place(900, 900);
    r2.place(-10, -50);
    assertEquals(r1, frames.get(3).listObjects().get(0));
    assertEquals(r2, frames.get(3).listObjects().get(1));
    assertEquals(r3, frames.get(3).listObjects().get(2));

    r1.place(0, 0);
    r2.place(555, 123);
    r3.place(70, 70);
    assertEquals(r1, frames.get(9).listObjects().get(0));
    assertEquals(r2, frames.get(9).listObjects().get(1));
    assertEquals(r3, frames.get(9).listObjects().get(2));

    r3.place(45, 45);
    assertEquals(r1, frames.get(14).listObjects().get(0));
    assertEquals(r2, frames.get(14).listObjects().get(1));
    assertEquals(r3, frames.get(14).listObjects().get(2));
  }

  //---------------------------- Test Exceptions ---------------------------------------------------

  @Test(expected = IllegalStateException.class)
  public void testExecuteFail() {
    animation.addCmd(cmds.get(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFail1() {
    animation.getFrame(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFail2() {
    animation.getFrame(animation.getFrames().size());
  }
}
