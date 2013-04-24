package fjord.ast.expr;

import java.util.List;

import fjord.ast.NodeVisitor;

public class RecordExpression implements Expr {
  
  private final List<FieldInitializer> fieldInitializers;

  public RecordExpression(List<FieldInitializer> fieldInitializers) {
    this.fieldInitializers = fieldInitializers;
  }

  @Override
  public void accept(NodeVisitor visitor) { 
    visitor.visit(this);
  }

  public List<FieldInitializer> getFieldInitializers() {
    return fieldInitializers;
  }
  
}