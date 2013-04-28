package fjord.ast.patparam;

import fjord.ast.NodeVisitor;
import fjord.ast.NodeWithChildren;

public class TuplePatParam extends NodeWithChildren<PatParam> implements PatParam {

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

}
