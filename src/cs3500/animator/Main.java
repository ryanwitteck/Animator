package cs3500.animator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.io.OurModelBuilder;
import cs3500.animator.model.IAnimation;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.VisualView;
import cs3500.animator.view.TextView;

/**
 * Main class --- TODO make better
 */
public class Main {

  /**
   * TODO
   * <p>
   * -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"
   */
  public static void main(String[] args) throws FileNotFoundException {
    // If there are not an even number of arguments, we cannot parse the information
    if (args.length % 2 != 0) {
      System.out.println("There must be an even number of arguments. Exiting Program.");
      return;
    }
    // Try to parse the information necessary to make our animation
    String fileName = "";
    String viewType = "";
    String out = "";
    int tickRate = 1;
    for (int i = 0; i < args.length; i += 2) {
      switch (args[i]) {
        case "-in":
          fileName = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-out":
          out = args[i + 1];
          break;
        case "-speed":
          tickRate = Integer.parseInt(args[i + 1]);
          break;
        default:
          System.out.println("Unrecognized input. Exiting program.");
          return;
      }
    }
    if (fileName.isEmpty() || viewType.isEmpty()) {
      System.out.println("An animation file and view type must be provided. Exiting Program.");
      return;
    }

    // create the animation from the given file
    AnimationFileReader reader = new AnimationFileReader();
    IAnimation animation = reader.readFile(fileName, new OurModelBuilder());
    AnimationView view;

    try {
      switch (viewType) {
        case "text":
          if (!out.isEmpty()) {
            FileWriter writer = new FileWriter(out);
            view = new TextView(animation, writer);
          } else {
            view = new TextView(animation, System.out);
          }
          view.renderAnimation();
          break;
        case "visual":
          view = new VisualView("Animation", animation, tickRate);
          view.renderAnimation();
          break;
        case "svg":
          // TODO
          System.out.println("This view is has not been implemented yet");
          break;
        default:
          System.out.println("The view type provided is not recognized. Exiting Program.");
          return;
      }
    } catch (IOException e) {
      System.out.println("Failed to render animation. Exiting Program.");
      return;
    }

    System.out.println();
  }
}
