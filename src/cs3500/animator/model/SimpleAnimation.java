package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.model.commands.ICommand;

/**
 * This is a simple implementation of IAnimation that extends AAnimation.
 * New Fields:
 * - cmdMap      a HashMap of all the commands in this animation mapped to their start tick
 * - cmds        a list of all the commands in this animation. Used to reset the cmdMap when needed
 * This class implements addCmd, removeCmd, compile, and getCmdMap from the IAnimation interface.
 *
 * <p>This class is used to create a model of an animation using the andCmd, removeCmd, and
 * compile methods. Using these three methods, it is also possible to edit an animation and
 * recompile it into a new animation.
 *
 * <p>Important: adding or removing commands from the animation may not have an effect until
 * after the compile method is called. These three methods should be the only ones used to create
 * an animation model. The addDrawable and removeDrawable are meant to be used only by ICommands and
 * if used the command log of this animation may be inaccurate and our views may be unable to
 * create a visualization of the model.
 */
public class SimpleAnimation extends AAnimation {

  private HashMap<Integer, List<ICommand>> cmdMap;
  private final List<ICommand> cmds;

  /**
   * Sole constructor of SimpleAnimation.
   * Calls parent constructor and initializes objects and cmdMap to empty HashMaps.
   */
  public SimpleAnimation() {
    super();
    cmdMap = new HashMap<>();
    cmds = new ArrayList<>();
  }

  @Override
  public void addCmd(ICommand cmd) {
    cmds.add(cmd);
  }

  @Override
  public void removeCmd(ICommand cmd) {
    cmds.remove(cmd);
  }

  /**
   * Executes the list of commands to create the frames in this animation.
   * - resets the command map
   * - resets the cmdLog
   * - resets the object map
   * - resets frames
   * - runs all the at a given tick commands to initialize each frame
   */
  public void compile() {
    resetCmdMap();
    cmdLog = new ArrayList<>();
    objects = new HashMap<>();
    frames = new ArrayList<>();

    for (int i = 0; i < nFrames; i++) {
      if (cmdMap.containsKey(i)) {
        for (ICommand cmd : cmdMap.get(i)) {
          if (!cmd.isRunning()) {
            cmd.execute(this);
            cmdLog.add(cmd.logCmd());
          } else {
            cmd.execute(this);
          }
          if (!cmd.isComplete()) {
            addToCmdMap(cmd);
          }
        }
      }
      frames.add(new Frame(objects.values()));
    }
    assert (nFrames == frames.size());
  }

  /**
   * Resets cmdMap to its initial state.
   * - resets cmdMap to a new HashMap
   * - calls reset on every ICommand in cmds
   * - adds every ICommand in cmds to cmdMap
   */
  private void resetCmdMap() {
    cmdMap = new HashMap<>();
    for (ICommand cmd : cmds) {
      cmd.reset();
      addToCmdMap(cmd);
    }
  }

  /**
   * Adds the given command to the cmdMap.
   *
   * @param cmd the command.
   */
  private void addToCmdMap(ICommand cmd) {
    int start = cmd.getStartTick();
    nFrames = Math.max(nFrames, cmd.getEndTick());

    if (cmdMap.containsKey(start)) {
      cmdMap.get(start).add(cmd);
    } else {
      List<ICommand> cmdList = new ArrayList<>();
      cmdList.add(cmd);
      cmdMap.put(start, cmdList);
    }
  }

  @Override
  public HashMap<Integer, List<ICommand>> getCmdMap() {
    resetCmdMap();
    return new HashMap<>(cmdMap);
  }
}
