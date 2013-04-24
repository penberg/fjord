package fjord.ast.typedefn;

import java.util.List;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class InterfaceTypeDefn implements Node {

  private final TypeName typeName;
  
  private final List<TypeDefnElement> interfaceTypeBody;
  
  public InterfaceTypeDefn(TypeName typeName, List<TypeDefnElement> interfaceTypeBody) {
    this.typeName = typeName;
    this.interfaceTypeBody = interfaceTypeBody;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public TypeName getTypeName() {
    return typeName;
  }

  public List<TypeDefnElement> getInterfaceTypeBody() {
    return interfaceTypeBody;
  }

}
