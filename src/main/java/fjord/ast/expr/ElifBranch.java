package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class ElifBranch implements Expr {

  private final Expr condition;
  
  private final Expr thenExpr;
  
  public ElifBranch(Expr condition, Expr thenExpr) {
    this.condition = condition;
    this.thenExpr = thenExpr;
  }
  
  @Override
  public void accept(NodeVisitor visitor) { 
    visitor.visit(this);
  }

  public Expr getCondition() {
    return condition;
  }

  public Expr getThenExpr() {
    return thenExpr;
  }

}
