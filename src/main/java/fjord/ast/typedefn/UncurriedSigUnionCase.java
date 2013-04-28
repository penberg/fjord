package fjord.ast.typedefn;

import fjord.ast.NodeVisitor;

public class UncurriedSigUnionCase implements UnionTypeCaseData {

  private final String ident;

  private final UncurriedSig uncurriedSig;

  public UncurriedSigUnionCase(String ident, UncurriedSig uncurriedSig) {
    this.ident = ident;
    this.uncurriedSig = uncurriedSig;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getIdent() {
    return ident;
  }

  public UncurriedSig getUncurriedSig() {
    return uncurriedSig;
  }

}
