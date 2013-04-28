package fjord.ast.typedefn;

import fjord.ast.ModuleElem;
import fjord.ast.NodeVisitor;

public class DelegateTypeDefn implements ModuleElem {

  private final TypeName typeName;
  
  private final UncurriedSig delegateSig;
  
  public DelegateTypeDefn(TypeName typeName, UncurriedSig delegateSig) {
    this.typeName = typeName; 
    this.delegateSig = delegateSig;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public TypeName getTypeName() {
    return typeName;
  }

  public UncurriedSig getDelegateSig() {
    return delegateSig;
  }

}
