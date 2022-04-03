package cs3500.animator.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.IFrame;
import cs3500.animator.model.interfaces.Drawable;
import cs3500.animator.model.shapes.Rectangle;

/**
 * A panel that can hold the actual IAnimation visualization.
 * Note that this class is package-private.
 * It's part of the View, and internal to the View implementation.
 */
class SwingViewPanel extends JPanel {

  private IFrame frame;

  /**
   * Set the current IFrame to the given IFrame.
   * The given IFrame will become the new IFrame currently being visualized.
   *
   * @param frame the desired IFrame
   */
  public void setFrame(IFrame frame) {
    this.frame = frame;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    List<Drawable> list = frame.listObjects();
    for (Drawable d : list) {
      if (d instanceof Rectangle) {
        Rectangle r = (Rectangle) d;
        g2.setColor(new Color());
        g2.drawRect((int) r.getPos().getX(), (int) r.getPos().getY(), r.getWidth(), r.getHeight());
      }
    }

  }
}
