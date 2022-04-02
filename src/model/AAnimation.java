package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.commands.ICommand;
import model.interfaces.Drawable;

/**
 * Abstract class for IAnimation interface.
 * Implements fields:
 *  - objects   the HashMap of the objects in this animation mapped to their name
 *  - frames    the list of IFrames that represents this animation
 *  - cmdLog    a list of the string representations of each ICommand in this animation.
 *              From this, we can create the command log.
 *  - nFrames   the number of frames in this animation i.e. the size of frames
 * Implements all methods defined in IAnimation except for addCmd and removeCmd.
 */
public abstract class AAnimation implements IAnimation {

  protected HashMap<String, Drawable> objects;
  protected List<IFrame> frames;
  protected List<String> cmdLog;
  protected int nFrames;

  /**
   * Default and sole constructor for AAnimation.
   * Initializes frames and cmdLog as empty lists and nFrames as 0.
   */
  public AAnimation() {
    objects = new HashMap<>();
    frames = new ArrayList<>();
    cmdLog = new ArrayList<>();
    nFrames = 0;
  }

  @Override
  public void addDrawable(Drawable d) {
    objects.put(d.getName(), d);
  }

  @Override
  public void removeDrawable(Drawable d) {
    objects.remove(d.getName());
  }

  @Override
  public Drawable getDrawable(String name) {
    return objects.get(name);
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

  @Override
  public String getCmdLog() {
    StringBuilder log = new StringBuilder();
    for (String s : cmdLog) {
      log.append(s).append("\n");
    }
    return log.toString();
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
}
