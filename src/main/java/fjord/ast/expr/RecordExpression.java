package fjord.ast.expr;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

import java.util.List;

public class RecordExpression implements Expr {
  
  private final List<FieldInitializer> fieldInitializers;

  public RecordExpression(List<FieldInitializer> fieldInitializers) {
    this.fieldInitializers = fieldInitializers;
  }

  @Override
  public void accept(NodeVisitor visitor) {  }

  public List<FieldInitializer> getFieldInitializers() {
    return fieldInitializers;
  }
  
}