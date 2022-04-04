package cs3500.animator.view;

import java.io.IOException;
import java.util.HashMap;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.commands.ICommand;

/**
 * This class represents a visualization of an IAnimation as an SVG file. TODO implement.
 */
public class SvgView implements AnimationView {

  private IAnimation animation;
  private Appendable ap;
  private HashMap<String, ICommand> objCmdMap;

  /**
   * Sole constructor of SvgView. Initialize fields animation and ap to the given arguments and
   * use the given model to initialize objCmdMap.
   * This view will not print to the given destination until renderAnimation is called.
   *
   * @param animation the animation being converted to SVG format.
   * @param ap        the destination that this view will be printed to.
   */
  public SvgView(IAnimation animation, Appendable ap) {
    this.animation = animation;
    this.ap = ap;
  }

  @Override
  public void renderAnimation() throws IOException {

  }
}
