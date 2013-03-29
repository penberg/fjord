package fjord;

import java.io.Console;

import fjord.compiler.Compiler;
import fjord.ast.CompilerDirectiveDecl;
import fjord.ast.NodeVisitor;
import fjord.ast.Node;

public class Main {

  public static void main(String[] args) throws Exception {
    banner();

    Environment env = new Environment();

    while (!env.isHalted()) {
      con.printf("> ");

      String input = con.readLine();
      if (input == null)
        break;

      eval(env, input);
    }
  }

  private static void eval(final Environment env, String input) throws Exception {
    Compiler compiler = new Compiler();

    Node node = compiler.compile(input);
    if (node == null)
      return;

    node.accept(new NodeVisitor() {
      @Override public void visit(CompilerDirectiveDecl decl) {
        if (decl.getIdent().equals("help"))
          help();
        else if (decl.getIdent().equals("quit"))
          env.halt();
        else
          con.printf("Invalid directive '%s'\n", decl);
      }
    });
  }

  private static void banner() {
    con.printf("Fjord\n");
    con.printf("\n");
    con.printf("For help type #help\n");
  }

  private static void help() {
    con.printf("\n");
    con.printf("  Directives:\n");
    con.printf("\n");
    con.printf("    #help                Display help\n");
    con.printf("    #quit                Exit\n");
    con.printf("\n");
  }

  private static Console con = System.console();

  private static class Environment {
    private boolean halted;

    public void halt() {
      this.halted = true;
    }

    public boolean isHalted() {
      return halted;
    }
  }
}
