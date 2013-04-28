package fjord.ast.typedefn;

import fjord.ast.Const;
import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class EnumTypeCase implements Node {

  private final String ident;
  
  private final Const constant;
  
  public EnumTypeCase(String ident, Const constant) {
    this.ident = ident;
    this.constant = constant;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  public String getIdent() {
    return ident;
  }

  public Const getConstant() {
    return constant;
  }
  
}
