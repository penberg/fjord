package fjord.ast.type;

import fjord.ast.NodeVisitor;
import fjord.ast.typar.TyparDefns;

public class ConstrainedType implements Type {

  private final Type type;
  
  private final TyparDefns typarDefns;

  public ConstrainedType(Type type, TyparDefns typarDefns) {
    this.type = type;
    this.typarDefns = typarDefns;
  }
  
  public Type getType() {
    return type;
  }

  public TyparDefns getTyparDefns() {
    return typarDefns;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
  
}
