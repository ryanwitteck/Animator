package cs3500.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.*;

import cs3500.animator.view.InteractiveView;

/**
 * This class is a controller of our interactive animation implementation. This controller creates
 * a GUI in a new swing window that the user can use to interact with an interactive view.
 * This controller provides the user with the ability to:
 * - Start, pause, resume, and restart the animation.
 * - Enable/disable looping.
 * - Increase or decrease the speed of the animation.
 */
public class SwingController extends JFrame implements
        AnimationController, ActionListener, ItemListener {
  private final InteractiveView view;
  private final JCheckBox looping;
  private final JTextField fpsTextbox;

  /**
   * Sole constructor for SwingController.
   * Initializes all fields of this class and sets up the GUI that will be used to by the user to
   * control the given InteractiveView.
   * Neither the control window nor the view will be rendered util the start method is called.
   * The animation view may not play until the play button is pressed depending on the view
   * implementation.
   *
   * @param view the InteractiveView that this controller will allow the user to interact with
   */
  public SwingController(InteractiveView view) {
    super("Control Window");

    setSize(350, 150);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.view = view;

    //main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout());
    add(mainPanel);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

    //play/pause button
    JButton pausePlay = new JButton("Play/Pause");
    pausePlay.setActionCommand("playPause");
    pausePlay.addActionListener(this);
    //restart button
    JButton restart = new JButton("Restart");
    restart.setActionCommand("restart");
    restart.addActionListener(this);
    buttonPanel.add(pausePlay);
    buttonPanel.add(restart);

    //looping and framerate panel
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

    //looping option
    this.looping = new JCheckBox("Loop Animation (off)");
    looping.setActionCommand("loop");
    looping.addItemListener(this);
    rightPanel.add(looping);

    //framerate controller
    JPanel fpsControl = new JPanel();

    fpsControl.add(new JLabel("fps: "));
    this.fpsTextbox = new JTextField();
    fpsTextbox.setColumns(5);
    fpsTextbox.setText(view.getFps() + "");
    fpsTextbox.setActionCommand("fps");
    fpsTextbox.addActionListener(this);
    fpsControl.add(fpsTextbox);
    rightPanel.add(fpsControl);

    mainPanel.add(buttonPanel);
    mainPanel.add(rightPanel);
  }

  @Override
  public void start() throws IOException {
    view.renderAnimation();
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    switch (cmd) {
      case "playPause":
        if (view.isRunning()) {
          view.pause();
        }
        else {
          view.play();
        }
        break;
      case "restart":
        view.restart();
        break;
      case "fps":
        try {
          int fps = Integer.parseInt(fpsTextbox.getText());
          view.setFps(fps);
        } catch (Exception exception) {
          fpsTextbox.setText(view.getFps() + "");
        }
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + cmd);
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    String cmd = ((JCheckBox) e.getItemSelectable()).getActionCommand();
    if (cmd.equals("loop")) {
      view.toggleLooping();
      if (view.getIsLooping()) {
        looping.setText("Loop Animation (on)");
      } else {
        looping.setText("Loop Animation (off)");
      }
    }
  }
}
