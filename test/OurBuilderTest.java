import org.junit.Before;
import org.junit.Test;

import cs3500.animator.io.OurModelBuilder;
import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.model.IAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for our ModelBuilder class.
 * TODO add more tests
 */
public class OurBuilderTest {

  private TweenModelBuilder<IAnimation> builder;

  @Before
  public void init() {
    builder = new OurModelBuilder();
  }

  @Test
  public void testSetBounds() {
    IAnimation a = builder.setBounds(100, 100).build();

    assertEquals(100, a.getWindowWidth());
    assertEquals(100, a.getWindowHeight());

    builder.setBounds(15342, 34343);
    assertEquals(15342, a.getWindowWidth());
    assertEquals(34343, a.getWindowHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsError1() {
    IAnimation a = builder.setBounds(-10, 100).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsError2() {
    IAnimation a = builder.setBounds(0, -1).build();
  }

  @Test
  public void testAddShapes() {
    IAnimation a = builder.addOval("O", 12, 50,
            (float) 12.43, (float) 42.4, 1, 0, (float) 0.4, 1, 10)
            .addRectangle("R", 90, 50,
            (float) 1235.8, (float) 4999.99, 1, 1, 0, 4, 10).build();

    Oval o = new Oval("O", 12, 50, 12.43, 42.4, new Color(255, 0, 102));
    Rectangle r = new Rectangle("R", 90, 50, 1235.8, 4999.99, new Color(255, 255, 0));
    assertEquals(o, a.getDrawable("O"));
    assertEquals(r, a.getDrawable("R"));
  }
}
