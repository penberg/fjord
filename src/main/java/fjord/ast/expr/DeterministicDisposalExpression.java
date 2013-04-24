package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class DeterministicDisposalExpression implements Expr {

  private final String ident;
  
  private final Expr initExpr;
  
  private final Expr inExpr;
  
  public DeterministicDisposalExpression(String ident, Expr initExpr, Expr inExpr) {
    this.ident = ident;
    this.initExpr = initExpr;
    this.inExpr = inExpr;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getIdent() {
    return ident;
  }

  public Expr getInitExpr() {
    return initExpr;
  }

  public Expr getInExpr() {
    return inExpr;
  }
  
}
