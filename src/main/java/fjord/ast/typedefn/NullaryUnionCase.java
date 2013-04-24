package fjord.ast.typedefn;

import fjord.ast.NodeVisitor;

public class NullaryUnionCase implements UnionTypeCaseData {

  private final String ident;
  
  public NullaryUnionCase(String ident) {
    this.ident = ident;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getIdent() {
    return ident;
  }
  
}
