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

/**
 * This class represents a text visualization of an IAnimation as in the SVG file format. This class
 * creates the svg view by reading and parsing the command logs of the ICommands that direct the
 * behavior of the IAnimation model being visualized. As such, if we decide to implement more
 * ICommands in the future, we will have to add more methods to this View implementation.
 */
public class SvgView implements AnimationView {

  private final IAnimation animation;
  private final Appendable ap;
  private final int fps;
  private List<String> objNames;
  private HashMap<String, List<ICommand>> objCmdMap;
  private String svgString;

  /**
   * Sole constructor of SvgView. Initialize fields animation, ap, and fps to the given arguments
   * and use the given model to initialize objNames, objCmdMap, and svgString.
   * This constructor assumes the animation given to it has already been created and has had its
   * compile method called.
   * This view will not write or append to the given destination until renderAnimation is called.
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

  /**
   * Initializes the fields objNames and objCmdMap by using the HashMap returned by the getCmdMap
   * method in IAnimation. The purpose of objNames and objCmdMap is to keep track of every object
   * that exists in the animation and the motions associated with them.
   */
  private void initMapAndList() {
    HashMap<Integer, List<ICommand>> tickCmdMap = animation.getCmdMap();
    objCmdMap = new HashMap<>();
    objNames = new ArrayList<>();

    for (int i = 0; i <= animation.getNFrames(); i++) {
      if (tickCmdMap.containsKey(i)) {
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
  }

  /**
   * Initializes the field svgString using the objNames and objCmdMap fields.
   * Using these two fields, we iterate through every unique object and assign their motions to
   * them in the SVG format.
   */
  private void initSvgString() {
    StringBuilder svgBuilder = new StringBuilder();
    String line = String.format(
            "<svg width=\"%d\" height=\"%d\" "
                    + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n",
            animation.getWindowWidth(), animation.getWindowHeight());
    svgBuilder.append(line);

    if (!objNames.isEmpty()) {
      for (String name : objNames) {
        svgBuilder.append(cmdsToSvg(objCmdMap.get(name)));
      }
    }
    svgBuilder.append("</svg>");

    svgString = svgBuilder.toString();
  }

  /**
   * This method takes a list of ICommand that all target the same object and use their logs to
   * create a representation of the object's behavior in the svg format.
   *
   * @param cmds the given list of ICommands.
   * @return a svg representation of the object's behavior in the animation.
   */
  private String cmdsToSvg(List<ICommand> cmds) {
    if (cmds.isEmpty()) {
      return "";
    }
    /*
     * shapeInfo is an array containing the target shape's type and attribute names
     * shapeInfo[0] = the shapeType e.g. "rect" or "ellipse"
     * shapeInfo[1] = the name of the x position attribute e.g. "x" or "cx"
     * shapeInfo[2] = the name of the y position attribute e.g. "y" or "cy"
     * shapeInfo[3] = the name of the x dimension attribute e.g. "width" or "rx"
     * shapeInfo[4] = the name of the y dimension attribute e.g. "height" or "ry"
     */
    String[] shapeInfo = new String[5];
    StringBuilder builder = new StringBuilder();

    if (cmds.get(0) instanceof AddRectCmd) {
      shapeInfo[0] = "rect";
      shapeInfo[1] = "x";
      shapeInfo[2] = "y";
      shapeInfo[3] = "width";
      shapeInfo[4] = "height";
    } else if (cmds.get(0) instanceof AddOvalCmd) {
      shapeInfo[0] = "ellipse";
      shapeInfo[1] = "cx";
      shapeInfo[2] = "cy";
      shapeInfo[3] = "rx";
      shapeInfo[4] = "ry";
    } else {
      return "";
    }
    builder.append(parseAddShape(shapeInfo, cmds.get(0)));

    for (int i = 1; i < cmds.size(); i++) {
      if (cmds.get(i) instanceof MoveCmd) {
        builder.append(parseMoveShape(shapeInfo, cmds.get(i)));
      } else if (cmds.get(i) instanceof ChangeColorCmd) {
        builder.append(parseChangeColor(shapeInfo, cmds.get(i)));
      } else if (cmds.get(i) instanceof ResizeCmd) {
        builder.append(parseResizeShape(shapeInfo, cmds.get(i)));
      } else if (cmds.get(i) instanceof RemoveDrawableCmd) {
        builder.append(parseRemoveShape(shapeInfo, cmds.get(i)));
      }
    }
    builder.append(String.format("</%s>\n", shapeInfo[0]));

    return builder.toString();
  }

  /**
   * Given an ICommand that adds a shape to the animation and the shape's type and attribute names,
   * parse the command into a form that svg recognizes.
   * This method assumes that input is a command that adds a shape to the animation.
   *
   * @param shapeInfo a string array containing the target object's type and attributes
   * @param cmd       the add shape command
   * @return the command parsed into svg format
   */
  private String parseAddShape(String[] shapeInfo, ICommand cmd) {
    String[] args = cmd.logCmd().split(" ");
    int[] pos = new int[2];
    int[] rgb = new int[3];
    parsePosnStr(args[7], pos);
    parseColorStr(args[16], rgb);
    int width = (int) Double.parseDouble(args[10]);
    int height = (int) Double.parseDouble(args[13]);

    String line = String.format(
            "<%s id=\"%s\" %s=\"%d\" %s=\"%d\" %s=\"%d\" %s=\"%d\" "
                    + "fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n",
            shapeInfo[0], cmd.getTarget(), shapeInfo[1], pos[0], shapeInfo[2], pos[1],
            shapeInfo[3], width, shapeInfo[4], height, rgb[0], rgb[1], rgb[2]);
    String line2 = String.format(
            "<set attributeType=\"xml\" begin=\"%dms\" "
                    + "attributeName=\"visibility\" to=\"visible\" />\n",
            cmd.getStartTick() * 1000 / fps);

    return line + line2;
  }

  /**
   * Given an ICommand that removes a shape from the animation and the shape's type and attribute
   * names, parse the command into a form that svg recognizes.
   * This method assumes that input is a command that removes a shape from the animation.
   *
   * @param shapeInfo a string array containing the target object's type and attributes
   * @param cmd       the add shape command
   * @return the command parsed into svg format
   */
  private String parseRemoveShape(String[] shapeInfo, ICommand cmd) {

    return String.format(
            "<set attributeType=\"xml\" begin=\"%dms\" "
                    + "attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n",
            cmd.getStartTick() * 1000 / fps);
  }

  /**
   * Given a MoveCmd, parse the command into a form that svg recognizes.
   *
   * @param shapeInfo a string array containing the target object's type and attributes
   * @param cmd       the move shape command
   * @return the command parsed into svg format
   */
  private String parseMoveShape(String[] shapeInfo, ICommand cmd) {
    String[] args = cmd.logCmd().split(" ");
    int[] initPos = new int[2];
    int[] endPos = new int[2];
    parsePosnStr(args[4], initPos);
    parsePosnStr(args[6], endPos);
    String line = String.format(
            "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                    + "attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
            cmd.getStartTick() * 1000 / fps, (cmd.getEndTick() - cmd.getStartTick()) * 1000 / fps,
            shapeInfo[1], initPos[0], endPos[0]);
    String line2 = String.format(
            "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                    + "attributeName=\"%s\" from=\"%d\" to=\"%d\" />\n",
            cmd.getStartTick() * 1000 / fps, (cmd.getEndTick() - cmd.getStartTick()) * 1000 / fps,
            shapeInfo[2], initPos[1], endPos[1]);

    return line + line2;
  }

  /**
   * Given a ResizeCmd, parse the command into a form that svg recognizes.
   *
   * @param shapeInfo a string array containing the target object's type and attributes
   * @param cmd       the move shape command
   * @return the command parsed into svg format
   */
  private String parseResizeShape(String[] shapeInfo, ICommand cmd) {
    String[] args = cmd.logCmd().split(" ");
    int startWidth = (int) Double.parseDouble(args[4]);
    int startHeight = (int) Double.parseDouble(args[6]);
    int endWidth = (int) Double.parseDouble(args[8]);
    int endHeight = (int) Double.parseDouble(args[10]);
    String line = String.format(
            "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                    + "attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
            cmd.getStartTick() * 1000 / fps, (cmd.getEndTick() - cmd.getStartTick()) * 1000 / fps,
            shapeInfo[3], startWidth, endWidth);
    String line2 = String.format(
            "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                    + "attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
            cmd.getStartTick() * 1000 / fps, (cmd.getEndTick() - cmd.getStartTick()) * 1000 / fps,
            shapeInfo[4], startHeight, endHeight);

    return line + line2;
  }

  /**
   * Given a ChangeColorCmd, parse the command into a form that svg recognizes.
   *
   * @param shapeInfo a string array containing the target object's type and attributes
   * @param cmd       the move shape command
   * @return the command parsed into svg format
   */
  private String parseChangeColor(String[] shapeInfo, ICommand cmd) {
    String[] args = cmd.logCmd().split(" ");
    int[] initRGB = new int[3];
    int[] endRGB = new int[3];
    parseColorStr(args[5], initRGB);
    parseColorStr(args[7], endRGB);

    return String.format(
            "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                    + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" "
                    + "fill=\"freeze\" />\n",
            cmd.getStartTick() * 1000 / fps, (cmd.getEndTick() - cmd.getStartTick()) * 1000 / fps,
            initRGB[0], initRGB[1], initRGB[2], endRGB[0], endRGB[1], endRGB[2]);
  }

  /**
   * Given the toString representation of a Posn, set the first two indices of the given array to
   * the truncated x and y values of the Posn. Assumes that the given string is obtained by
   * calling the toString method in Posn and that the given array's length is at least 2.
   *
   * @param str the toString of some Posn. Should be in the form "( x, y )".
   * @param pos the array to store the obtained x and y values to.
   */
  private void parsePosnStr(String str, int[] pos) {
    String[] splitStr = str.substring(1, str.length() - 1).split(",");
    pos[0] = (int) Double.parseDouble(splitStr[0]);
    pos[1] = (int) Double.parseDouble(splitStr[1]);
  }

  /**
   * Given the toString representation of a Color, set the first three indices of the given array
   * to the truncated rgb values of the Color. Assumes that the given string is obtained by
   * calling the toString method in Color and that the given array's length is at least 3.
   *
   * @param str the toString of some Color. Should be in the form "(r,b,g)".
   * @param rgb the array to store the obtained rgb values to.
   */
  private void parseColorStr(String str, int[] rgb) {
    String[] splitStr = str.substring(1, str.length() - 1).split(",");
    rgb[0] = (int) Double.parseDouble(splitStr[0]);
    rgb[1] = (int) Double.parseDouble(splitStr[1]);
    rgb[2] = (int) Double.parseDouble(splitStr[2]);
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
