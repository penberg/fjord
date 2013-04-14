package fjord.compiler;

import fjord.Value;
import fjord.ast.*;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

import static me.qmx.jitescript.CodeBlock.newCodeBlock;
import static me.qmx.jitescript.util.CodegenUtils.ci;
import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;
import me.qmx.jitescript.JiteClass;

public class Compiler {

  public ScriptFragment parse(String input) throws Exception {
    CharStream stream = new ANTLRStringStream(input);

    FsharpLexer lexer = new FsharpLexer(stream);

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    FsharpParser parser = new FsharpParser(tokens);

    return parser.scriptFragment();
  }

  public Value codegen(final ValueDefn defn) {
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

  private static class JiteClassLoader extends ClassLoader {
    public Class<?> define(JiteClass jiteClass) {
      byte[] classBytes = jiteClass.toBytes();
      return super.defineClass(jiteClass.getClassName(), classBytes, 0, classBytes.length);
    }
  }
}
