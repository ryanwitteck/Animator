import java.io.FileWriter;
import java.io.IOException;

/**
 * This class creates a text file that can be parsed by our program to create an animation of
 * a simple solar system.
 */
public class MakeSolarSystem {

  /**
   * This method creates a text file that can be parsed by our program to create an animation of
   * a simple solar system. This does not take any arguments.
   */
  public static void main(String[] args) throws IOException {
    FileWriter writer = new FileWriter("SolarSystem.txt");

    int size = 1000;
    int center = size / 2;
    int endTime = 3600;
    writer.append("canvas " + size + " " + size + "\n");
    writer.append("rectangle name void min-x 0.0 min-y 0.0 " +
            "width " + size + " height " + size + " color 0.0 0.0 0.0 from 1 to " + endTime + "\n");

    writer.append(makePlanet("sun", center - 50, center - 50, 100, 1, 1, 0.5F, 1, endTime));

    writer.append(makePlanet("mercury", center + 140, center - 10,
            20, 0.2F, 0.2F, 0.2F, 1, endTime));
    writer.append(makePlanet("venus", center + 210, center - 20,
            40, 0.9F, 0.35F, 0.35F, 1, endTime));
    writer.append(makePlanet("earth", center + 280, center - 20,
            40, 0.1F, 0.1F, 0.9F, 1, endTime));
    writer.append(makePlanet("mars", center + 350, center - 20,
            40, 0.5F, 0.15F, 0.15F, 1, endTime));

    int nPlanets = 4;
    String[] names = {"mercury", "venus", "earth", "mars"};
    float[] radius = {140, 210, 280, 350};
    float[] angle = {0, 0, 0, 0};
    float[] aSpeed = {0.06F, 0.045F, 0.035F, 0.02F};
    int[] xoffset = {center, center, center, center};
    int[] yoffset = {center - 10, center - 20, center - 20, center - 20};

    for (int i = 1; i < endTime; i++) {
      for (int j = 0; j < nPlanets; j++) {
        writer.append(moveCmd(names[j],
                calcX(radius[j], angle[j], xoffset[j]), calcY(radius[j], angle[j], yoffset[j]),
                calcX(radius[j], angle[j] + aSpeed[j], xoffset[j]),
                calcY(radius[j], angle[j] + aSpeed[j], yoffset[j]),
                i, i + 1));
        angle[j] += aSpeed[j];
      }
    }
    writer.close();
  }

  /**
   * Return the string representation of a command to create a planet or other circular celestial
   * body in our animation.
   *
   * @param name  the name of the object
   * @param x     the initial x coordinate
   * @param y     the initial y coordinate
   * @param rad   the radius of the object
   * @param r     the red value of the object's color
   * @param g     the green value of the object's color
   * @param b     the blue value of the object's color
   * @param start when the object should be created
   * @param end   when the object should be removed
   * @return the string representation for the oval command
   */
  private static String makePlanet(String name, float x, float y, float rad,
                                   float r, float g, float b, int start, int end) {
    return String.format("oval name %s center-x %f center-y %f " +
                    "x-radius %f y-radius %f color %f %f %f from %d to %d\n",
            name, x, y, rad, rad, r, g, b, start, end);
  }

  /**
   * Return the string representation of a command to move the given object from the given start
   * coords to the given end coords over the given period.
   *
   * @param name the name of the object
   * @param x0   the initial x coordinate
   * @param y0   the initial y coordinate
   * @param x1   the end x coordinate
   * @param y1   the end y coordinate
   * @param t0   when the command should start
   * @param t1   when the command should end
   * @return the string representation for the move command
   */
  private static String moveCmd(String name, float x0, float y0,
                                float x1, float y1, int t0, int t1) {
    return String.format("move name %s moveto %f %f %f %f from %d to %d\n",
            name, x0, y0, x1, y1, t0, t1);
  }

  /**
   * Calculate the x coordinate of a planet given its orbital radius, angle relative to the sun,
   * and some x offset.
   *
   * @param radius the distance between the planet and the sun
   * @param angle  the angle the planet is at in its orbit
   * @param offset the x offset
   * @return the x coord of the planet
   */
  private static float calcX(float radius, float angle, float offset) {
    return radius * (float) (Math.cos(angle)) + offset;
  }

  /**
   * Calculate the y coordinate of a planet given its orbital radius, angle relative to the sun,
   * and some y offset.
   *
   * @param radius the distance between the planet and the sun
   * @param angle  the angle the planet is at in its orbit
   * @param offset the y offset
   * @return the y coord of the planet
   */
  private static float calcY(float radius, float angle, float offset) {
    return radius * (float) (Math.sin(angle)) + offset;
  }
}