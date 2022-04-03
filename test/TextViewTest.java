import org.junit.Test;

import java.io.IOException;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for our TextView implementation.
 * TODO add more tests
 */
public class TextViewTest {

  @Test
  public void testView() {
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
}
