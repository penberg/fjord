package fjord.ast.type.atomic;

import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class TypeTypeAtomicType implements AtomicType {

  private final Type type1;
  
  private final Type type2;
  
  public TypeTypeAtomicType(Type type1, Type type2) {
    this.type1 = type1;
    this.type2 = type2;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public Type getType2() {
    return type2;
  }

  public Type getType1() {
    return type1;
  }

}
