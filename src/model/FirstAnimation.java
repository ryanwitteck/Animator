package model;

import java.util.List;

import controller.commands.ICommand;
import model.ObjectInterfaces.Drawable;

public class FirstAnimation implements IAnimation {
  private final List<Drawable> objects;

  public FirstAnimation(List<Drawable> objects) {
    if (objects == null) {
      throw new IllegalArgumentException("Cannot have null arguments.");
    }
    this.objects = objects;
  }

  @Override
  public void execute(ICommand cmd) {
    cmd.execute();
  }

  @Override
  public List<Drawable> listObjects() {
    return objects;
  }

  private boolean inBounds(int index) {
    return (index >= 0 && index < this.objects.size());
  }
}
