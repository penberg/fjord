package fjord.ast.type;

import fjord.ast.NodeVisitor;

public class ArrayType implements Type {
  
  private final Type type;

  private final int dimensions;
  
  public ArrayType(Type type, int dimensions) {
    this.type = type;
    this.dimensions = dimensions;
  }

  public Type getType() {
    return type;
  }

  public int getDimensions() {
    return dimensions;
  }
  
  @Override 
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}