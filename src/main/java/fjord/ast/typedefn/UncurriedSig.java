package fjord.ast.typedefn;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class UncurriedSig implements Node {

  private final ArgSpec argSpec;
  
  private final Type type;
  
  public UncurriedSig(ArgSpec argSpec, Type type) {
    this.argSpec = argSpec; 
    this.type = type;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    
  }

  public Type getType() {
    return type;
  }

  public ArgSpec getArgSpec() {
    return argSpec;
  }

}
