package model.ObjectInterfaces;

import model.Color;

/**
 * Our interface for any object that can be scaled when animated in the future.
 */
public interface Scalable {
  // TODO -- Document

  void stretchHorizontal(double scale);

  void stretchVertical(double scale);

  void scaleUp(double scale);

  void scaleDown(double scale);
}
