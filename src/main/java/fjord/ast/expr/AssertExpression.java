package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class AssertExpression implements Expr {

  private final Expr assertExpr;

  public AssertExpression(Expr assertExpr) {
    this.assertExpr = assertExpr;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getAssertExpr() {
    return assertExpr;
  }

}
