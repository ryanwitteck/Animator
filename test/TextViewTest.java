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
 * TODO add more tests
 */
public class TextViewTest {

  @Test
  public void testViewEmptyAnimation() {
    StringBuilder builder = new StringBuilder();
    IAnimation animation = new SimpleAnimation();
    TextView view = new TextView(animation, builder);

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
    TextView view = new TextView(animation, builder);

    try {
      view.renderAnimation();
      assertEquals(animation.getCmdLog(), builder.toString());
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
    TextView view = new TextView(animation, writer);

    view.renderAnimation();
    FileReader reader = new FileReader("test/TextViewFileTest");
    char cb[] = new char[639];
    reader.read(cb);
    String s = String.copyValueOf(cb);
    assertEquals(animation.getCmdLog(), s);
  }
}
