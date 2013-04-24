package fjord.ast.expr;

import java.util.List;

import fjord.ast.pat.AtomicPattern;
import fjord.ast.NodeVisitor;
import fjord.ast.typedefn.FunctionDefn;

public class FunctionExpression implements Expr {

  private final List<AtomicPattern> argumentPatterns;
  
  private final Expr funBody;
  
  public FunctionExpression(List<AtomicPattern> argumentPatterns, Expr funBody) {
    this.argumentPatterns = argumentPatterns;
    this.funBody = funBody;
  }

  public Expr getFunctionBody() {
    return funBody;
  }

  public List<AtomicPattern> getArgumentPatterns() {
    return argumentPatterns;
  }

  @Override
  public void accept(NodeVisitor visitor) {
  }

}
