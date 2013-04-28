package fjord.ast.patparam;

import fjord.ast.NodeVisitor;

public class NullPatParam implements PatParam {

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}
