package playground;

import java.awt.*;

public class MouseWiggler {
  public static void main(String... args) throws Exception {
    Robot robot = new Robot();
    while(true) {
      robot.mouseMove(500, 500);
      Thread.sleep(10000);
      robot.mouseMove(502, 502);
      Thread.sleep(10000);
    }
  }
}
