package fjord.ast.typedefn;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class InterfaceSpec implements Node {

  private final Type type;
  
  public InterfaceSpec(Type type) {
    this.type = type;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {

  }

  public Type getType() {
    return type;
  }

}
