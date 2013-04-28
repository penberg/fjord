package fjord.ast.expr;

import fjord.ast.Const;
import fjord.ast.NodeVisitor;

public class ConstantExpression implements Expr {

  private final Const cons;

  public ConstantExpression(Const cons) {
    this.cons = cons;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Const getCons() {
    return cons;
  }

  @Override
  public String toString() {
    return cons.toString();
  }

}
