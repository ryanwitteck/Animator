import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.SimpleAnimation;
import cs3500.animator.model.attributes.Color;
import cs3500.animator.model.attributes.Posn;
import cs3500.animator.model.commands.AddOvalCmd;
import cs3500.animator.model.commands.AddRectCmd;
import cs3500.animator.model.commands.ChangeColorCmd;
import cs3500.animator.model.commands.ICommand;
import cs3500.animator.model.commands.MoveCmd;
import cs3500.animator.model.commands.ResizeCmd;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.VisualView;

/**
 * This class runs a short demo of our swing view. It is for testing purposes only.
 */
public class SwingDemo {

  /**
   * This method creates and renders a basic animation in the swing view.
   * This is for testing purposes only.
   *
   * @param args the command line arguments. These are not used in this method.
   */
  public static void main(String[] args) {
    List<ICommand> cmds = new ArrayList<>();
    cmds.add(new AddRectCmd("R1", 0, 0, 30, 40, new Color(0, 100, 100), 1));
    cmds.add(new AddRectCmd("R2", 50, 100, 20, 20, new Color(0, 200, 0), 1));
    cmds.add(new AddOvalCmd("O1", 100, 100, 90, 60, new Color(100, 100, 100), 2));
    cmds.add(new MoveCmd("R1", 4, 100, new Posn(0, 0), new Posn(50, 200)));
    cmds.add(new MoveCmd("R2", 5, 100, new Posn(50, 100), new Posn(400, 300)));
    cmds.add(new MoveCmd("O1", 4, 150, new Posn(100, 100), new Posn(45, 45)));
    cmds.add(new ChangeColorCmd("O1", 4, 150, new Color(100, 100, 100), new Color(255, 0, 170)));
    cmds.add(new ResizeCmd("O1", 4, 150, 90, 60, 60, 90));

    IAnimation animation = new SimpleAnimation();
    for (ICommand cmd : cmds) {
      animation.addCmd(cmd);
    }
    animation.compile();

    AnimationView view = new VisualView("Demo", animation, 50);
    try {
      view.renderAnimation();
    } catch (IOException e) {
      System.out.println("Failed to render demo in SwingDemo.testRender");
    }
  }
}
