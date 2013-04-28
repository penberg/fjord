package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class FieldInitializer implements Expr {

  private final String ident;
  
  private final Expr expr;
  
  public FieldInitializer(String ident, Expr expr) {
    this.ident = ident;
    this.expr = expr;
  }
   
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }


  public Expr getExpr() {
    return expr;
  }


  public String getIdent() {
    return ident;
  }

}
