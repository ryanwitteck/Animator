package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for IAnimation interface.
 * Implements all methods defined in IAnimation except for addCmd and removeCmd.
 */
public abstract class AAnimation implements IAnimation {

  protected List<IFrame> frames;
  protected List<String> cmdLog;
  protected int nFrames;

  /**
   * Default constructor for AAnimation.
   * Initialize frames and cmdLog as empty lists.
   * Initialize tick and nFrames as 0.
   */
  public AAnimation() {
    frames = new ArrayList<>();
    cmdLog = new ArrayList<>();
    nFrames = 0;
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
    return new ArrayList<>(frames);
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
