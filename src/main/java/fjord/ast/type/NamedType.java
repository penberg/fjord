package fjord.ast.type;

import java.util.ArrayList;
import java.util.List;

import fjord.ast.NodeVisitor;

public class NamedType implements Type {
  
  private final String ident;
  
  private final List<Type> types;
  
  public NamedType(String ident) {
    this(ident, new ArrayList<Type>());
  }
  
  public NamedType(String ident, List<Type> types) {
    this.ident = ident;
    this.types = types;
  }

  public String getTypeName() {
    return ident;
  }
  
  public List<Type> getTypes() {
    return types;
  }
  
  @Override 
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}