package cs3500.animator.view;

import java.io.IOException;

/**
 * Interface for the visualization of IAnimation.
 * Implementations of this interface should be able to provide an understandable
 * visual representation of every frame of an Animation.
 */
public interface AnimationView {

  /**
   * This method renders the Animation being viewed. --- TODO make better
   *
   * @throws IOException if the view fails to render the animation to the given data destination.
   */
  void renderAnimation() throws IOException;
}
