import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import controller.commands.AddShapeCmd;
import controller.commands.ICommand;
import controller.commands.MoveCmd;
import controller.commands.PlaceCmd;
import model.ObjectInterfaces.Drawable;
import model.attributes.Color;
import model.attributes.Posn;
import model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * JUnit test cases for the implemented commands.
 */
public class CommandTest {

  //---------------------------- Constructor Illegal Arg Exception ---------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testInitFailInstMove() {
    DummyClass dummy = new DummyClass();
    new PlaceCmd(dummy, 1, new Posn(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInitFailMove() {
    DummyClass dummy = new DummyClass();
    new MoveCmd(dummy, 1, 3, new Posn(0, 0));
  }

  //---------------------------- Test Functionality ------------------------------------------------

  @Test
  public void testAdd() {
    Rectangle rect1 = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    Rectangle rect2 = new Rectangle("R2", 3.33, 6.67, 87, 11, new Color(0, 0, 0));
    Rectangle rect3 = new Rectangle("R3", -10, 999, 50, 1, new Color(0, 0, 0));
    List<Drawable> list = new ArrayList<>();

    ICommand cmd1 = new AddShapeCmd(rect1, 1, list);
    ICommand cmd2 = new AddShapeCmd(rect2, 1, list);
    ICommand cmd3 = new AddShapeCmd(rect3, 4, list);

    assertEquals(1, cmd1.getStartTick());
    assertEquals(1, cmd1.getEndTick());
    assertEquals(1, cmd2.getStartTick());
    assertEquals(4, cmd3.getStartTick());

    assertEquals("Create Drawable named R1 at t=1", cmd1.logCmd());
    assertEquals("Create Drawable named R2 at t=1", cmd2.logCmd());
    assertEquals("Create Drawable named R3 at t=4", cmd3.logCmd());

    assertFalse(cmd1.isComplete());
    assertFalse(cmd2.isComplete());
    assertFalse(cmd3.isComplete());

    assertTrue(list.isEmpty());
    cmd1.execute();
    cmd2.execute();
    cmd3.execute();
    assertEquals(rect1, list.get(0));
    assertEquals(rect2, list.get(1));
    assertEquals(rect3, list.get(2));

    assertTrue(cmd1.isComplete());
    assertTrue(cmd2.isComplete());
    assertTrue(cmd3.isComplete());
  }

  @Test
  public void testPlaceMove() {
    Rectangle rect1 = new Rectangle("R1", 3.33, 6.67, 87, 11, new Color(0, 0, 0));
    Rectangle rect2 = new Rectangle("R2", -10, 99, 50, 1, new Color(0, 0, 0));
    Posn p = new Posn(-10, 99);

    ICommand cmd1 = new MoveCmd(rect2, 1, 12, new Posn(1, 55));
    ICommand cmd2 = new MoveCmd(rect1, 1, 1000, new Posn(10943.1343, 32142.765));
    ICommand cmd3 = new PlaceCmd(rect2, 1, new Posn(0, 3.33));
    ICommand cmd4 = new PlaceCmd(rect1, 1, new Posn(-1.75, -9));

    assertFalse(cmd1.isComplete() || cmd1.isRunning());
    assertFalse(cmd2.isComplete() || cmd2.isRunning());
    assertFalse(cmd3.isComplete() || cmd3.isRunning());
    assertFalse(cmd4.isComplete() || cmd4.isRunning());

    for (int i = 1; i < 11; i++) {
      cmd1.execute();
      p.move(1, -4);
      assertTrue(cmd1.isRunning());
      assertFalse(cmd1.isComplete());
      assertEquals(p, rect2.getPos());
    }
    cmd1.execute();
    assertEquals(new Posn(1, 55), rect2.getPos());
    assertTrue(cmd1.isComplete() && !cmd1.isRunning());

    cmd3.execute();
    assertEquals(new Posn(0, 3.33), rect2.getPos());
    assertTrue(cmd3.isComplete() && !cmd3.isRunning());

    for (int i = 1; i < 999; i++) {
      cmd2.execute();
      assertTrue(cmd2.isRunning());
      assertFalse(cmd2.isComplete());
    }
    cmd2.execute();
    assertEquals(new Posn(10943.1343, 32142.765), rect1.getPos());
    assertTrue(cmd2.isComplete() && !cmd2.isRunning());

    cmd4.execute();
    assertEquals(new Posn(-1.75, -9), rect1.getPos());
    assertTrue(cmd4.isComplete() && !cmd4.isRunning());

    assertEquals("R2 moves from : ( -10.0, 99.0 ) to ( 1.0, 55.0 ) from t=1 to t=12",
            cmd1.logCmd());
    assertEquals("R1 moves from : ( 3.33, 6.67 ) to ( 10943.1343, 32142.765 ) " +
            "from t=1 to t=1000", cmd2.logCmd());
    assertEquals("R2 moves from : ( 1.0, 55.0 ) to ( 0.0, 3.33 ) at t=1", cmd3.logCmd());
    assertEquals("R1 moves from : ( 10943.134300000109, 32142.764999999396 ) " +
            "to ( -1.75, -9.0 ) at t=1", cmd4.logCmd());
  }

  //---------------------------- Execute Illegal State Exception -----------------------------------

  @Test(expected = IllegalStateException.class)
  public void testExecFail1() {
    Rectangle rect = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    List<Drawable> list = new ArrayList<>();
    ICommand cmd = new AddShapeCmd(rect, 1, list);
    cmd.execute();
    cmd.execute();
  }

  @Test(expected = IllegalStateException.class)
  public void testExecFail2() {
    Rectangle rect = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    ICommand cmd = new MoveCmd(rect, 1, 12, new Posn(1, 55));

    for (int i = 0; i < 12; i++) {
      cmd.execute();
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testLogFail1() {
    Rectangle rect = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    ICommand cmd = new PlaceCmd(rect, 1, new Posn(0, 3.33));
    cmd.logCmd();
  }

  @Test(expected = IllegalStateException.class)
  public void testLogFail2() {
    Rectangle rect = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    ICommand cmd = new MoveCmd(rect, 1, 12, new Posn(1, 55));
    cmd.logCmd();
  }
}
