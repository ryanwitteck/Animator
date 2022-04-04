import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.commands.AddOvalCmd;
import cs3500.animator.model.commands.AddRectCmd;
import cs3500.animator.model.commands.ICommand;
import cs3500.animator.model.commands.MoveCmd;
import cs3500.animator.model.commands.PlaceCmd;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SvgView;

import static org.junit.Assert.assertEquals;

public class SvgViewTest {

  @Test
  public void testEmptyAnimation() throws IOException {
    StringBuilder builder = new StringBuilder();
    IAnimation a = new SimpleAnimation();
    a.compile();
    AnimationView view = new SvgView(a, builder, 1);
    view.renderAnimation();
    String expected = "<svg width=\"500\" height=\"500\" "
            + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n</svg>";
    assertEquals(expected, builder.toString());
  }

  @Test
  public void testAddShapes() throws IOException {
    StringBuilder builder = new StringBuilder();
    IAnimation a = new SimpleAnimation();
    a.addCmd(new AddRectCmd("R1", 0, 0, 40, 30, new Color(100, 150, 50), 0));
    a.addCmd(new AddRectCmd("R2", 50, 50, 30, 40, new Color(10, 255, 100), 1));
    a.addCmd(new AddOvalCmd("O1", 100, 100, 70, 70, new Color(90, 10, 70), 2));
    a.addCmd(new AddOvalCmd("O2", 200, 50, 40, 80, new Color(230, 230, 230), 6));
    a.compile();
    AnimationView view = new SvgView(a, builder, 1);
    view.renderAnimation();

    FileReader reader = new FileReader("test/SvgAddShapesExpected.txt");
    char[] cb = new char[517];
    reader.read(cb);
    String expected = String.copyValueOf(cb);
    assertEquals(expected.replace("\r", ""), builder.toString());
  }

  @Test
  public void testBasicAnimation() throws IOException {
    StringBuilder builder = new StringBuilder();
    IAnimation a = new SimpleAnimation();
    a.addCmd(new AddRectCmd("R", 100, 100, 40, 30, new Color(100, 150, 50), 0));
    a.addCmd(new AddOvalCmd("O", 100, 100, 40, 40, new Color(90, 10, 70), 2));
    a.addCmd(new MoveCmd("R", 1, 10, new Posn(100, 100), new Posn(0, 0)));
    a.addCmd(new MoveCmd("O", 5, 15, new Posn(100, 100), new Posn(200, 200)));
    a.compile();
    AnimationView view = new SvgView(a, builder, 3);
    view.renderAnimation();

    FileReader reader = new FileReader("test/BasicSvgExpected.txt");
    char[] cb = new char[750];
    reader.read(cb);
    String expected = String.copyValueOf(cb);
    assertEquals(expected.replace("\r", ""), builder.toString());
  }
}
