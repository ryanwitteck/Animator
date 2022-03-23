import org.junit.Before;
import org.junit.Test;

import controller.commands.InstMoveCmd;
import controller.commands.MoveCmd;
import model.attributes.Posn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test cases for the implemented commands.
 */
public class CommandTest {

  //---------------------------- Constructor Null Arg Exception ------------------------------------

  @Test(expected = IllegalArgumentException.class)
  public void testInitFailInstMove() {
    DummyClass dummy = new DummyClass();
    new InstMoveCmd(dummy, 1, new Posn(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInitFailMove() {
    DummyClass dummy = new DummyClass();
    new MoveCmd(dummy, 1, 3, new Posn(0, 0));
  }


}
