package cs3500.animator.io;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.SimpleAnimation;

/**
 * TODO
 */
public class OurModelBuilder implements TweenModelBuilder<IAnimation> {

  private IAnimation animation;

  public OurModelBuilder() {
    animation = new SimpleAnimation();
  }

  @Override
  public TweenModelBuilder<IAnimation> setBounds(int width, int height) {
    return null;
  }

  @Override
  public TweenModelBuilder<IAnimation> addOval(String name, float cx, float cy, float xRadius, float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
    return null;
  }

  @Override
  public TweenModelBuilder<IAnimation> addRectangle(String name, float lx, float ly, float width, float height, float red, float green, float blue, int startOfLife, int endOfLife) {
    return null;
  }

  @Override
  public TweenModelBuilder<IAnimation> addMove(String name, float moveFromX, float moveFromY, float moveToX, float moveToY, int startTime, int endTime) {
    return null;
  }

  @Override
  public TweenModelBuilder<IAnimation> addColorChange(String name, float oldR, float oldG, float oldB, float newR, float newG, float newB, int startTime, int endTime) {
    return null;
  }

  @Override
  public TweenModelBuilder<IAnimation> addScaleToChange(String name, float fromSx, float fromSy, float toSx, float toSy, int startTime, int endTime) {
    return null;
  }

  @Override
  public IAnimation build() {
    return null;
  }
}
