import org.junit.Before;
import org.junit.Test;

import cs3500.animator.io.OurModelBuilder;
import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.model.IAnimation;

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
    IAnimation a1 = builder.setBounds(100, 100).build();
    IAnimation a2 = builder.setBounds(15342, 34343).build();

    (a.getWindowHeight());


  }

  @Test


  @Test


  @Test


}
