package fjord.ast.type;

import fjord.ast.NodeVisitor;

public class AnonymousTypeWithSubtypeConstraint implements Type {
  
  private final Type type;
  
  public AnonymousTypeWithSubtypeConstraint(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }
  
  @Override 
  public void accept(NodeVisitor visitor) {
  }

}