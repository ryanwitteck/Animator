import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.commands.AddRectCmd;
import cs3500.animator.model.commands.ICommand;
import cs3500.animator.model.commands.MoveCmd;
import cs3500.animator.model.commands.PlaceCmd;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for our TextView implementation.
 */
public class TextViewTest {

  @Test
  public void testViewEmptyAnimation() {
    StringBuilder builder = new StringBuilder();
    IAnimation animation = new SimpleAnimation();
    TextView view = new TextView(animation, builder, 1);

    try {
      view.renderAnimation();
      assertEquals(animation.getCmdLog(), builder.toString());
      assertEquals("Window: 500 by 500\n", builder.toString());
    } catch (IOException e) {
      System.out.println("Could not read data destination");
    }
  }

  @Test
  public void testComplexAnimation() {
    StringBuilder builder = new StringBuilder();
    IAnimation animation = new SimpleAnimation();
    ArrayList<ICommand> cmds = new ArrayList<>();
    cmds.add(new AddRectCmd("R1", 0, 0, 30, 40, new Color(0, 100, 100), 1));
    cmds.add(new AddRectCmd("R2", 50, 100, 20, 20, new Color(0, 200, 0), 1));
    cmds.add(new AddRectCmd("R3", 100, 100, 90, 90, new Color(100, 100, 100), 2));
    cmds.add(new PlaceCmd("R1", 3, new Posn(900, 900)));
    cmds.add(new PlaceCmd("R2", 3, new Posn(-10, -50)));
    cmds.add(new MoveCmd("R1", 4, 10, new Posn(900, 900), new Posn(0, 0)));
    cmds.add(new MoveCmd("R2", 5, 10, new Posn(-10, -50), new Posn(555, 123)));
    cmds.add(new MoveCmd("R3", 4, 15, new Posn(100, 100), new Posn(45, 45)));

    for (ICommand cmd : cmds) {
      animation.addCmd(cmd);
    }
    animation.compile();
    TextView view = new TextView(animation, builder, 100);

    try {
      view.renderAnimation();

      String expected = "Window: 500 by 500\n"
              + "Created Rectangle name = R1 posn = (0.0,0.0) width = 30.0 height = 10.0ms\n"
              + "Created Rectangle name = R2 posn = (50.0,100.0) width = 20.0 height = 10.0ms\n"
              + "Created Rectangle name = R3 posn = (100.0,100.0) width = 90.0 height = 20.0ms\n"
              + "R1 moves from : (0.0,0.0) to (900.0,900.0) at t = 30.0ms\n"
              + "R2 moves from : (50.0,100.0) to (-10.0,-50.0) at t = 30.0ms\n"
              + "R1 moves from : (900.0,900.0) to (0.0,0.0) from t = 40.0ms to t = 100.0ms\n"
              + "R3 moves from : (100.0,100.0) to (45.0,45.0) from t = 40.0ms to t = 0.15s\n"
              + "R2 moves from : (-10.0,-50.0) to (555.0,123.0) from t = 50.0ms to t = 100.0ms\n";

      assertEquals(expected, builder.toString());
    } catch (IOException e) {
      System.out.println("Could not read data destination");
    }
  }

  @Test
  public void testWriteToFile() throws IOException {
    FileWriter writer = new FileWriter("test/TextViewFileTest");
    IAnimation animation = new SimpleAnimation();
    ArrayList<ICommand> cmds = new ArrayList<>();
    cmds.add(new AddRectCmd("R1", 0, 0, 30, 40, new Color(0, 100, 100), 1));
    cmds.add(new AddRectCmd("R2", 50, 100, 20, 20, new Color(0, 200, 0), 1));
    cmds.add(new AddRectCmd("R3", 100, 100, 90, 90, new Color(100, 100, 100), 2));
    cmds.add(new PlaceCmd("R1", 3, new Posn(900, 900)));
    cmds.add(new PlaceCmd("R2", 3, new Posn(-10, -50)));
    cmds.add(new MoveCmd("R1", 4, 10, new Posn(900, 900), new Posn(0, 0)));
    cmds.add(new MoveCmd("R2", 5, 10, new Posn(-10, -50), new Posn(555, 123)));
    cmds.add(new MoveCmd("R3", 4, 15, new Posn(100, 100), new Posn(45, 45)));

    for (ICommand cmd : cmds) {
      animation.addCmd(cmd);
    }
    animation.compile();
    TextView view = new TextView(animation, writer, 2);

    view.renderAnimation();
    FileReader reader = new FileReader("test/TextViewFileTest");
    char[] cb = new char[568];
    reader.read(cb);
    String s = String.copyValueOf(cb);

    String expected = "Window: 500 by 500\n"
            + "Created Rectangle name = R1 posn = (0.0,0.0) width = 30.0 height = 0.5s\n"
            + "Created Rectangle name = R2 posn = (50.0,100.0) width = 20.0 height = 0.5s\n"
            + "Created Rectangle name = R3 posn = (100.0,100.0) width = 90.0 height = 1.0s\n"
            + "R1 moves from : (0.0,0.0) to (900.0,900.0) at t = 1.5s\n"
            + "R2 moves from : (50.0,100.0) to (-10.0,-50.0) at t = 1.5s\n"
            + "R1 moves from : (900.0,900.0) to (0.0,0.0) from t = 2.0s to t = 5.0s\n"
            + "R3 moves from : (100.0,100.0) to (45.0,45.0) from t = 2.0s to t = 7.5s\n"
            + "R2 moves from : (-10.0,-50.0) to (555.0,123.0) from t = 2.5s to t = 5.0s\n";

    assertEquals(expected, s);
  }
}
