package fjord.ast.typedefn;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;
import fjord.ast.type.Type;

public class NAryUnionCase extends NodeWithChildren<Type> implements UnionTypeCaseData {

  private final String ident;

  public NAryUnionCase(String ident) {
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
