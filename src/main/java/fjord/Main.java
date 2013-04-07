package fjord;

import static me.qmx.jitescript.CodeBlock.newCodeBlock;
import static me.qmx.jitescript.util.CodegenUtils.ci;
import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;
import me.qmx.jitescript.JiteClass;

import java.lang.reflect.Method;
import java.io.Console;

import fjord.compiler.Compiler;
import fjord.ast.*;

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

  public static void eval(final Environment env, String input) throws Exception {
    Compiler compiler = new Compiler();

    Node node = compiler.parse(input);
    if (node == null)
      return;

    node.accept(new DefaultNodeVisitor() {
      @Override public void visit(CompilerDirectiveDecl decl) {
        if (decl.getIdent().equals("help"))
          help();
        else if (decl.getIdent().equals("quit"))
          env.halt();
        else
          con.printf("Invalid directive '%s'\n", decl);
      }
    });

    node.accept(new DefaultNodeVisitor() {
      @Override public void visit(ValueDefn defn) {
        Value val = codegen(defn);

        con.printf("val %s = %s\n", defn.pattern(), val.eval());
      }
    });
  }

  private static Value codegen(final ValueDefn defn) {
    try {
      JiteClass jiteClass = new JiteClass(defn.pattern(), new String[] { p(Value.class) }) {{
        defineDefaultConstructor();

        defineMethod("eval", ACC_PUBLIC, sig(Object.class),
          newCodeBlock()
            .ldc(defn.expr())
            .areturn()
        );
      }};
      Class<?> klass = new JiteClassLoader().define(jiteClass);
      return (Value) klass.newInstance();
    } catch (Exception e) {
      return new Value() { public Object eval() { return null; } };
    }
  }

  private static void banner() {
    String version = String.format(
        "Fjord (%s %s) [%s-%s]",
        System.getProperty("java.vm.name"),
        System.getProperty("java.runtime.version"),
        System.getProperty("os.name"),
        System.getProperty("os.arch")
      );
    con.printf("%s\n\n", version);
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

  public static class Environment {
    private boolean halted;

    public void halt() {
      this.halted = true;
    }

    public boolean isHalted() {
      return halted;
    }
  }

  private static class JiteClassLoader extends ClassLoader {
    public Class<?> define(JiteClass jiteClass) {
      byte[] classBytes = jiteClass.toBytes();
      return super.defineClass(jiteClass.getClassName(), classBytes, 0, classBytes.length);
    }
  }
}
