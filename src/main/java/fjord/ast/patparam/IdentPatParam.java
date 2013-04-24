package fjord.ast.patparam;

import fjord.ast.NodeVisitor;

public class IdentPatParam implements PatParam {

  private final String ident;

  public IdentPatParam(String ident) {
    this.ident = ident;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {

  }

  public String getIdent() {
    return ident;
  }

}
