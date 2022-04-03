package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.IAnimation;

/**
 * This interface represents a text representation of an animation. --- TODO make better
 */
public class TextView implements AnimationView {

  private IAnimation animation;
  private Appendable ap;

  /**
   * Constructor --- TODO make better
   *
   * @param animation the animation that this view will visualize
   * @param ap        the visualization of the animation
   */
  public TextView(IAnimation animation, Appendable ap) {
    this.animation = animation;
    this.ap = ap;
  }

  @Override
  public void renderAnimation() throws IOException {
    if (ap instanceof FileWriter) {
      FileWriter writer = (FileWriter) ap;
      writer.write(animation.getCmdLog());
      writer.close();
    }
    else {
      ap.append(animation.getCmdLog());
    }
  }
}
