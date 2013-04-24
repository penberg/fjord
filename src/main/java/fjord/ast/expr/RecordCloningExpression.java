package fjord.ast.expr;

import java.util.List;

import fjord.ast.NodeVisitor;

public class RecordCloningExpression implements Expr {
  
  private final Expr expr;
  
  private final List<FieldInitializer> fieldInitializers;

  public RecordCloningExpression(Expr expr, List<FieldInitializer> fieldInitializers) {
    this.expr = expr;
    this.fieldInitializers = fieldInitializers;
  }

  @Override
  public void accept(NodeVisitor visitor) { 
    visitor.visit(this);
  }

  public Expr getExpr() {
    return expr;
  }
  
  public List<FieldInitializer> getFieldInitializers() {
    return fieldInitializers;
  }
  
}