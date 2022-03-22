package model;

import java.util.List;

import controller.commands.ICommand;
import model.ObjectInterfaces.Drawable;

public interface IAnimation {

  /**
   * Execute the command of the function object given to it.
   * @param cmd the function object
   */
  void execute(ICommand cmd);

  /**
   * Get the list of animated objects.
   * @return the list of objects being animated
   */
  List<Drawable> listObjects();
}

