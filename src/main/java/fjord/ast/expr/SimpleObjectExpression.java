package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class SimpleObjectExpression implements Expr {

  private final Type type;
  
  private final Expr expr;
  
  public SimpleObjectExpression(Type type, Expr expr) {
    this.type = type;
    this.expr = expr;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public Type getType() {
    return type;
  }

  public Expr getExpr() {
    return expr;
  }

}
