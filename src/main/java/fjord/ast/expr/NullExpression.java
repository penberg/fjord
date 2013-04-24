package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class NullExpression implements Expr {

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}
