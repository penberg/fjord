package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class UpcastExpression implements Expr {

  private final Expr castExpression;

  public UpcastExpression(Expr castExpression) {
    this.castExpression = castExpression;
  }
  
  public Expr getCastExpression() {
    return castExpression;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
  
}
