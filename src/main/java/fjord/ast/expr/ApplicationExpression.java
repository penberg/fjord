package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class ApplicationExpression implements Expr {

  private final Expr left;

  private final Expr right;

  public ApplicationExpression(Expr left, Expr right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getRight() {
    return right;
  }

  public Expr getLeft() {
    return left;
  }

}
