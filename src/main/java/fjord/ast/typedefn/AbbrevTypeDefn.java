package fjord.ast.typedefn;

import fjord.ast.ModuleElem;
import fjord.ast.NodeVisitor;
import fjord.ast.type.Type;

public class AbbrevTypeDefn implements ModuleElem {

  private final TypeName typeName;

  private final Type type;

  public AbbrevTypeDefn(TypeName typeName, Type type) {
    this.typeName = typeName;
    this.type = type;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public TypeName getTypeName() {
    return typeName;
  }

  public Type getType() {
    return type;
  }

}
