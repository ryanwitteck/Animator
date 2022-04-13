package cs3500.animator.ui;

import java.io.IOException;

/**
 * This interface represents a "user interface" of an interactive AnimationView.
 * Implementations of this interface should be able to provide an understandable way for the user
 * to interact with an AnimationView as it is running.
 */
public interface InteractiveUI {
  /**
   * This method is used to start the animation. It should call the renderAnimation method of the
   * AnimationView and initialize the UI that the user will use to interact with the animation.
   */
  void start() throws IOException;
}
