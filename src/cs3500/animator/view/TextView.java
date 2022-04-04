package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.commands.GradualCmd;
import cs3500.animator.model.commands.ICommand;
import cs3500.animator.model.commands.InstantCmd;


/**
 * This interface represents a text representation of an animation. This class will print the view
 * to the destination determined by the user. The rendered view is just the command log of the
 * IAnimation being viewed except that time is measured in seconds instead of ticks.
 */
public class TextView implements AnimationView {

  private final IAnimation animation;
  private final Appendable ap;
  private final int fps;
  private String textView;

  /**
   * Sole constructor of TextView. Initializes animation and ap to the given arguments and
   * calls initTextView to initialize the textView String using the IAnimation model.
   * The appendable will not be written to until the renderAnimation method is called.
   *
   * @param animation the animation that this view will visualize
   * @param ap        the visualization of the animation
   * @param fps       the framerate of this animation
   */
  public TextView(IAnimation animation, Appendable ap, int fps) {
    this.animation = animation;
    this.ap = ap;
    this.fps = fps;
    initTextView();
  }

  private void initTextView() {
    HashMap<Integer, List<ICommand>> cmdMap = animation.getCmdMap();
    StringBuilder builder = new StringBuilder();
    builder.append("Window: ").append(animation.getWindowWidth())
            .append(" by ").append(animation.getWindowHeight()).append("\n");

    for (int i = 0; i <= animation.getNFrames(); i++) {
      if (cmdMap.containsKey(i)) {
        for (ICommand cmd : cmdMap.get(i)) {
          String log = cmd.logCmd();
          double startTime = 1.0 * cmd.getStartTick() / fps;
          if (cmd instanceof InstantCmd) {
            builder.append(log, 0, log.indexOf("t = "));
            if (startTime > 0.1) {
              builder.append("t = ").append(Math.round(startTime * 1000) / 1000.0).append("s\n");
            } else {
              builder.append("t = ").append(Math.round(startTime * 1000000) / 1000.0)
                      .append("ms\n");
            }
          } else if (cmd instanceof GradualCmd) {
            builder.append(log, 0, log.indexOf("t = "));
            if (startTime > 0.1) {
              builder.append("t = ").append(Math.round(startTime * 1000) / 1000.0).append("s ");
            } else {
              builder.append("t = ").append(Math.round(startTime * 1000000) / 1000.0).append("ms ");
            }
            double endTime = 1.0 * cmd.getEndTick() / fps;
            if (endTime > 0.1) {
              builder.append("to t = ").append(Math.round(endTime * 1000) / 1000.0).append("s\n");
            } else {
              builder.append("to t = ").append(Math.round(endTime * 1000000) / 1000.0)
                      .append("ms\n");
            }
          } else {
            builder.append(log);
          }
        }
      }
    }

    textView = builder.toString();
  }


  @Override
  public void renderAnimation() throws IOException {
    if (ap instanceof FileWriter) {
      FileWriter writer = (FileWriter) ap;
      writer.write(textView);
      writer.close();
    } else {
      ap.append(textView);
    }
  }
}
