package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.interfaces.Drawable;

/**
 * Represents a single frame in an animation.
 * Each IFrame contains the information necessary for a user to visualize
 * the state of an animation at a single instant in time.
 */
public interface IFrame {

  /**
   * Get the list of animated objects in this frame.
   *
   * @return the list of objects being animated
   */
  List<Drawable> listObjects();

  // TODO -- draw method
}
