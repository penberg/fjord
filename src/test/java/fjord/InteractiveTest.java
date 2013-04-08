package fjord;

import static org.junit.Assert.*;
import java.io.Console;
import org.junit.Test;

public class InteractiveTest {
  private Main.Environment env = new Main.Environment();

  @Test public void valueDefn() throws Exception {
    String output = Main.eval(env, "let x = 1");
    assertEquals("val x = 1\n", output);
  }

  @Test public void quit() throws Exception {
    assertFalse(env.isHalted());
    String output = Main.eval(env, "#quit");
    assertTrue(env.isHalted());
    assertEquals("", output);
  }
}
