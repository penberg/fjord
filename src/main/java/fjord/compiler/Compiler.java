package fjord.compiler;

import fjord.Value;
import fjord.ast.*;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

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
      Codegen codegen = new Codegen();
      defn.accept(codegen);
      return (Value) codegen.getKlass().newInstance();
    } catch (Exception e) {
      return new Value() { public Object eval() { return null; } };
    }
  }
}
