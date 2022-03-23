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
   * Constructor for AAnimation. Initialize frames and cmdLog as empty lists. Initialize tick and
   * nFrames as 0.
   */
  public AAnimation() {
    frames = new ArrayList<>();
    cmdLog = new ArrayList<>();
    tick = 0;
    nFrames = 0;
  }

  @Override
  public IFrame getFrame(int tick) {
    if (outBounds(tick)) {
      throw new IllegalArgumentException("Error: Invalid tick");
    }
    this.tick = tick;
    return frames.get(this.tick);
  }

  @Override
  public IFrame getCurrent() {
    if (outBounds(tick)) {
      throw new IllegalStateException("Error: Invalid tick");
    }
    return frames.get(tick);
  }

  @Override
  public IFrame getPrev() {
    if (outBounds(tick - 1)) {
      throw new IllegalStateException("Error: Invalid tick");
    }
    tick--;
    return frames.get(tick);
  }

  @Override
  public IFrame getNext() {
    if (outBounds(tick + 1)) {
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
    StringBuilder log = new StringBuilder();
    for (String s : cmdLog) {
      log.append(s).append("\n");
    }
    return log.toString();
  }

  /**
   * Check if the given index is a valid index for frames.
   *
   * @param index the given index
   * @return whether the index is in bounds
   */
  protected boolean outBounds(int index) {
    return (index < 0 || index >= this.nFrames);
  }
}
