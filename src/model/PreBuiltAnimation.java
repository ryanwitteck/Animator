package model;

import java.util.HashMap;
import java.util.List;

import controller.commands.ICommand;

/**
 * Represents an animation that cannot be edited after creation.
 */
public class PreBuiltAnimation extends AAnimation {
  private List<IFrame> frames;
  private int tick;
  private HashMap<Integer, List<ICommand>> cmdMap;

  /**
   * Constructs an animation given a list of commands.
   * @param cmds the list of commands
   */
  public PreBuiltAnimation(List<ICommand> cmds) {
    initHashMap(cmds);
    initFrames();
    tick = 0;
  }

  // TODO -- document and implement
  private void initHashMap(List<ICommand> cmds) {

  }

  // TODO -- document and implement
  private void initFrames() {

  }

  @Override
  public void execute(ICommand cmd) {
    throw new IllegalStateException("Error: This animation does not accept commands");
  }
}
