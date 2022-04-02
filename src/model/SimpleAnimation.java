package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.commands.ICommand;
import model.interfaces.Drawable;

/**
 * This is a simple implementation of IAnimation that extends AAnimation.
 * This class allows the user to ... TODO
 */
public class SimpleAnimation extends AAnimation {

  private HashMap<Integer, List<ICommand>> cmdMap;

  /**
   * Constructs an animation given an initial state and a list of commands.
   *
   * @param objects the list of objects in the animation
   * @param cmds    the list of commands
   */
  public SimpleAnimation(List<Drawable> objects, List<ICommand> cmds) {
    super();
    this.frames.add(new Frame(objects));
    initHashMap(cmds);
    initFrames(objects);
  }

  @Override
  public void addCmd(ICommand cmd) {
    throw new IllegalStateException("Error: This animation does not accept changes");
  }

  @Override
  public void removeCmd(ICommand cmd) {
    throw new IllegalStateException("Error: This animation does not accept changes");
  }

  /**
   * A method to initialize our hashmap given our list of commands.
   *
   * @param cmds list of commmands.
   */
  private void initHashMap(List<ICommand> cmds) {
    cmdMap = new HashMap<>();
    for (ICommand cmd : cmds) {
      nFrames = Math.max(nFrames, cmd.getEndTick());
      addToCmdMap(cmd);
    }
  }

  /**
   * Applies the list of commands to the object and adds new frames to the canvas.
   *
   * @param objects list of drawable objects.
   */
  private void initFrames(List<Drawable> objects) {
    for (int i = 1; i < nFrames; i++) {
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
  }

  /**
   * Get the tick-command map of this animation.
   *
   * @return cmdMap this tick-command map
   */
  public HashMap<Integer, List<ICommand>> getCmdMap() {
    return cmdMap;
  }
}
