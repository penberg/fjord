package fjord.ast.typedefn;

import java.util.List;

import fjord.ast.ModuleElem;
import fjord.ast.NodeVisitor;

public class TypeExtension implements ModuleElem {

  private final TypeName typeName;

  private final List<TypeDefnElement> typeDefnElements;

  public TypeExtension(TypeName typeName, List<TypeDefnElement> typeDefnElements) {
    this.typeName = typeName;
    this.typeDefnElements = typeDefnElements;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public List<TypeDefnElement> getTypeDefnElements() {
    return typeDefnElements;
  }

  public TypeName getTypeName() {
    return typeName;
  }

}
