import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Frame;
import model.interfaces.Drawable;
import model.interfaces.Movable;
import model.attributes.Color;
import model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * JUnit test cases for our Frame implementation.
 */
public class FrameTest {
  @Test
  public void testFrame() {
    List<Drawable> objects = new ArrayList<>();
    objects.add(new Rectangle("R1", 0, 0, 100, 40, new Color(0, 100, 100)));
    objects.add(new Rectangle("R2", 53, 214, 500, 1000, new Color(0, 200, 0)));
    objects.add(new Rectangle("R3", 1244, 876, 123, 987, new Color(100, 100, 100)));

    Frame f = new Frame(objects);
    List<Drawable> list = f.listObjects();
    assertEquals(objects, list);
    ((Movable) objects.get(0)).move(0, 1);
    assertNotEquals(objects, list);
  }
}
