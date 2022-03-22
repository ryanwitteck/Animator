package model;

import java.util.List;

public abstract class AAnimation implements IAnimation {
  protected List<IFrame> frames;
  protected int tick;
  protected List<String> cmdLog;

  @Override
  public IFrame getFrame(int tick) {
    if (!inBounds(tick)) {
      throw new IllegalArgumentException("Error: Invalid tick");
    }
    return frames.get(tick);
  }

  @Override
  public IFrame getCurrent() {
    if (!inBounds(tick)) {
      throw new IllegalArgumentException("Error: Invalid tick");
    }
    return frames.get(tick);
  }

  @Override
  public IFrame getPrev() {
    if (!inBounds(tick - 1)) {
      throw new IllegalArgumentException("Error: Invalid tick");
    }
    tick--;
    return frames.get(tick);
  }

  @Override
  public IFrame getNext() {
    if (!inBounds(tick + 1)) {
      throw new IllegalArgumentException("Error: Invalid tick");
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
    return (index >= 0 && index < this.frames.size());
  }
}
