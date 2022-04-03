package cs3500.animator.io;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.commands.AddOvalCmd;
import cs3500.animator.model.commands.AddRectCmd;
import cs3500.animator.model.commands.ChangeColorCmd;
import cs3500.animator.model.commands.MoveCmd;
import cs3500.animator.model.commands.RemoveDrawableCmd;
import cs3500.animator.model.commands.ResizeCmd;

/**
 * TODO
 */
public class OurModelBuilder implements TweenModelBuilder<IAnimation> {

  private final IAnimation animation;

  public OurModelBuilder() {
    animation = new SimpleAnimation();
  }

  @Override
  public TweenModelBuilder<IAnimation> setBounds(int width, int height) {
    checkDimensions(width, height);
    animation.setBounds(width, height);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimation> addOval(String name, float cx, float cy,
                                               float xRadius, float yRadius,
                                               float red, float green, float blue,
                                               int startOfLife, int endOfLife) {
    checkDimensions(xRadius, yRadius);
    checkColor(red, green, blue);
    checkTime(startOfLife, endOfLife);
    animation.addCmd(new AddOvalCmd(name, cx, cy, xRadius, yRadius,
            new Color(255 * red, 255 * green, 255 * blue), startOfLife));
    animation.addCmd(new RemoveDrawableCmd(name, endOfLife));
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimation> addRectangle(String name, float lx, float ly,
                                                    float width, float height,
                                                    float red, float green, float blue,
                                                    int startOfLife, int endOfLife) {
    checkDimensions(width, height);
    checkColor(red, green, blue);
    checkTime(startOfLife, endOfLife);
    animation.addCmd(new AddRectCmd(name, lx, ly, width, height,
            new Color(255 * red, 255 * green, 255 * blue), startOfLife));
    animation.addCmd(new RemoveDrawableCmd(name, endOfLife));
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimation> addMove(String name, float moveFromX, float moveFromY,
                                               float moveToX, float moveToY,
                                               int startTime, int endTime) {
    checkTime(startTime, endTime);
    animation.addCmd(new MoveCmd(name, startTime, endTime,
            new Posn(moveFromX, moveFromY), new Posn(moveToX, moveToY)));
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimation> addColorChange(String name,
                                                      float oldR, float oldG, float oldB,
                                                      float newR, float newG, float newB,
                                                      int startTime, int endTime) {
    checkTime(startTime, endTime);
    checkColor(oldR, oldG, oldB);
    checkColor(newR, newG, newB);
    animation.addCmd(new ChangeColorCmd(name, startTime, endTime,
            new Color(255 * oldR, 255 * oldG, 255 * oldB), new Color(255 * newR, 255 * newG, 255 * newB)));
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimation> addScaleToChange(String name, float fromSx, float fromSy,
                                                        float toSx, float toSy,
                                                        int startTime, int endTime) {
    checkDimensions(fromSx, fromSy);
    checkDimensions(toSx, toSy);
    checkTime(startTime, endTime);
    animation.addCmd(new ResizeCmd(name, startTime, endTime, fromSx, fromSy, toSx, toSy));
    return this;
  }

  @Override
  public IAnimation build() {
    animation.compile();
    return animation;
  }

  /**
   * Given two values representing the dimensions of some shape,
   * throw an error if either is invalid.
   *
   * @param width  the width of the shape
   * @param height the height of the shape
   * @throws IllegalArgumentException if either dimension is negative
   */
  private void checkDimensions(float width, float height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Error: dimensions cannot be negative");
    }
  }

  /**
   * Given two values representing the start and end times of some period of time,
   * throw an error if either is invalid.
   * Times are invalid if either is negative or if the end time is before the start time.
   *
   * @param startTime the start time
   * @param endTime   the end time
   * @throws IllegalArgumentException if either time is negative invalid
   */
  private void checkTime(int startTime, int endTime) {
    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Error: time cannot be negative");
    }
    if (endTime < startTime) {
      throw new IllegalArgumentException("Error: end time cannot be before start time");
    }
  }

  /**
   * Given the RGB values of some color, throw an error if any of the three is invalid.
   * Color values are invalid they are outside the range [0, 1].
   *
   * @param red   the red time
   * @param green the green time
   * @param blue  the blue value
   * @throws IllegalArgumentException if any color value is invalid
   */
  private void checkColor(float red, float green, float blue) {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Error: color values must be in the range [0, 1]");
    }
    if (red > 1 || green > 1 || blue > 1) {
      throw new IllegalArgumentException("Error: color values must be in the range [0, 1]");
    }
  }
}
