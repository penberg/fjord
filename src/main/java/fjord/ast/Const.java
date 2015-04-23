package fjord.ast;

import fjord.types.TypeClass;

public class Const implements Node {
  public Const(String value) {
    this.value = value;
    this.typeClass = null;
  }

  public Const(String value, TypeClass typeClass) {
    this.value = value;
    this.typeClass = typeClass;
  }

  public Object parseValue() {
    return typeClass.parseValue(value);
  }

  public TypeClass getTypeClass() {
    return typeClass;
  }

  @Override public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override public String toString() {
    return value;
  }

  private final String value;
  private final TypeClass typeClass;
}
