package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.commands.ICommand;
import model.interfaces.Drawable;

/**
 * This is a simple implementation of IAnimation that extends AAnimation.
 * New Fields:
 * - objects     a HashMap of all the objects in this animation mapped to their names
 * - cmdMap      a HashMap of all the commands in this animation mapped to their start tick
 * - cmds        a list of all the commands in this animation. Used to reset the cmdMap when needed
 * This class implements addCmd and removeCmd from the IAnimation interface.
 */
public class SimpleAnimation extends AAnimation {

  private HashMap<String, Drawable> objects;
  private HashMap<Integer, List<ICommand>> cmdMap;
  private List<ICommand> cmds;

  /**
   * Default and sole constructor of SimpleAnimation.
   * Calls parent constructor and initializes objects and cmdMap to empty HashMaps.
   */
  public SimpleAnimation() {
    super();
    objects = new HashMap<>();
    cmdMap = new HashMap<>();
    cmds = new ArrayList<>();
  }

  @Override
  public void addCmd(ICommand cmd) {
    cmds.add(cmd);
    resetCmdMap();
  }

  @Override
  public void removeCmd(ICommand cmd) {
    cmds.remove(cmd);
    resetCmdMap();
  }

  /**
   * Resets cmdMap to its initial state:
   *  - resets cmdMap to a new HashMap
   *  - calls reset on every ICommand in cmds
   *  - adds every ICommand in cmds to cmdMap
   */
  private void resetCmdMap() {
    cmdMap = new HashMap<>();
    for (ICommand cmd : cmds) {
      cmd.reset();
      addToCmdMap(cmd);
    }
  }

  /**
   * Applies the list of commands to the object and adds new frames to the canvas.
   *
   * @param objects list of drawable objects.
   */
  private void initFrames(List<Drawable> objects) {
    for (int i = 0; i < nFrames; i++) {
      if (cmdMap.containsKey(i)) {
        List<ICommand> cmds = cmdMap.get(i);
        for (ICommand cmd : cmds) {
          if (!cmd.isRunning()) {
            cmd.execute();
            cmdLog.add(cmd.logCmd());
          } else {
            cmd.execute();
          }
          if (!cmd.isComplete()) {
            addToCmdMap(cmd);
          }
        }
      }

      frames.add(new Frame(objects));
    }
    assert (nFrames == frames.size());
  }

  /**
   * Adds the given command to the cmdMap.
   *
   * @param cmd the command.
   */
  private void addToCmdMap(ICommand cmd) {
    int start = cmd.getStartTick();

    if (cmdMap.containsKey(start)) {
      cmdMap.get(start).add(cmd);
    } else {
      List<ICommand> cmdList = new ArrayList<>();
      cmdList.add(cmd);
      cmdMap.put(start, cmdList);
    }
    resetCmdMap();
  }

  /**
   * Get the tick-command HashMap of this animation.
   *
   * @return cmdMap the HashMap of this animation's commands mapped to their start ticks.
   */
  public HashMap<Integer, List<ICommand>> getCmdMap() {
    return new HashMap<>(cmdMap);
  }
}
