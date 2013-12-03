package fjord;

import java.lang.reflect.Method;
import java.io.PrintWriter;

import jline.console.ConsoleReader;

import fjord.compiler.Compiler;
import fjord.ast.*;

public class Main {

  public static void main(String[] args) throws Exception {
    ConsoleReader reader = new ConsoleReader();
    reader.setPrompt("> ");

    PrintWriter out = new PrintWriter(reader.getOutput());
    out.println(banner());

    Environment env = new Environment();

    while (!env.isHalted()) {
      String line = reader.readLine();
      if (line == null)
        break;

      String output = eval(env, line);

      out.println(output);
      out.flush();
    }
  }

  public static String eval(final Environment env, String input) throws Exception {
    final Compiler compiler = new Compiler();

    Node node = compiler.parse(input);
    if (node == null)
      return "";

    final StringBuilder output = new StringBuilder();

    node.accept(new DefaultNodeVisitor() {
      @Override public void visit(CompilerDirectiveDecl decl) {
        if (decl.getIdent().equals("help"))
          output.append(help());
        else if (decl.getIdent().equals("quit"))
          env.halt();
        else
          output.append(String.format("Invalid directive '%s'\n", decl));
      }
    });

    node.accept(new DefaultNodeVisitor() {
      @Override public void visitAfter(ValueDefn defn) {
        Value val = compiler.codegen(defn);

        output.append(String.format("val %s = %s\n", defn.pattern(), val.eval()));
      }
    });

    return output.toString();
  }

  private static String banner() {
    StringBuilder output = new StringBuilder();
    String version = String.format(
        "Fjord (%s %s) [%s-%s]",
        System.getProperty("java.vm.name"),
        System.getProperty("java.runtime.version"),
        System.getProperty("os.name"),
        System.getProperty("os.arch")
      );
    output.append(String.format("%s\n\n", version));
    output.append(String.format("For help type #help\n"));
    return output.toString();
  }

  private static String help() {
    StringBuilder output = new StringBuilder();
    output.append(String.format("\n"));
    output.append(String.format("  Directives:\n"));
    output.append(String.format("\n"));
    output.append(String.format("    #help                Display help\n"));
    output.append(String.format("    #quit                Exit\n"));
    output.append(String.format("\n"));
    return output.toString();
  }

  public static class Environment {
    private boolean halted;

    public void halt() {
      this.halted = true;
    }

    public boolean isHalted() {
      return halted;
    }
  }
}
