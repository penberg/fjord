package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class TryFinallyExpression implements Expr {

  private final Expr tryExpr;

  private final Expr finallyExpr;

  public TryFinallyExpression(Expr tryExpr, Expr finallyExpr) {
    this.tryExpr = tryExpr;
    this.finallyExpr = finallyExpr;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getTryExpr() {
    return tryExpr;
  }

  public Expr getFinallyExpr() {
    return finallyExpr;
  }

}
