package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.model.interfaces.Drawable;

/**
 * Abstract class for IAnimation interface.
 * Implements fields:
 * - objects    the list of all the objects in this animation. Used to keep track of the order
 *              in which objects are added to the animation.
 * - objMap     the HashMap of the objects in this animation mapped to their name. Used to quickly
 *              reference specific objects.
 * - frames     the list of IFrames that represents this animation
 * - cmdLog     a list of the string representations of each ICommand in this animation.
 *              From this, we can create the command log.
 * - nFrames    the number of frames in this animation i.e. the size of frames
 * - width      the width of the window
 * - height     the height of the window
 * This class implements all methods defined in IAnimation except for addCmd, removeCmd, compile,
 * and getCmdMap.
 * This class is meant to be extended by more in depth implementations of IAnimation.
 */
public abstract class AAnimation implements IAnimation {

  protected List<Drawable> objects;
  protected HashMap<String, Drawable> objMap;
  protected List<IFrame> frames;
  protected List<String> cmdLog;
  protected int nFrames;
  private int width;
  private int height;

  /**
   * Sole constructor for AAnimation.
   * Initializes frames and cmdLog as empty lists, nFrames as 0, and both width and height as 500.
   */
  public AAnimation() {
    this.width = 500;
    this.height = 500;
    objects = new ArrayList<>();
    objMap = new HashMap<>();
    frames = new ArrayList<>();
    cmdLog = new ArrayList<>();
    nFrames = 0;
  }

  @Override
  public void addDrawable(Drawable d) {
    if (objMap.containsKey(d.getName())) {
      throw new IllegalArgumentException("Error: An object with this name already exists");
    }
    Drawable copy = d.getCopy();
    objects.add(copy);
    objMap.put(d.getName(), copy);
  }

  @Override
  public void removeDrawable(String name) {
    objects.remove(objMap.remove(name));
  }

  @Override
  public void setBounds(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public Drawable getDrawable(String name) {
    return objMap.get(name);
  }

  @Override
  public IFrame getFrame(int tick) {
    if (outBounds(tick)) {
      throw new IllegalArgumentException("Error: Invalid tick");
    }
    return frames.get(tick);
  }

  @Override
  public List<IFrame> getFrames() {
    List<IFrame> copy = new ArrayList<>();

    for (IFrame f : frames) {
      copy.add(new Frame(f.listObjects()));
    }

    return copy;
  }

  /**
   * Check if the given index is a valid index for frames.
   * An index is valid if it is in the range [0, nFrames - 1].
   *
   * @param index the given index
   * @return whether the index is in bounds
   */
  protected boolean outBounds(int index) {
    return (index < 0 || index >= this.nFrames);
  }

  @Override
  public int getNFrames() {
    return nFrames;
  }

  @Override
  public String getCmdLog() {
    StringBuilder log = new StringBuilder();
    log.append("Window: ").append(width).append(" by ").append(height).append("\n");
    for (String s : cmdLog) {
      log.append(s).append("\n");
    }
    return log.toString();
  }

  @Override
  public int getWindowWidth() {
    return width;
  }

  @Override
  public int getWindowHeight() {
    return height;
  }
}
