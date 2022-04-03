import org.junit.Test;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.commands.AddRectCmd;
import cs3500.animator.model.commands.ICommand;
import cs3500.animator.model.commands.MoveCmd;
import cs3500.animator.model.commands.PlaceCmd;
import cs3500.animator.model.commands.RemoveDrawableCmd;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.interfaces.Movable;
import cs3500.animator.model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * JUnit test cases for the implemented commands.
 */
public class CommandTest {

  //---------------------------- Test Functionality ------------------------------------------------

  @Test
  public void testAddRemove() {
    IAnimation animation = new SimpleAnimation();
    Rectangle rect1 = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    Rectangle rect2 = new Rectangle("R2", 3.33, 6.67, 87, 11, new Color(0, 0, 0));
    Rectangle rect3 = new Rectangle("R3", -10, 999, 50, 1, new Color(0, 0, 0));

    ICommand cmd1 = new AddRectCmd("R1", 0, 0, 10, 5, new Color(0, 0, 0), 1);
    ICommand cmd2 = new AddRectCmd("R2", 3.33, 6.67, 87, 11, new Color(0, 0, 0), 1);
    ICommand cmd3 = new AddRectCmd("R3", -10, 999, 50, 1, new Color(0, 0, 0), 4);

    assertEquals(1, cmd1.getStartTick());
    assertEquals(1, cmd1.getEndTick());
    assertEquals(1, cmd2.getStartTick());
    assertEquals(4, cmd3.getStartTick());

    assertEquals("Created Rectangle named R1 at t=1", cmd1.logCmd());
    assertEquals("Created Rectangle named R2 at t=1", cmd2.logCmd());
    assertEquals("Created Rectangle named R3 at t=4", cmd3.logCmd());

    assertFalse(cmd1.isComplete());
    assertFalse(cmd2.isComplete());
    assertFalse(cmd3.isComplete());

    cmd1.execute(animation);
    cmd2.execute(animation);
    cmd3.execute(animation);
    assertEquals(rect1, animation.getDrawable("R1"));
    assertEquals(rect2, animation.getDrawable("R2"));
    assertEquals(rect3, animation.getDrawable("R3"));

    assertTrue(cmd1.isComplete());
    assertTrue(cmd2.isComplete());
    assertTrue(cmd3.isComplete());

    ICommand rcmd1 = new RemoveDrawableCmd("R1", 1);
    ICommand rcmd2 = new RemoveDrawableCmd("R2", 1);
    ICommand rcmd3 = new RemoveDrawableCmd("R3", 4);

    rcmd1.execute(animation);
    rcmd2.execute(animation);
    rcmd3.execute(animation);
    assertNull(animation.getDrawable("R1"));
    assertNull(animation.getDrawable("R2"));
    assertNull(animation.getDrawable("R3"));
  }

