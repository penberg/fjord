package fjord.ast.typedefn;

import java.util.List;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;
import fjord.ast.type.Type;

public class CurriedSig extends NodeWithChildren<List<ArgSpec>> {

  private Type type;
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public void setType(Type type) {
    this.type = type;
  }
  
  public Type getType() {
    return type;
  }
  
}
