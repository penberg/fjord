package fjord.ast.pat;

import fjord.ast.NodeVisitor;

public class NamedPattern implements Pat {

  private final String ident;
  
  public NamedPattern(String ident) {
    this.ident = ident;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getIdent() {
    return ident;
  }
  
  @Override
  public String toString() {
    return ident;
  }

}