  @Test
  public void testPlaceMove() {
    IAnimation a = new SimpleAnimation();
    Rectangle rect1 = new Rectangle("R1", 3.33, 6.67, 87, 11, new Color(0, 0, 0));
    Rectangle rect2 = new Rectangle("R2", -10, 99, 50, 1, new Color(0, 0, 0));
    a.addDrawable(rect1);
    a.addDrawable(rect2);

    Posn p = new Posn(-10, 99);

    ICommand cmd1 = new MoveCmd("R2", 1, 12, new Posn(-10, 99), new Posn(1, 55));
    ICommand cmd2 = new MoveCmd("R1", 1, 1000,
            new Posn(3.33, 6.67), new Posn(10943.1343, 32142.765));
    ICommand cmd3 = new PlaceCmd("R2", 1, new Posn(0, 3.33));
    ICommand cmd4 = new PlaceCmd("R1", 1, new Posn(-1.75, -9));

    assertFalse(cmd1.isComplete() || cmd1.isRunning());
    assertFalse(cmd2.isComplete() || cmd2.isRunning());
    assertFalse(cmd3.isComplete() || cmd3.isRunning());
    assertFalse(cmd4.isComplete() || cmd4.isRunning());

    for (int i = 1; i < 11; i++) {
      cmd1.execute(a);
      p.move(1, -4);
      assertTrue(cmd1.isRunning());
      assertFalse(cmd1.isComplete());
      assertEquals(p, ((Movable) a.getDrawable("R2")).getPos());
    }
    cmd1.execute(a);
    assertEquals(new Posn(1, 55), ((Movable) a.getDrawable("R2")).getPos());
    assertTrue(cmd1.isComplete() && !cmd1.isRunning());

    cmd3.execute(a);
    assertEquals(new Posn(0, 3.33), ((Movable) a.getDrawable("R2")).getPos());
    assertTrue(cmd3.isComplete() && !cmd3.isRunning());

    for (int i = 1; i < 999; i++) {
      cmd2.execute(a);
      assertTrue(cmd2.isRunning());
      assertFalse(cmd2.isComplete());
    }
    cmd2.execute(a);
    assertEquals(new Posn(10943.1343, 32142.765), ((Movable) a.getDrawable("R1")).getPos());
    assertTrue(cmd2.isComplete() && !cmd2.isRunning());

    cmd4.execute(a);
    assertEquals(new Posn(-1.75, -9), ((Movable) a.getDrawable("R1")).getPos());
    assertTrue(cmd4.isComplete() && !cmd4.isRunning());

    assertEquals("R2 moves from : ( -10.0, 99.0 ) to ( 1.0, 55.0 ) from t=1 to t=12",
            cmd1.logCmd());
    assertEquals("R1 moves from : ( 3.33, 6.67 ) to ( 10943.1343, 32142.765 ) " +
            "from t=1 to t=1000", cmd2.logCmd());
    assertEquals("R2 moves from : ( 1.0, 55.0 ) to ( 0.0, 3.33 ) at t=1", cmd3.logCmd());
    assertEquals("R1 moves from : ( 10943.134300000109, 32142.764999999396 ) " +
            "to ( -1.75, -9.0 ) at t=1", cmd4.logCmd());

    a.removeDrawable("R2");
    Rectangle rect3 = new Rectangle("R2", -10, 99, 50, 1, new Color(0, 0, 0));
    a.addDrawable(rect3);
    cmd1.reset();
    assertFalse(cmd1.isComplete() || cmd1.isRunning());
    p.set(-10, 99);

    for (int i = 1; i < 11; i++) {
      cmd1.execute(a);
      p.move(1, -4);
      assertTrue(cmd1.isRunning());
      assertFalse(cmd1.isComplete());
      assertEquals(p, ((Movable) a.getDrawable("R2")).getPos());
    }
    cmd1.execute(a);
    assertEquals(new Posn(1, 55), ((Movable) a.getDrawable("R2")).getPos());
    assertTrue(cmd1.isComplete() && !cmd1.isRunning());
  }

  //---------------------------- Execute Illegal State Exception -----------------------------------

  @Test(expected = IllegalStateException.class)
  public void testExecFail1() {
    IAnimation a = new SimpleAnimation();
    ICommand cmd = new AddRectCmd("R1", 0, 0, 10, 5, new Color(0, 0, 0), 1);
    cmd.execute(a);
    cmd.execute(a);
  }

  @Test(expected = IllegalStateException.class)
  public void testExecFail2() {
    IAnimation a = new SimpleAnimation();
    Rectangle rect = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    a.addDrawable(rect);
    ICommand cmd = new MoveCmd("R1", 1, 12, new Posn(0, 0), new Posn(1, 55));

    for (int i = 0; i < 12; i++) {
      cmd.execute(a);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testExecFail3() {
    IAnimation a = new SimpleAnimation();
    Rectangle rect = new Rectangle("R1", 0, 0, 10, 5, new Color(0, 0, 0));
    a.addDrawable(rect);
    ICommand cmd = new MoveCmd("R1", 1, 12, new Posn(0, 0), new Posn(1, 55));
    ICommand cmd2 = new MoveCmd("R1", 0, 1, new Posn(0, 0), new Posn(1, 1));

    cmd2.execute(a);
    cmd.execute(a);
  }

  @Test(expected = IllegalStateException.class)
  public void testLogFail1() {
    ICommand cmd = new PlaceCmd("R1", 1, new Posn(0, 3.33));
    cmd.logCmd();
  }

  @Test(expected = IllegalStateException.class)
  public void testLogFail2() {
    ICommand cmd = new MoveCmd("R1", 1, 12, new Posn(1, 55), new Posn(1, 55));
    cmd.logCmd();
  }
}
