package model.shapes;

import model.interfaces.Drawable;
import model.interfaces.Movable;
import model.interfaces.Scalable;

/**
 * Our interface for various shapes that we want to create.
 * All shapes extend the Movable, Drawable, and Scalable interfaces.
 * Subject to change.
 */
public interface IShape extends Movable, Drawable, Scalable {

}
