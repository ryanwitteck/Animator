package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.commands.ICommand;
import model.ObjectInterfaces.Drawable;

/**
 * Represents an animation that cannot be edited after creation.
 */
public class PreBuiltAnimation extends AAnimation {
  private HashMap<Integer, List<ICommand>> cmdMap;

  /**
   * Constructs an animation given an initial state and a list of commands.
   *
   * @param objects the list of objects in the animation
   * @param cmds    the list of commands
   */
  public PreBuiltAnimation(List<Drawable> objects, List<ICommand> cmds) {
    super();
    this.frames.add(new Frame(objects));
    initHashMap(cmds);
    initFrames(objects);
  }

  // TODO -- document
  private void initHashMap(List<ICommand> cmds) {
    cmdMap = new HashMap<Integer, List<ICommand>>();
    for (ICommand cmd : cmds) {
      nFrames = Math.max(nFrames, cmd.getEndTick());
      addToCmdMap(cmd);
    }
  }

  // TODO -- document
  private void initFrames(List<Drawable> objects) {
    for (int i = 1; i < nFrames; i++) {
      if (cmdMap.containsKey(i)) {
        List<ICommand> cmds = cmdMap.get(i);
        for (ICommand cmd : cmds) {
          if (!cmd.isRunning()) {
            cmd.execute();
            cmdLog.add(cmd.logCmd());
          }
          else {
            cmd.execute();
          }
          frames.add(new Frame(objects));
          if (!cmd.isComplete()) {
            addToCmdMap(cmd);
          }
        }
      }
    }
    nFrames = frames.size();
  }

  // TODO -- document
  private void addToCmdMap(ICommand cmd) {
    int start = cmd.getStartTick();

    if (cmdMap.containsKey(start)) {
      cmdMap.get(start).add(cmd);
    } else {
      List<ICommand> cmdList = new ArrayList<ICommand>();
      cmdList.add(cmd);
      cmdMap.put(start, cmdList);
    }
  }

  @Override
  public void execute(ICommand cmd) {
    throw new IllegalStateException("Error: This animation does not accept commands");
  }

  /**
   * Get the tick-command map of this animation.
   * @return cmdMap this tick-command map
   */
  public HashMap<Integer, List<ICommand>> getCmdMap() {
    return cmdMap;
  }
}
