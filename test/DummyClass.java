import model.ObjectInterfaces.Drawable;
import model.attributes.Color;

/**
 * Dummy class for testing purposes.
 */
public class DummyClass implements Drawable {
  @Override
  public Drawable getCopy() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void setColor(int r, int g, int b) {

  }

  @Override
  public void setColor(Color c) {

  }
}
