package fjord.ast.pat;

import fjord.ast.NodeVisitor;

public class AsPattern implements Pat {

  private final String ident;

  private final Pat pattern;

  public AsPattern(Pat pattern, String ident) {
    this.pattern = pattern;
    this.ident = ident;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }


  public String getIdent() {
    return ident;
  }

  public Pat getPattern() {
    return pattern;
  }

  @Override
  public String toString() {
    return ident;
  }

}
