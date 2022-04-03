package cs3500.animator.view;

import java.io.IOException;

import cs3500.animator.model.IAnimation;

/**
 * TODO
 */
public class SvgView implements AnimationView {



  private IAnimation animation;
  private Appendable ap;

  /**
   * TODO
   *
   * @param animation the animation being converted to SVG format.
   * @param ap        the visualization of this view.
   */
  public SvgView(IAnimation animation, Appendable ap) {
    this.animation = animation;
    this.ap = ap;
  }

  @Override
  public void renderAnimation() throws IOException {

  }
}
