import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import cs3500.animator.Main;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for our Main class and method.
 * TODO add more tests
 */
public class MainTest {

  @Test
  public void testBigBang() throws IOException {
    Main myMain = new Main();
    String args[] = {"-view", "text", "-out", "test\\BigBangOut.txt", "-speed", "200", "-in",
            "C:\\Users\\charl\\Desktop\\CS3500\\IntelliJ" +
                    "\\Animator\\test\\demos\\big-bang-big-crunch.txt"};
    myMain.main(args);

    FileReader outReader = new FileReader("test\\BigBangOut.txt");
    char cb1[] = new char[655];
    outReader.read(cb1);
    String actual = "";
    for (int i = 0; i < 655; i++) {
      if (!(cb1[i] + "").isBlank()) {
        actual += cb1[i];
      }
    }

    FileReader expReader = new FileReader("test\\BigBangOutCorrect.txt");
    char cb2[] = new char[660];
    expReader.read(cb2);
    String expected = "";
    for (int i = 0; i < 660; i++) {
      if (!(cb2[i] + "").isBlank()) {
        expected += cb2[i];
      }
    }
    assertEquals(expected, actual);

  }

}
