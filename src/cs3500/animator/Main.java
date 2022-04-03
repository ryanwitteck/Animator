package cs3500.animator;

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
 * This class is the main class of this program. It contains one method main, which is used
 * by the user to interact with this program. This class is responsible for parsing command line
 * inputs, using our other classes to create an animation model and its view, and giving the user
 * access to the view.
 */
public class Main {

  /**
   * Our main method. Takes in command line arguments and parses them to get the information
   * required to create a model and view of the user's animation.
   * <p>
   * Command line arguments should be in formatted in pairs with the field being declared first
   * followed by the value. In other words:
   * "-in" followed by "name-of-animation-file"
   * "-view" followed by "type-of-view"
   * "-out" followed by "where-output-should-go"
   * "-speed" followed by "integer-ticks-per-second"
   * The order that the arguments for -in, -out, -view, and -speed are given does not matter.
   * <p>
   * The arguments for -in and -view are required by this method,
   * while -out will default to System.out and -speed will default to 1 tick per second.
   *
   * @param args the command line arguments.
   * @throws IOException if the program cannot read or output to a given file.
   */
  public static void main(String[] args) throws IOException {
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

    System.out.println();
  }
}
