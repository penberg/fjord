package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.ValueDefn;

public class ValueDefinitionExpression implements Expr {

  private final ValueDefn valueDefn;
  
  private final Expr expr;
  
  public ValueDefinitionExpression(ValueDefn valueDefn, Expr expr) {
    this.valueDefn = valueDefn;
    this.expr = expr;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
  
  public Expr getExpr() {
    return expr;
  }

  public ValueDefn getValueDefn() {
    return valueDefn;
  }
  
}
