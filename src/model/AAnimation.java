package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents common attributes and functions among IAnimation implementations.
 */
public abstract class AAnimation implements IAnimation {
  protected List<IFrame> frames;
  protected List<String> cmdLog;
  protected int tick;
  protected int nFrames;

  /**
   * Construct for AAnimation.
   * Initialize frames and cmdLog as empty lists.
   * Initialize tick and nFrames as 0.
   */
  public AAnimation() {
    frames = new ArrayList<>();
    cmdLog = new ArrayList<>();
    tick = 0;
    nFrames = 0;
  }

  @Override
  public IFrame getFrame(int tick) {
    if (!inBounds(tick)) {
      throw new IllegalArgumentException("Error: Invalid tick");
    }
    this.tick = tick;
    return frames.get(this.tick);
  }

  @Override
  public IFrame getCurrent() {
    if (!inBounds(tick)) {
      throw new IllegalStateException("Error: Invalid tick");
    }
    return frames.get(tick);
  }

  @Override
  public IFrame getPrev() {
    if (!inBounds(tick - 1)) {
      throw new IllegalStateException("Error: Invalid tick");
    }
    tick--;
    return frames.get(tick);
  }

  @Override
  public IFrame getNext() {
    if (!inBounds(tick + 1)) {
      throw new IllegalStateException("Error: Invalid tick");
    }
    tick++;
    return frames.get(tick);
  }

  @Override
  public List<IFrame> getFrames() {
    return frames;
  }

  @Override
  public String getCmdLog() {
    String log = "";
    for (String s : cmdLog) {
      log += s + "\n";
    }
    return log;
  }

  // TODO -- document
  protected boolean inBounds(int index) {
    return (index >= 0 && index < this.nFrames);
  }
}
