package fjord;

import static org.junit.Assert.*;
import org.junit.Test;

public class InteractiveTest {
  private Main.Environment env = new Main.Environment();

  @Test public void valueDefn() throws Exception {
    assertEval("let x = 1", "val x : int32 = 1\n");
    assertEval("let x = 1.0", "val x : float = 1.0\n");
    assertEval("let x = false", "val x : bool = false\n");
    assertEval("let x = true", "val x : bool = true\n");
  }

  @Test public void applicationExpr() throws Exception {
    assertEval("let x = 1 + 2", "val x : int32 = 3\n");
    assertEval("let x = 1 - 2", "val x : int32 = -1\n");
  }

  private void assertEval(String input, String output) throws Exception {
    assertEquals(input, output, Main.eval(env, input));
  }

  @Test public void quit() throws Exception {
    assertFalse(env.isHalted());
    String output = Main.eval(env, "#quit");
    assertTrue(env.isHalted());
    assertEquals("", output);
  }
}
