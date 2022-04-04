package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.commands.ICommand;

/**
 * This class represents a visualization of an IAnimation as an SVG file. TODO implement.
 */
public class SvgView implements AnimationView {

  private IAnimation animation;
  private Appendable ap;
  private HashMap<String, List<ICommand>> objCmdMap;
  private String svgString;

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
    initObjCmdMap();
    initSvgString();
  }

  private void initObjCmdMap() {
    HashMap<Integer, List<ICommand>> tickCmdMap = animation.getCmdMap();
    objCmdMap = new HashMap<>();

    for (int i = 0; i < animation.getNFrames(); i++) {
      for (ICommand cmd : tickCmdMap.get(i)) {
        String name = cmd.getTarget();
        if (objCmdMap.containsKey(name)) {
          objCmdMap.get(name).add(cmd);
        } else {
          objCmdMap.put(name, new ArrayList<>());
          objCmdMap.get(name).add(cmd);
        }
      }
    }
  }

  private void initSvgString() {
    StringBuilder svgBuilder = new StringBuilder();
    svgBuilder.append("<svg width=\"" + animation.getWindowWidth()
            + "\" height=\"" + animation.getWindowHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");


    svgString = svgBuilder.toString();
  }

  @Override
  public void renderAnimation() throws IOException {
    // TODO implement
    // These calls are just meant to save our style points
    animation.getCmdLog();
    ap.append("");
    objCmdMap.get("");
  }
}
