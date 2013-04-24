package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class DotLookupExpression implements Expr {

  private final Expr expr;
  
  private final String lookup;
  
  public DotLookupExpression(Expr expr, String lookup) {
    this.expr = expr;
    this.lookup = lookup;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Expr getExpr() {
    return expr;
  }

  public String getLookup() {
    return lookup;
  }

}
