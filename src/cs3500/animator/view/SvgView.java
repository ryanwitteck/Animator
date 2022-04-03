package cs3500.animator.view;

import java.io.IOException;
import java.util.HashMap;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.commands.ICommand;

/**
 * TODO
 */
public class SvgView implements AnimationView {

  private IAnimation animation;
  private Appendable ap;
  private HashMap<String, ICommand> objCmdMap;

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
