import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import cs3500.animator.Main;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for our Main class and method.
 */
public class MainTest {

  /**
   * Tests our main method and text view using the big-bang-big-crunch animation.
   * This test will likely fail on other computers because the path of the big bang animation file
   * is too specific. I was unable to make this test work with a less specific path.
   *
   * @throws IOException if any of the File readers or writers fail.
   */
  @Test
  public void testBigBang() throws IOException {
    Main myMain = new Main();
    String path = "C:\\Users\\charl\\Desktop\\CS3500\\IntelliJ" +
            "\\Animator\\test\\demos\\big-bang-big-crunch.txt";
    String[] args = {"-view", "text", "-out", "test\\BigBangOut.txt", "-speed", "200", "-in", path};
    myMain.main(args);

    FileReader outReader = new FileReader("test\\BigBangOut.txt");
    char[] cb1 = new char[558];
    outReader.read(cb1);
    String actual = String.copyValueOf(cb1);

    FileReader expReader = new FileReader("test\\BigBangOutCorrect.txt");
    char[] cb2 = new char[563];
    expReader.read(cb2);
    String expected = String.copyValueOf(cb2);

    assertEquals(expected.replace("\r", ""), actual);
  }
}
