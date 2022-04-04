package cs3500.animator.view;

import java.io.IOException;

/**
 * Interface for the visualization of IAnimation.
 * Implementations of this interface should be able to provide an understandable
 * visual representation of an IAnimation model be that through a text output, animated video,
 * or some other method.
 */
public interface AnimationView {

  /**
   * This method renders the visualization of the IAnimation model being viewed.
   *
   * @throws IOException if the view fails to render the animation to the given data destination.
   */
  void renderAnimation() throws IOException;
}
