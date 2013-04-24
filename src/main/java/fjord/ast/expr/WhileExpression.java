package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class WhileExpression implements Expr {

  private final Expr condition;
  
  private final Expr whileBody;
  
  public WhileExpression(Expr condition, Expr whileBody) {
    this.condition = condition;
    this.whileBody = whileBody;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getCondition() {
    return condition;
  }

  public Expr getWhileBody() {
    return whileBody;
  }

}
