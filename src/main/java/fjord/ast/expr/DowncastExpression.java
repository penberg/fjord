package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class DowncastExpression implements Expr {

  private final Expr castExpr;

  public DowncastExpression(Expr castExpr) {
    this.castExpr = castExpr;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getCastExpr() {
    return castExpr;
  }

}
