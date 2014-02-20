package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.Operator;

public class ApplicationExpression implements Expr {

  private final Expr left;

  private final Operator op;

  private final Expr right;

  public ApplicationExpression(Expr left, Operator op, Expr right) {
    this.left  = left;
    this.op    = op;
    this.right = right;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getRight() {
    return right;
  }

  public Operator op() {
    return op;
  }

  public Expr getLeft() {
    return left;
  }

}
