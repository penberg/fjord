package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class LazyExpression implements Expr {

  private final Expr expr;
  
  public LazyExpression(Expr expr) {
    this.expr = expr;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getExpr() {
    return expr;
  }

}
