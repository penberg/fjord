package fjord.ast.typedefn;

import java.util.List;

import fjord.ast.ModuleElem;
import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class EnumTypeDefn implements ModuleElem {

  private final TypeName typeName;
  
  private final List<EnumTypeCase> enumTypeCases;
  
  public EnumTypeDefn(TypeName typeName, List<EnumTypeCase> enumTypeCases) {
    this.typeName = typeName;
    this.enumTypeCases = enumTypeCases;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {

  }

  public TypeName getTypeName() {
    return typeName;
  }

  public List<EnumTypeCase> getEnumTypeCase() {
    return enumTypeCases;
  }

}
