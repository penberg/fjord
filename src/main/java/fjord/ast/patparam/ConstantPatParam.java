package fjord.ast.patparam;

import fjord.ast.Const;
import fjord.ast.NodeVisitor;

public class ConstantPatParam implements PatParam {

  private final Const constant;
  
  public ConstantPatParam(Const constant) {
    this.constant = constant;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
  }

  public Const getConstant() {
    return constant;
  }

}
