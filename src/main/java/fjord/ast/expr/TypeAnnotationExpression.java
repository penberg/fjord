package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class TypeAnnotationExpression implements Expr {

  private final Expr expr;
  
  private final Type type;
  
  public TypeAnnotationExpression(Expr expr, Type type) {
    this.expr = expr;
    this.type = type;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }


  public Expr getExpr() {
    return expr;
  }


  public Type getType() {
    return type;
  }

  
  
}
