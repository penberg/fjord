package fjord.ast.typedefn;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class DelegateTypeDefn implements Node {

  private final TypeName typeName;
  
  private final UncurriedSig delegateSig;
  
  public DelegateTypeDefn(TypeName typeName, UncurriedSig delegateSig) {
    this.typeName = typeName; 
    this.delegateSig = delegateSig;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public TypeName getTypeName() {
    return typeName;
  }

  public UncurriedSig getDelegateSig() {
    return delegateSig;
  }

}
