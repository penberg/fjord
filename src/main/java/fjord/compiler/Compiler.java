package fjord.compiler;

import fjord.ast.Node;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

public class Compiler {

  public Node compile(String input) throws Exception {
    CharStream stream = new ANTLRStringStream(input);

    FsharpLexer lexer = new FsharpLexer(stream);

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    FsharpParser parser = new FsharpParser(tokens);

    return parser.scriptFragment();
  }
}
