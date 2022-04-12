package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import cs3500.animator.model.IAnimation;

/**
 * This class represents a visual representation of an animation using swing that the user can
 * interact with. This view extends the VisualView class and retains all the functionality present
 * in its parent class.
 * This view provides the user with the ability to:
 * - Start, pause, resume, and restart the animation.
 * - Enable/disable looping.
 * - Increase or decrease the speed of the animation.
 * The animation will be rendered as a video in a new window that is opened when renderAnimation is
 * run. This window will also allow the user to scroll through it (TODO implement scrolling).
 */
public class InteractiveView extends JFrame implements AnimationView, ActionListener, ItemListener {

  private final Timer timer;
  private final IAnimation animation;
  private final SwingViewPanel panel;
  private int fps;
  private int tick;
  private boolean isLooping;
  private final JCheckBox looping;
  private final JTextField fpsTextbox;

  /**
   * Sole constructor for InteractiveView.
   * Initializes all fields of this class and sets the parameters of the window that the animation
   * will be animated in.
   * The window will not appear until the renderAnimation method is called.
   * The animation will not play until the play button is pressed.
   *
   * @param windowTitle the title of the animation window
   * @param animation   the animation that this view will visualize
   * @param fps         the framerate of this animation in frames per second
   */
  public InteractiveView(String windowTitle, IAnimation animation, int fps) {
    super(windowTitle);

    setSize(animation.getWindowWidth(), animation.getWindowHeight() + 100);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.animation = animation;
    this.timer = new Timer(1000 / fps, this);
    this.fps = fps;
    this.timer.setActionCommand("timer");
    this.tick = 0;
    this.isLooping = false;

    //main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //animation panel with a scrollbar
    this.panel = new SwingViewPanel();
    this.panel.setFrame(animation.getFrame(0));
    JScrollPane scrollPane = new JScrollPane(panel);
    mainPanel.add(BorderLayout.CENTER, scrollPane);

    //control panel
    JPanel controlPanel = new JPanel();
    controlPanel.setBorder(BorderFactory.createTitledBorder("Animation Controls"));
    controlPanel.setPreferredSize(new Dimension(animation.getWindowWidth(), 100));
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
    mainPanel.add(BorderLayout.SOUTH, controlPanel);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

    //play/pause button
    JButton pausePlay = new JButton("Play/Pause");
    pausePlay.setActionCommand("play");
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
    fpsTextbox.setText(fps + "");
    fpsTextbox.setActionCommand("fps");
    fpsTextbox.addActionListener(this);
    fpsControl.add(fpsTextbox);
    rightPanel.add(fpsControl);

    controlPanel.add(buttonPanel);
    controlPanel.add(rightPanel);
  }

  @Override
  public void renderAnimation() {
    this.setVisible(true);
    repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    switch (cmd) {
      case "timer":
        tick++;
        if (tick >= animation.getNFrames()) {
          if (isLooping) {
            tick = 0;
          }
          else {
            timer.stop();
          }
        }
        else {
          panel.setFrame(animation.getFrame(tick));
          repaint();
        }
        break;
      case "play":
        if (timer.isRunning()) {
          timer.stop();
        }
        else {
          timer.start();
        }
        break;
      case "restart":
        tick = 0;
        panel.setFrame(animation.getFrame(0));
        repaint();
        timer.start();
        break;
      case "fps":
        try {
          int newfps = Integer.parseInt(fpsTextbox.getText());
          if (newfps > 0 && newfps < 4096) {
            fps = newfps;
            timer.setDelay(1000 / fps);
          }
        } catch(NumberFormatException ignored) {
          
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
      isLooping = !isLooping;
      if (isLooping) {
        looping.setText("Loop Animation (on)");
      }
      else {
        looping.setText("Loop Animation (off)");
      }
    }
  }
}

