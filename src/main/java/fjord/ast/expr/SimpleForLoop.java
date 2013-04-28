package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class SimpleForLoop implements Expr {

  private final String ident;

  private final Expr initExpr;

  private final Expr toExpr;

  private final Expr forBody;

  public SimpleForLoop(String ident, Expr initExpr, Expr toExpr, Expr forBody) {
    this.ident = ident;
    this.initExpr = initExpr;
    this.toExpr = toExpr;
    this.forBody = forBody;
  }

  public String getIdent() {
    return ident;
  }

  public Expr getInitExpr() {
    return initExpr;
  }

  public Expr getToExpr() {
    return toExpr;
  }

  public Expr getForBody() {
    return forBody;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}
