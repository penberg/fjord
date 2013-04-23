package fjord.ast.pat;

import fjord.ast.NodeVisitor;

public class FieldPattern implements Pat {

  private final String ident;
  
  private final Pat pattern;
  
  public FieldPattern(String ident, Pat pattern) {
    this.ident = ident;
    this.pattern = pattern;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public Pat getPattern() {
    return pattern;
  }

  public String getIdent() {
    return ident;
  }

}
