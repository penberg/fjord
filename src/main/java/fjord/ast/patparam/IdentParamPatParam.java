package fjord.ast.patparam;

import fjord.ast.NodeVisitor;

public class IdentParamPatParam implements PatParam {

  private final String ident;
  
  private final PatParam patParam;
  
  public IdentParamPatParam(String ident, PatParam patParam) {
    this.ident = ident;
    this.patParam = patParam;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {

  }

  public String getIdent() {
    return ident;
  }

  public PatParam getPatParam() {
    return patParam;
  }

}
