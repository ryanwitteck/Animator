package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.commands.AddOvalCmd;
import cs3500.animator.model.commands.AddRectCmd;
import cs3500.animator.model.commands.ChangeColorCmd;
import cs3500.animator.model.commands.ICommand;
import cs3500.animator.model.commands.MoveCmd;
import cs3500.animator.model.commands.RemoveDrawableCmd;
import cs3500.animator.model.commands.ResizeCmd;
import cs3500.animator.model.shapes.Rectangle;

/**
 * This class represents a visualization of an IAnimation as an SVG file. TODO implement.
 */
public class SvgView implements AnimationView {

  private IAnimation animation;
  private Appendable ap;
  private int fps;
  private List<String> objNames;
  private HashMap<String, List<ICommand>> objCmdMap;
  private String svgString;

  /**
   * Sole constructor of SvgView. Initialize fields animation, ap, and fps to the given arguments
   * and use the given model to initialize objNames, objCmdMap, and svgString.
   * This view will not print to the given destination until renderAnimation is called.
   *
   * @param animation the animation being converted to SVG format.
   * @param ap        the destination that this view will be printed to.
   * @param fps       the framerate of this animation.
   */
  public SvgView(IAnimation animation, Appendable ap, int fps) {
    this.animation = animation;
    this.ap = ap;
    this.fps = fps;
    initMapAndList();
    initSvgString();
  }

  private void initMapAndList() {
    HashMap<Integer, List<ICommand>> tickCmdMap = animation.getCmdMap();
    objCmdMap = new HashMap<>();
    objNames = new ArrayList<>();

    for (int i = 0; i < animation.getNFrames(); i++) {
      for (ICommand cmd : tickCmdMap.get(i)) {
        String name = cmd.getTarget();
        if (objCmdMap.containsKey(name)) {
          objCmdMap.get(name).add(cmd);
        } else {
          objNames.add(name);
          objCmdMap.put(name, new ArrayList<>());
          objCmdMap.get(name).add(cmd);
        }
      }
    }
  }

  private void initSvgString() {
    StringBuilder svgBuilder = new StringBuilder();
    svgBuilder.append("<svg width=\"").append(animation.getWindowWidth())
            .append("\" height=\"").append(animation.getWindowHeight())
            .append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (String name : objNames) {
      for (ICommand cmd : objCmdMap.get(name)) {
        svgBuilder.append(cmd.toSvg(fps));
      }
    }
    svgBuilder.append("/svg");

    svgString = svgBuilder.toString();
  }

  private String cmdToSvg(ICommand cmd) {
    return "<animate attributeType=\"xml\" "
            + "attributeName=\"" + xattr + "\" "
            + "begin=\"" + startTick * 1000 / fps + "ms\" end=\"" + endTick * 1000 / fps + "ms\" "
            + "from=\"" + fromX + "\" to=\"" + dest.getX() + "\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" "
            + "begin=\"" + startTick * 1000 / fps + "ms\" end=\"" + endTick * 1000 / fps + "ms\" "
            + "attributeName=\"" + yattr + "\" "
            + "from=\"" + startPos.getY() + "\" to=\"" + dest.getY() + "\" fill=\"freeze\" />\n";
  }

  @Override
  public void renderAnimation() throws IOException {
    if (ap instanceof FileWriter) {
      FileWriter writer = (FileWriter) ap;
      writer.write(svgString);
      writer.close();
    } else {
      ap.append(svgString);
    }
  }
}
