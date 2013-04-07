package fjord;

import static org.junit.Assert.*;
import org.junit.Test;

public class InteractiveTest {
  private Main.Environment env = new Main.Environment();

  @Test public void quit() throws Exception {
    assertFalse(env.isHalted());
    Main.eval(env, "#quit");
    assertTrue(env.isHalted());
  }
}
