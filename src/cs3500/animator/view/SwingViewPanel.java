package cs3500.animator.view;

import java.awt.*;

import javax.swing.JPanel;

import cs3500.animator.model.IFrame;

/**
 * A panel that can hold the actual IAnimation visualization.
 * Note that this class is package-private.
 * It's part of the View, and internal to the View implementation.
 */
class SwingViewPanel extends JPanel {
  private IFrame frame;

  /**
   *
   * @param frame
   */
  public void setFrame(IFrame frame) {
    this.frame = frame;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;


  }


}
