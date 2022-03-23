import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.commands.AddShapeCmd;
import controller.commands.ICommand;
import controller.commands.MoveCmd;
import controller.commands.PlaceCmd;
import model.ObjectInterfaces.Drawable;
import model.PreBuiltAnimation;
import model.attributes.Color;
import model.attributes.Posn;
import model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JUnit test cases for our Animation implementation.
 */
public class AnimationTest {
  private List<Drawable> objects = new ArrayList<Drawable>();
  private List<ICommand> cmds = new ArrayList<ICommand>();

  @Before
  public void init() {
    objects = new ArrayList<Drawable>();
    Rectangle r1 = new Rectangle("R1", 0, 0, 30, 40, new Color(0, 100, 100));
    Rectangle r2 = new Rectangle("R2", 50, 100, 20, 20, new Color(0, 200, 0));
    Rectangle r3 = new Rectangle("R3", 100, 100, 90, 90, new Color(100, 100, 100));

    cmds = new ArrayList<ICommand>();
    cmds.add(new AddShapeCmd(r1, 1, objects));
    cmds.add(new AddShapeCmd(r2, 1, objects));
    cmds.add(new AddShapeCmd(r3, 2, objects));
    cmds.add(new PlaceCmd(r1, 3, new Posn(900, 900)));
    cmds.add(new PlaceCmd(r2, 3, new Posn(-10, -50)));
    cmds.add(new MoveCmd(r1, 4, 10, new Posn(0, 0)));
    cmds.add(new MoveCmd(r2, 5, 10, new Posn(555, 123)));
    cmds.add(new MoveCmd(r3, 4, 15, new Posn(45, 45)));
  }

  @Test
  public void testHashMap() {
    List<ICommand> tick1 = new ArrayList<ICommand>();
    tick1.add(cmds.get(0));
    tick1.add(cmds.get(1));

    List<ICommand> tick2 = new ArrayList<ICommand>();
    tick2.add(cmds.get(2));

    List<ICommand> tick3 = new ArrayList<ICommand>();
    tick3.add(cmds.get(3));
    tick3.add(cmds.get(4));

    List<ICommand> tick4 = new ArrayList<ICommand>();
    tick4.add(cmds.get(5));
    tick4.add(cmds.get(7));

    List<ICommand> tick5 = new ArrayList<ICommand>();
    tick5.add(cmds.get(6));
    tick5.add(cmds.get(5));
    tick5.add(cmds.get(7));

    List<ICommand> tick9 = new ArrayList<ICommand>();
    tick9.add(cmds.get(6));
    tick9.add(cmds.get(5));
    tick9.add(cmds.get(7));

    List<ICommand> tick14 = new ArrayList<ICommand>();
    tick14.add(cmds.get(7));

    PreBuiltAnimation animation = new PreBuiltAnimation(objects, cmds);
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
}
